package dev.syntax.step01_hello_servlet;

import java.io.IOException;

import javax.servlet.ServletException;
// javax -> jakarta로 네임스페이스가 변경됨
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// 서버를 실행하면 localhost:8080/step02_servlet_syntax로 서버가 켜질건데,
// 사용자가 어떤 하위 경로(세그먼트)로 요청했을 때, HelloServlet이 실행되게 할 것인가?
// -> 경로 매핑

public class HelloServlet extends HttpServlet {

  // GET 요청 처리
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    System.out.println("HelloServlet:GET 호출됨");
  }
  
  
}
