package dev.mvc.spring;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller // @Component와 동일
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	public HomeController() {
		super();
		System.out.println("called");
		// TODO Auto-generated constructor stub
	}

	// localhost:8080/spring까지가 루트 주소
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home"; // home.jsp 파일을 응답 처리
		// 실제 경로를 찾아서 해당 파일을 응답하려면
		// 원래 경로는 WEB-INF/views/home.jsp
		// 개발자는 파일 이름만 작성할 수 있게 편의성으로 제공해주는 클래스
	}
	
}
