package dev.syntax.step02_practice;

import java.sql.*;


public class Main {
	
	// 1. DBMS 서버에 접근하기 위한 설정 정보 작성
	  private static final String USER_NAME = "root";
	  private static final String PASSWORD = "1234";
	  private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	  private static final String DATABASE_SCHEMA = "sakila";
	  
	  private static Connection connection;
	  private static Statement statement;
	  
	public static void main(String[] args) {
		// 2. DBMS와의 커넥션 연결
		  // Class.forName("패키지 풀네임을 포함한 특정 클래스 이름, FQCN")
		  try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL+DATABASE_SCHEMA, USER_NAME, PASSWORD);
			System.out.println(connection);

			statement = connection.createStatement();
			
			final String sql = "SELECT * FROM rental LIMIT 20 ;"; 
			ResultSet rs = statement.executeQuery(sql);
			
			System.out.println("---------------------------------------------------------------------");
			System.out.printf("%-9s | %-20s | %-12s | %s%n",
			        "rental_id", "rental_date", "customer_id", "return_date");
			System.out.println("---------------------------------------------------------------------");
			while(rs.next()) {
					int rental_id  = rs.getInt("rental_id");
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
				statement.close();
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    
	}
}
