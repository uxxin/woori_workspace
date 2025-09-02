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
import dev.mouse.util.MouseModelAndView;

public class MouseRegisterFormController implements MouseController {
	
	  private MouseDataAccessor mouseDAO;

	  
	
	  public MouseRegisterFormController(MouseDataAccessor mouseDAO) {
		this.mouseDAO = new MouseDataAccessor();
	}



	@Override
	  public MouseModelAndView process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    
	    // MouseList 조회 로직에 대한 부분만 작성 가능
	    List<Mouse> mice = mouseDAO.findAll();
	    
	    String url = "/WEB-INF/mouseRegister.jsp";
	    
	    MouseModelAndView modelAndView = new MouseModelAndView();
	    modelAndView.setViewPath(url);
	    modelAndView.addObject("mouseRegister", mice);
	    
	    return modelAndView;
	    
	  }
	  }

//	@Override
//	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		StringBuilder responseHTML = new StringBuilder();
//
//		responseHTML.append("<html>");
//		responseHTML.append("	<head>");
//		responseHTML.append("		<meta charset=\"UTF-8\"/>");
//		responseHTML.append("		<title>Mouse 등록 페이지</title>");
//		responseHTML.append("	</head>");
//		responseHTML.append("		<body>");
//		
//		final String URL = "\"mouse-api/mouseInsert\"";
//		final String HTTP_METHOD = "\"POST\""; 
//		
//		String formAttribute = String.format("action=%s method=%s", URL, HTTP_METHOD);
//		responseHTML.append("<h1>Mouse 등록 페이지</h1>");
//		responseHTML.append("			<form " + formAttribute + ">");
//		
//		String nameInputAttribute = "type=\"text\" name=\"name\"";
//		responseHTML.append("			name: <input " + nameInputAttribute + "><br/>");
//		
//		String countryInputAttribute = "type=\"text\" name=\"country\"";
//		responseHTML.append("			country: <input " + countryInputAttribute + "><br/>");
//		
//		String addressInputAttribute = "type=\"text\" name=\"address\"";
//		responseHTML.append("			address: <input " + addressInputAttribute + "><br/>");
//		
//		String buttonAttribute = "type=\"submit\"";
//		responseHTML.append("			<button " + buttonAttribute + ">등록</button><br/>");
//		responseHTML.append("		</body>");
//
//		response.setStatus(200);
//		response.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html");
//
//		PrintWriter writer = response.getWriter();
//		writer.write(responseHTML.toString());
//	}
	
	
}
