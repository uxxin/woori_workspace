package dev.syntax.step03_properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Main {

    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {
        Properties p = new Properties();

        try (InputStream stream = new FileInputStream("jdbc.properties")) {
            p.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        String USER_NAME = p.getProperty("USER_NAME");
        String PASSWORD = p.getProperty("PASSWORD");
        String DB_URL = p.getProperty("DB_URL");
        String DATABASE_SCHEMA = p.getProperty("DATABASE_SCHEMA");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL + DATABASE_SCHEMA, USER_NAME, PASSWORD);
            System.out.println("DB 연결 성공: " + connection);

            statement = connection.createStatement();
            final String sql = "SELECT * FROM rental LIMIT 20";
            ResultSet rs = statement.executeQuery(sql);

            System.out.println("---------------------------------------------------------------------");
            System.out.printf("%-9s | %-20s | %-12s | %s%n",
                    "rental_id", "rental_date", "customer_id", "return_date");
            System.out.println("---------------------------------------------------------------------");

            while (rs.next()) {
                int rental_id = rs.getInt("rental_id");
                String rental_date = rs.getString("rental_date");
                int customer_id = rs.getInt("customer_id");
                String return_date = rs.getString("return_date");

                System.out.printf("%-9d | %-20s | %-12d | %s%n",
                        rental_id, rental_date, customer_id, return_date);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

