package dev.syntax;

import java.sql.Connection;
import java.sql.SQLException;

import dev.syntax.config.HikariCPDataSource;
import dev.syntax.config.HikariCPDataSourceV2;

/**
 * MaxConnection ?
 * - 히카리가 생성한 커넥션 개수를 초과하여 커넥션을 요청하면 ? 
 * - 로그를 통해 어떻게 동작하는지 확인
 */
public class Step04MaxConnection {
	public static void main(String[] args) throws SQLException {
		for(int i =1;i<=10;i++) {
			HikariCPDataSourceV2.getConnection();
		}
		
		HikariCPDataSourceV2.getConnection(); // 11번째 커넥션 객체 할당 요청
		
		while(true);
	}
}
