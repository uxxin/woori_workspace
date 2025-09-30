package dev.mvc.spring.step01_process_request;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

// 사용자의 요청을 받아서 처리하는 컨트롤러
@Component(value = "/step01-first-controller")
// -> FirstController 클래스를 URL 경로를 지정하고, 스프링에서 관리할 빈으로 등록
public class FirstController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("step01-first-controller 호출됨");
		return null;
	}
	
}
