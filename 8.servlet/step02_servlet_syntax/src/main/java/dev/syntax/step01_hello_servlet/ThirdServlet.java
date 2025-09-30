package dev.syntax.step01_hello_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/servlets/third-servlet")
public class ThirdServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // HTML 형태로 구조들을 잡아서 응답하려면?

    // HTML 형태로 응답하기 위한 처리
    response.setContentType("text/html"); // Content-Type: text/html이라고 응답 헤더에 명시

    // HTML 형태로 응답해야하기 위해서는??
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("<title>Hello Third World!</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>Hello World!</h1>");
    out.println("</body>");
    out.println("</html>");

    out.close();
  }

}