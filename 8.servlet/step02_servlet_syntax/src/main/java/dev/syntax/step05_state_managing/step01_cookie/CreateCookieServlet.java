package dev.syntax.step05_state_managing.step01_cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 쿠키를 생성해서 브라우저에(클라이언트) 심는 작업을 수행하는 서블릿
 */
@WebServlet("/create-cookie")
public class CreateCookieServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");
    
    Cookie cookie = new Cookie("id", "guest");
    resp.addCookie(cookie); // 응답 객체에 쿠키 동봉
    
    PrintWriter out = resp.getWriter();
    out.print("서버에서 생성한 쿠키가 클라이언트로 전송 되었음.");
    out.close();
    
  }

  
}
