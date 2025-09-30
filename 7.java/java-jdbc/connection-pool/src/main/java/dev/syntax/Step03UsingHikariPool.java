package dev.syntax;
import java.sql.Connection;
import java.sql.SQLException;

import dev.syntax.config.HikariCPDataSource;

/**
 * 이전까지는 우리가 직접 만들었다면 상용 library인 hikari cp를 이용해보자
 */
public class Step03UsingHikariPool {

	public static void main(String[] args) throws SQLException {
		// Hikari 버전 5.0.1 사용하기
		// 로깅 라이브러리는 logback
		
		Connection connection = HikariCPDataSource.getConnection();
		System.out.println(connection);
		
		while(true);

	}

}