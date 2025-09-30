package dev.mvc.spring.step02_resolving_view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

@Component(value = "/step02-first-controlller")
public class FirstResolvingViewController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ModelAndView mnv = new ModelAndView();
		mnv.setViewName("home"); // 렌더링할 .jsp 파일명
		// ->home.jsp로 렌더링되도록 지정
		
		return mnv;
	}
	
}
