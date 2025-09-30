package dev.syntax;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Failover - 장애복구
 * Connection conneciton = datasource.getConnection()를 통해 커넥션 객체를 생성했다고 할 때,
 * -> connection의 주소값(0x100)이라고 가정,
 * 
 * 만약에 MySQL 서버가 예기치 못하게 종료가 됨
 * 
 * -> 실제 MySQL 서버와 연결되어 있던 주소값이 무의미해지면서 쿼리 실패
 * 
 * 이후 MySQL 서버가 다시 복구되었음
 * 
 * 그때, Hikari가 어떻게 동작할까?
 */
public class Step08Failover {

  public static void main(String[] args) throws Exception {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/testdb");
        config.setUsername("root");
        config.setPassword("1234");

        config.setMaximumPoolSize(1);       // 풀에 1개만
        config.setMaxLifetime(15_000);      // 15초마다 갱신
        config.setKeepaliveTime(5_000);     // 5초마다 ping
        config.setConnectionTimeout(3_000);
        config.setValidationTimeout(2_000);

        try (HikariDataSource ds = new HikariDataSource(config)) {

            while (true) {
               Connection conn = ds.getConnection();
               
                try (PreparedStatement ps = conn.prepareStatement("SELECT NOW()");
                     ResultSet rs = ps.executeQuery()) {

                    if (rs.next()) {
                        System.out.println("쿼리 성공 (" + conn + "): " + rs.getString(1));
                    }
                } catch (Exception e) {
                    System.err.println("쿼리 실패 (" + conn + "): " + e.getMessage());
                }
                
                conn.close();

                Thread.sleep(5000);
            }
        }
    }


}
