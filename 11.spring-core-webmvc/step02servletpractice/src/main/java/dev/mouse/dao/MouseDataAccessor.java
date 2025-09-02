package dev.mouse.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.mouse.model.Mouse;
import dev.mouse.util.DBUtil;

// Mouse DAO 클래스
public class MouseDataAccessor {
	
	public List<Mouse> findAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rset;
		Mouse mouse;
		List<Mouse> mouseList = new ArrayList<>();

		String query = "SELECT * FROM mouse";

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(query);
			rset = pstmt.executeQuery();
			while (rset.next()) {
				int id = rset.getInt("id");
				String name = rset.getString("name");
				String country = rset.getString("country");
				String address = rset.getString("address");
				mouse = new Mouse(id, name, country, address);

				mouseList.add(mouse);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(con, pstmt);
		}

		return mouseList;
	}
	
	public int add(Mouse mouse) {
		int result = 0;
		String query = "INSERT INTO mouse (name, country, address) VALUES(?, ?, ?)";
		try (Connection con = DBUtil.getConnection(); 
			 PreparedStatement pstmt = con.prepareStatement(query)) {

			pstmt.setString(1, mouse.getName());
			pstmt.setString(2, mouse.getCountry());
			pstmt.setString(3, mouse.getAddress());

			// DB Query 수행
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}

		return result;
	}
}
