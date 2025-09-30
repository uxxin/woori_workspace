package dev.syntax.step05_state_managing.step02_session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 로그인 처리를 수행하는 서블릿
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();

		// 사용자가 입력한 아이디, 비밀번호 추출
		String id = req.getParameter("id");
		String password = req.getParameter("password");

		// 인증이 성공하였을 경우, 세션 객체를 생성
		HttpSession session = req.getSession();
		System.out.println("세션 ID는 " + session.getId());

		if (session.isNew()) {
			out.println("로그인 완료<br>");
			out.println("<form action='logout' method='get'>");
			out.println("  <button type='submit'>로그아웃</button>");
			out.println("</form>");	
		} else {
			out.println("현재 로그인 상태입니다<br>");
		}

		out.close();
	}

}