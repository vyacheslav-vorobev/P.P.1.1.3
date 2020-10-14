package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        setSql("CREATE TABLE IF NOT EXISTS `store`.`new_table1` (\n" +
                    "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `lastName` VARCHAR(45) NOT NULL,\n" +
                    "  `age` INT(45) NOT NULL,\n" +
                    "  PRIMARY KEY (`id`, `name`, `lastName`, `age`));");
    }

    public void dropUsersTable() {
        setSql("DROP TABLE IF EXISTS new_table1;");
    }

    public void saveUser(String name, String lastName, byte age) {
        setSql(String.format("INSERT INTO `store`.`new_table1` (`name`, `lastName`, `age`) VALUES ('%s', '%s', '%d');", name,lastName, age));
        System.out.println("User с именем – "+ name +" добавлен в базу данных ");
    }

    public void removeUserById(long id) {
        setSql(String.format("delete from new_table1 where %d", id));
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String query = "select * from new_table1";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                User user = new User(resultSet.getString(2),
                        resultSet.getString(3),resultSet.getByte(4));

                user.setId(resultSet.getLong(1));
                users.add(user);
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        setSql("delete from new_table1;");
    }
    private void setSql(String sql){
        try (Connection connection = Util.connection(); Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            statement.execute(sql);
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
