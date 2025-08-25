package dev.syntax.step03_servlet_request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// HTTPRequest 요청을 처리하는 서블릿 객체 예시 코드
// 사용자의 요청 정보(Request Message)를 받아서 처리할 수 있는 서블릿
@WebServlet(urlPatterns = "/first-request-servlet")
public class ProcessingHttpRequestServlet extends HttpServlet {

	/*
	 * HttpServletRequest 네트워크를 통해 전달받은 요청 정보를 자바 서버에서 활용하기 위해 자바에서 추상화해놓은 클래스
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		// 응답객체에 설정하는 코드
		// 응답할 컨텐츠의 타입, 인코딩 타입 등으 명시
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");


		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<body>");
		out.println("<head>");
		out.println("<title>Request Information Example</title>");
		out.println("</head>");

		out.println("<body>");

		out.println("<h3>요청 메시지에 담긴 정보 확인</h3>");

		// Header 메시지에 담긴 정보 추출
		out.println("Context Path : " + request.getContextPath() + "<br/>");
		out.println("Request URL : " + request.getRequestURL() + "<br/>");
		out.println("Request URI : " + request.getRequestURI() + "<br/>");
		out.println("Server Port : " + request.getServerPort() + "<br/>");
		out.println("Request Protocol : " + request.getProtocol() + "<br/>");
		out.println("Request METHOD: " + request.getMethod() + "<br/>");
		out.println("Query String : " + request.getQueryString() + "<br/>");// http://localhost:8080/step10newsyntax/first-request-servlet?name=jerry&age=15
		out.println("Parameter(name) : " + request.getParameter("name") + "<br/>");
		out.println("Parameter(age) : " + request.getParameter("age") + "<br/>");

		out.println("</body>");
		out.println("</html>");

		out.close();
	}

}
