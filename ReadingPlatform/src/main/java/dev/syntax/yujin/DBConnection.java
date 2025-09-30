package dev.syntax.yujin;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {	
	private static final String URL = "jdbc:mysql://localhost:3306/sinyangkimoon"; // DB 이름 넣기
    private static final String USER = "root"; // 사용자명
    private static final String PASSWORD = "1234"; // 비밀번호

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ DB 연결 성공!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

}
