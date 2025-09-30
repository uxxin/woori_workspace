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

//@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sinyangkimoon";
	private static final String JDBC_USER = "root";
	private static final String JDBC_PASSWORD = "1234";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		boolean loginSuccess = false;

		try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
				PreparedStatement stmt = conn
						.prepareStatement("SELECT * FROM user WHERE nickname = ? AND password = ?")) {

			stmt.setString(1, username);
			stmt.setString(2, password);

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				loginSuccess = true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		

}
