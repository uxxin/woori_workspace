package dev.mouse.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mouse.dao.MouseDataAccessor;
import dev.mouse.model.Mouse;
import dev.mouse.util.DBUtil;

/*
 * 전체 Mouse 목록 조회 요청에 대한 처리 수행
 */
@WebServlet("/mice")
public class MouseListServlet extends HttpServlet {

	private MouseDataAccessor mouseDAO;

	public MouseListServlet() {
		this.mouseDAO = new MouseDataAccessor();
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

		// MouseDAO.java를 통해 DB에서 Mouse 데이터의 값을 조회해서 받아오기
		List<Mouse> mice = mouseDAO.findAll();

		// 조회된 값을 화면에 출력하는 View 코드 작성
		StringBuilder responseHTML = new StringBuilder();

		responseHTML.append("<html>");
		responseHTML.append("	<head>");
		responseHTML.append("		<title>Mouse 목록 페이지</title>");
		responseHTML.append("	</head>");
		responseHTML.append("	<body>");
		responseHTML.append("<h1>Mouse 목록 페이지</h1>");
		responseHTML.append("<ul>");

		String liStyle = "style=\"display:flex; list-style:none;\"";
		for (Mouse mouse : mice) {
			responseHTML.append("<li " + liStyle + ">");
			responseHTML.append(String.format("<div>%s</div>", mouse.getId()));
			responseHTML.append(String.format("<div>%s</div>", mouse.getName()));
			responseHTML.append(String.format("<div>%s</div>", mouse.getCountry()));
			responseHTML.append(String.format("<div>%s</div>", mouse.getAddress()));
			responseHTML.append("</li>");

		}
		responseHTML.append("</ul>");

		responseHTML.append("	</body>");
		responseHTML.append("		</html>");

		response.setStatus(200);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");

		PrintWriter writer = response.getWriter();
		writer.write(responseHTML.toString());
	}

}
