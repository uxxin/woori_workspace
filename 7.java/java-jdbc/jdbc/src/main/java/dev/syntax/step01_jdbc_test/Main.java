package dev.syntax.step01_jdbc_test;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.*;

/**
 * JDBC 적용 
 */
public class Main {
  
  // 1. DBMS 서버에 접근하기 위한 설정 정보 작성
  private static final String USER_NAME = "root";
  private static final String PASSWORD = "1234";
  private static final String DB_URL = "jdbc:mysql://localhost:3306/";
  private static final String DATABASE_SCHEMA = "testdb";
  
  private static Connection connection;
  private static Statement statement;

  public static void main(String[] args) {
	  // 2. DBMS와의 커넥션 연결
	  // Class.forName("패키지 풀네임을 포함한 특정 클래스 이름, FQCN")
	  try {
		Class.forName("com.mysql.cj.jdbc.Driver"); //클래스 로딩
		// JDBC 4.0 버전 이후로 모든 드라이버들은 클래스패스를 통해 ----- 
		connection = DriverManager.getConnection(DB_URL+DATABASE_SCHEMA, USER_NAME, PASSWORD);
		System.out.println(connection);
		// while(true);
		
		// 3. 쿼리 실행을 위한 준비
			// 3-1. Java App에서 작성한 쿼리문을 DBMS로 전달해주는 객체 - Statement
		statement = connection.createStatement();
		
		final String sql = "SELECT NOW()"; // 현재 시간 조회
		ResultSet rs = statement.executeQuery(sql);
		
		//next() => 다음 행에 데이터가 있는지 확인하여 커서를 이동 시킴. * 지금은 행 하나(now 포함 2개)여서 if고 여러개면 while 써서 꺼냄. 
		if(rs.next()) {
			String now = rs.getString(1); // 결과셋 테이블이 첫 번째 컬럼
			System.out.println("현재 시간: "+now);
		}
		
	} catch (ClassNotFoundException | SQLException e) {
		e.printStackTrace();
	} finally {
		// 5. DB와의 커넥션 자원을 해제하는 처리
		try {
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
    
  }

}
