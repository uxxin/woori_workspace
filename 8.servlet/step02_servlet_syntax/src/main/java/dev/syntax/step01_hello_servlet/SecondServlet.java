package dev.syntax.step01_hello_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 서블릿 객체를 서블릿 컨테이너에 등록하는 방법 2번째
 * - 1번째는 web.xml에 작성
 * - 2번째는 Annotation 방식
 * -> XML을 통한 관리 방식의 번거로움, 낮은 가독성
 * 추상화되고 직관적인 방식
 * 
 * @WebServlet 
 * ref. https://javaee.github.io/javaee-spec/javadocs/javax/servlet/annotation/WebServlet.html
 */

@WebServlet(urlPatterns = "/servlets/second-servlet")
public class SecondServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/plain; charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            out.print("Hello!"); // 화면에 Hello! 문자열을 출력
        }
  }
  
  
}



