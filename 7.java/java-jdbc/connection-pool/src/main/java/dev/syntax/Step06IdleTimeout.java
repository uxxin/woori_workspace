package dev.syntax;

import java.sql.Connection;
import java.sql.SQLException;
import dev.syntax.config.HikariCPDataSourceV2;

/**
 * IdleTimeout 옵션 테스트 시나리오
 * 
 *  커넥션 풀에 생성된 커넥션 객체들의 유휴 연결 유지 시간(idleTimeout)이 경과될 경우,
 *  최소 연결 개수(minimumIdle)까지만 유지되는지 확인
 *  
 *  
 */

public class Step06IdleTimeout {

  public static void main(String[] args) throws SQLException, InterruptedException {
    // 초기 커넥션 1개 요청
    System.out.println("10초 경과 전..");
    Connection connection1 =  HikariCPDataSourceV2.getConnection(); // 커넥션 풀에서 1번째 커넥션 할당 요청
    // After cleanup  stats (total=1, active=1, idle=0, waiting=0)
        
    Thread.sleep(10000);
    // After adding stats (total=4, active=1, idle=3, waiting=0)
    
    System.out.println("10초 후..");
    Connection connection2 =  HikariCPDataSourceV2.getConnection(); // 2번째 커넥션 할당 요청
    Connection connection3 =  HikariCPDataSourceV2.getConnection(); // 3번째 커넥션 할당 요청
    Connection connection4 =  HikariCPDataSourceV2.getConnection(); // 4번째 커넥션 할당 요청
    Connection connection5 =  HikariCPDataSourceV2.getConnection(); // 5번째 커넥션 할당 요청
        
    Thread.sleep(10000);
    System.out.println("10초 후.. 2개 반납");
    connection4.close(); // 커넥션 객체 자원 해제가 아닌 반납, Releases this Connection object
    connection5.close();
        
    while (true) {}
  }

}