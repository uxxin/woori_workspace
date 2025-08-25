package dev.syntax.step05_state_managing.step02_session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 로그아웃 할 때는, 세션을 새롭게 생성하면 안 되기 때문에 "false"로 지정
		HttpSession session = req.getSession(false);

		if (session != null) {
			session.invalidate();
			System.out.println("로그아웃: 세션 삭제됨");
		}

		// 로그인 페이지로 다시 이동
		resp.sendRedirect(req.getContextPath() + "/login.html");
	}
}