package dev.syntax.step05_state_managing.step01_cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 브라우저에서 전송한 쿠키의 값을 추출하여 처리하는 서블릿
@WebServlet("/read-cookie")
public class ReadCookieServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html;charset=UTF-8");

    PrintWriter out = resp.getWriter();
    
    // 요청 객체에서 쿠키를 추출
    Cookie[] cookies = req.getCookies();
    
    for (Cookie cookie : cookies) {
      out.printf("%s : %s", cookie.getName(), cookie.getValue() + "<br>");
    }

    out.close();
  }
  
}
