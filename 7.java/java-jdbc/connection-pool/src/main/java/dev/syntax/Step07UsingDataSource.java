package dev.syntax;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Step07UsingDataSource {

  public static void main(String[] args) throws SQLException {
    final String URL = "jdbc:mysql://localhost:3306/";
    final String DATABASE_NAME = "testdb";
    final String USER = "root";
    final String PASSWORD = "1234";
    
    // 순수 JDBC에서는 DriverManager.getConnection();으로 사용했음
    // DriverManager는 DataSource를 구현하지 않았기 때문에 별도의 라이브러리 설치 필요
    // org.springframework.jdbc.datasource를 활용
    // spring-jdbc-5.3.24.jar 다운
    DataSource dataSource 
      = new DriverManagerDataSource(URL+DATABASE_NAME, USER, PASSWORD);
    
    // 커넥션 연결할 때마다 URL, USER, PASSWORD 전달x
    Connection connection1 = dataSource.getConnection();
    System.out.println(connection1);
    
    // Hikari, ApacheDBCP도 DataSource 인터페이스를 구현해둠
    // Ex. HikariDataSource
  }

}
