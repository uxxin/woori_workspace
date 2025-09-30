package dev.syntax.yujin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getNickname")
public class GetNicknameServlet extends HttpServlet {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sinyangkimoon";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("application/json;charset=UTF-8");

        String nickname = "사용자"; // 기본값
        try {
            // MySQL 드라이버 로딩
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement("SELECT nickname FROM `user` WHERE id = ?")) {
                
                stmt.setInt(1, 1); // id = 1
                ResultSet rs = stmt.executeQuery();
                
                if (rs.next()) {
                    nickname = rs.getString("nickname");
                    System.out.println("DB nickname: " + nickname);
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("id=1 유저 없음");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("MySQL 드라이버 로딩 실패");
        }

        // JSON 형식으로 반환
        resp.getWriter().write("{\"nickname\": \"" + nickname + "\"}");
    }
}

