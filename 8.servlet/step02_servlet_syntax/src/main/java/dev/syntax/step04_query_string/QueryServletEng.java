package dev.syntax.step04_query_string;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/query-test-eng")
public class QueryServletEng extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    resp.setCharacterEncoding("UTF-8");
    
    PrintWriter out = resp.getWriter();
    
    out.print("<h1>GET방식으로 요청되었음</h1>");
    
    // 사용자가 form에서 입력된 값을 추출해서 처리 - req.getParameter() 활용
    int id = Integer.parseInt(req.getParameter("id")); // input 태그의 name attribute값과 매칭
    // TODO: 나머지 값들 직접 추출
    String password = req.getParameter("pwd");
    String name = req.getParameter("name");
    String[] hobbies = req.getParameterValues("hobby");//쿼리스트링의 변수 형태가 배열타입일 때
    String gender = req.getParameter("gender");
    String country = req.getParameter("country");
    String introduction = req.getParameter("introduction");
    
    out.print("ID : " + id + " <br/> ");
    out.print("비밀번호 : " + password + " <br/> ");
    out.print("이름 : " + name + " <br/> ");
    out.print("취미 : ");
    for(int i = 0; i < hobbies.length; i++) {
       out.print(hobbies[i] + " ");
    }
    out.print("<br/>");
    out.print("성별 : " + gender + "<br/>");
    out.print("국가 : "+ country + "<br/>");
    out.print("소개 : " + introduction + "<br/>");
    out.close();
    
  }

  
}
