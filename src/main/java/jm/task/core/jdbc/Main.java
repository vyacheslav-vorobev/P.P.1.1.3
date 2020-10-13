package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UserService userService  = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Вячеслав","Воробьев", (byte)23);
        userService.saveUser("НеВячеслав","НеВоробьев", (byte)64);
        userService.saveUser("Vyacheslav","Vorobev", (byte)56);
        userService.saveUser("ИмяЧеловека","Фамилия", (byte)43);
        List<User> list = userService.getAllUsers();
        for (User user: list){
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
