package dev.syntax;

import java.sql.Connection;
import java.sql.SQLException;

import dev.syntax.config.HikariCPDataSourceV2;

public class Step05 {

    public static void main(String[] args) throws SQLException, InterruptedException {
        System.out.println("=== 시나리오 1: minimumIdle=3, idleTimeout=5000ms 테스트 ===");

        // 5개의 커넥션을 얻어서 사용 후 바로 닫기
        for (int i = 1; i <= 5; i++) {
            Connection conn = HikariCPDataSourceV2.getConnection();
            System.out.println(i + "번째 커넥션 얻음");
            
            // 실제 쿼리 실행 가능 (선택)
            // Statement stmt = conn.createStatement();
            // stmt.executeQuery("SELECT NOW()");
            
            conn.close(); // 풀로 반환
            System.out.println(i + "번째 커넥션 닫음 (풀에 반환됨)");
        }

        // idleTimeout 기다리기
        System.out.println("커넥션이 idle 상태로 유지되는지 확인 위해 10초 대기...");
        Thread.sleep(10000); // 10초 대기

        System.out.println("대기 종료! Workbench에서 'SHOW PROCESSLIST;' 확인:");
        System.out.println("- 최소 3개의 커넥션은 살아있어야 함 (minimumIdle=3)");
        System.out.println("- 나머지 idle 커넥션은 idleTimeout 후 제거되어야 함");
    }
}
