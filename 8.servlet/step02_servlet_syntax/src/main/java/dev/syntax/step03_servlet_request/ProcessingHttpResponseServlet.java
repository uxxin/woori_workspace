package dev.syntax.step03_servlet_request;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 사용자의 요청 정보를 받고, 적절한 응답 객체를 통해 처리하는 서블릿
 */
@WebServlet(urlPatterns = "/first-response-servlet")
public class ProcessingHttpResponseServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 응답할 컨텐츠의 타입, 인코딩 타입 등을 명시
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    
    // 커스텀 응답 메시지 작성, 개발자 도구의 네트워크 탭에서 응답 헤더 확인
    response.setHeader("custom-header", "my-custom-header-message");
    
    // 화면에 출력할 문자열
    PrintWriter out = response.getWriter();
    
    // <html></html> 생략
    out.print("Hello");
    out.close();
  }
  
}
