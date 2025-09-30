package dev.syntax;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Step01UsingDriverManager {
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "1234";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_SCHEMA = "testdb";
	
	public static void main(String[] args) throws SQLException {
		Connection connection1 = DriverManager.getConnection(DB_URL+DATABASE_SCHEMA,USER_NAME, PASSWORD);
		Connection connection2 = DriverManager.getConnection(DB_URL+DATABASE_SCHEMA,USER_NAME, PASSWORD);
		
		System.out.println(connection1);
		System.out.println(connection2);
		
		while(true);
	}
}
