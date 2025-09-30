package dev.syntax;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 2개 이상의 커넥션 객체를 보관, 관리를 수행하는 커넥션 풀 클래스
 */

public class Step02UsingConnectionPool {
	
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "1234";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/";
	private static final String DATABASE_SCHEMA = "testdb";
	
	public static void main(String[] args) {
	    // (1~5번)은 한번씩만 수행, (2~4번)은 운영 과정에서 반복적으로 수행되는 작업
		//커넥션 풀 생성
			//1. static 메서드인 create() 호출
			try {
				CustomConnectionPool.create(DB_URL+DATABASE_SCHEMA, USER_NAME, PASSWORD);
//				while(true);
				
				//(2~4) 시스템 운영 과정에서 지속적으로 반복하여 동작하는 작업들
		        // TODO: 2. getConnection()을 통해 커넥션 풀에서 커넥션 객체를 획득
				// (풀에서 커넥션 객체를 꺼내는 작업)
				Connection connection1 = CustomConnectionPool.getConnection();
				Connection connection2 = CustomConnectionPool.getConnection();
				// -> 커넥션을 가져올 때마다 URL, USER의 정보를 전달하지 않아도 됨
				
				//3. 각 클라이언트들은 할당받은 커넥션 객체를 통해 DB 입출력 처리를 수행했다고 가정
				
				// TODO: 4. 입출력 처리가 완료되었을 경우에는 releaseConnection()를 통해 커넥션 객체를 반납.
				CustomConnectionPool.releaseConnection(connection1);

				
				// TODO: 5. 프로그램 종료 전에는 shutdown() 호출해서 모든 커넥션 객체의 자원 해제
				CustomConnectionPool.shutdown();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
