package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String url = "jdbc:mysql://localhost:3306/zadacha_1_1_3";
    private static final String user = "root";
    private static final String password = "1234";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Драйвер зарегистрирован!");
        } catch (ClassNotFoundException e) {
            System.err.println("Ошибка: драйвер не зарегистрирован!" + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Подключение успешно!");
        } catch (SQLException e) {
            System.err.println("Ошибка:" + e.getMessage());
        }
        return connection;
    }
}
