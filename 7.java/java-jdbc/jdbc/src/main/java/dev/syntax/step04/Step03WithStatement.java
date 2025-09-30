package dev.syntax.step04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Step03WithStatement {
    
    // DBMS 서버 접속 정보
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_SCHEMA = "sakila";
    
    private static Connection connection;
    private static Statement statement;
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
          
          // 사용자 입력 받기
            System.out.print("조회할 사용자 이름을 입력하세요: ");
            String inputName = sc.nextLine();
                        
            // 1. DB 연결
            connection = DriverManager.getConnection(DB_URL + DATABASE_SCHEMA, USER_NAME, PASSWORD);
            
            // 2. Statement 객체 생성
            statement = connection.createStatement();
            
            // 3. 인젝션 취약한 쿼리 (문자열 결합)
            String sql = "SELECT * FROM customer WHERE first_name = '" + inputName + "'";
            System.out.println("실행할 쿼리: " + sql);
            
            // 4. 실행
            ResultSet rs = statement.executeQuery(sql);
            
            // 5. 결과 출력
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("customer_id")
                    + ", First name: " + rs.getString("first_name")
                    + ", Last name: " + rs.getString("last_name"));
            }
            
        } catch (SQLException e) {
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

// SELECT * FROM users WHERE name = '' OR '1'='1'