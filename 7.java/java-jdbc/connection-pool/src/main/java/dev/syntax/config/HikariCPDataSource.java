package dev.syntax.config;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Hikari CP 설정용 파일
 */
public class HikariCPDataSource {
  
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;
  
  static { // static 블록, 애플리케이션 실행 시 실행됨
    
    // Hikari CP를 구성하기 위한 필수 설정 파라미터
    config.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
    // ...
    config.setUsername("root");
    config.setPassword("1234");
    
    // ds 생성 전에 미리 해야함 . 
    config.setMaximumPoolSize(20);

    ds = new HikariDataSource(config);
      }
  
  public static Connection getConnection() throws SQLException {
    return ds.getConnection();
  }
  
  private HikariCPDataSource() {}
  
}