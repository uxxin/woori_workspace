package dev.syntax.step04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Step04WithPreparedStatement {

	// DBMS 서버 접속 정보
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "1234";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_SCHEMA = "sakila";

	private static Connection connection;
	private static PreparedStatement statement;

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {

			System.out.print("조회할 사용자 이름을 입력하세요: ");
			String inputName = sc.nextLine();

			connection = DriverManager.getConnection(DB_URL + DATABASE_SCHEMA, USER_NAME, PASSWORD);

			String sql = "SELECT * FROM customer WHERE first_name = ? ";

//          statement = connection.createStatement();
			statement = connection.prepareStatement(sql);
			System.out.println("실행할 쿼리: " + sql);
			statement.setString(1, inputName); // 사용자 입력 값으로 이름 set

			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println("ID: " + rs.getInt("customer_id") + ", First name: " + rs.getString("first_name")
						+ ", Last name: " + rs.getString("last_name"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}