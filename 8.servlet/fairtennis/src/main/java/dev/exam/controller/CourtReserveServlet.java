package dev.exam.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.exam.model.Reservation;

@WebServlet("/tennis/reserve")
public class CourtReserveServlet extends HttpServlet {

	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {

	    String sessionToken = (String) request.getSession().getAttribute("csrfToken");
	    String requestToken = request.getParameter("csrfToken");

	    // 세션 토큰과 요청 토큰 비교
	    if (sessionToken == null || !sessionToken.equals(requestToken)) {
	        // 토큰 불일치 시 unauthorized.jsp로 포워딩
	        final String path = "/WEB-INF/unauthorized.jsp";
	        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
	        dispatcher.forward(request, response);
	        return; // 반드시 return으로 이후 코드 실행 차단
	    }

	    // 토큰 사용 후 삭제 (한 번만 유효)
	    request.getSession().removeAttribute("csrfToken");

	    // form 입력 값 추출
	    String reservedTimeString = request.getParameter("datetime");
	    LocalDateTime reservedTime = LocalDateTime.parse(reservedTimeString, formatter);

	    int courtNumber = Integer.parseInt(request.getParameter("court"));
	    String centerName = request.getParameter("center");

	    // 예매 처리
	    Reservation reservation = new Reservation(centerName, courtNumber, reservedTime);
	    request.setAttribute("reservation", reservation);

	    final String path = "/WEB-INF/success.jsp";
	    RequestDispatcher dispatcher = request.getRequestDispatcher(path);
	    dispatcher.forward(request, response);
	}

}
