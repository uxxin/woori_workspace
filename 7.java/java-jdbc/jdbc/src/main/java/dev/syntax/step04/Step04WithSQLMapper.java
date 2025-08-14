package dev.syntax.step04;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import dev.syntax.step04.DBUtil;

public class Step04WithSQLMapper {
    
    // DBMS 서버 접속 정보
    private static Connection connection;
    private static Statement statement;
    
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
          
          // 사용자 입력 받기
            System.out.print("조회할 사용자 이름을 입력하세요: ");
            String inputName = sc.nextLine();
                        
            // 1. DB 연결
            connection = DBUtil.getConnection("src/main/resources/jdbc.properties");
            System.out.println(connection);
            
            // 2. Statement 객체 생성
            statement = connection.createStatement();
            
            String sql = "SELECT * FROM customer";
            System.out.println("실행할 쿼리: " + sql);
            
            // 3. 실행
            ResultSet rs = statement.executeQuery(sql);
            
            // 4. 결과 출력
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

