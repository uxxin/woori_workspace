package dev.mouse.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mouse.dao.MouseDataAccessor;
import dev.mouse.model.Mouse;

/*
 * 전체 Mouse 목록 조회 요청에 대한 처리 수행
 */
@WebServlet("/mice")
public class MouseListServlet extends HttpServlet {

  private MouseDataAccessor mouseDAO;

  public MouseListServlet() {
    this.mouseDAO = new MouseDataAccessor();
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {

    // MouseDAO.java를 통해 DB에서 Mouse 데이터의 값을 조회해서 받아오기
    List<Mouse> mice = mouseDAO.findAll();

    // mice에 담긴 Mouse 데이터들이 mouseList.jsp로 전달되어서 화면에 렌더링되도록
      // jsp(Java Server Page)도 결국 서블릿으로 변환됨(Jasper 라이브러리)
    
    // mouseList라는 키값으로 mice라는 데이터를 요청 객체에 담음
    req.setAttribute("mouseList", mice); 
    
    // JSP 페이지로 포워드
    String url = "/WEB-INF/mouseList.jsp";
    
    RequestDispatcher dispatcher = req.getRequestDispatcher(url);
    dispatcher.forward(req, response);

    response.setStatus(200);
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    
  }

}
