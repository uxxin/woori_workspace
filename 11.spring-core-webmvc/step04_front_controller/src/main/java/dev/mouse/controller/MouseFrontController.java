package dev.mouse.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mouse.util.MouseModelAndView;

// SpringMVC의 DispatcherServlet을 간이로 구현해보는 클래스,
// 클라이언트의 모든 요청을 받아서 처리하는 단일 진입점인 유일한 서블릿
@WebServlet(urlPatterns = "/mouse-api/*")
public class MouseFrontController extends HttpServlet {
  /*
    TODO: 세부 요청 경로별 개별 컨트롤러를 맵핑하는 로직
    mouse-api/mice 경로일 경우, MouseListController 호출
    mouse-api/mice/add 경로일 경우, MouseInsertCtlr 호출
  */
  // String - 세부 URI 주소
  // MouseController - 개별 역할을 수행할 컨트롤러 객체들의 상위 인터페이스
  private Map<String, MouseController> controllerMap 
    = new HashMap();
  
  public MouseFrontController() {
    // MouseFrontController가 초기화될 때
    // 프론트 컨트롤러가 기억할 각 경로별 개별 컨트롤러 정보 초기화
    controllerMap.put("/mice", new MouseListController());
    // TODO: InsertController 추가 구현하기
    controllerMap.put("/mouseInsert", new MouseInsertController());
    
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    /*
     * 1. 요청 객체(req)에서 URI 값을 확인한 후, 
     * 어떤 컨트롤러가 요청을 수행해야할지 결정 후 분기
     * 
     * 2. 대부분의 컨트롤러에서 수행하는 공통 로직들을 분리하는 처리 로직
     * ex. 인코딩 처리, 컨텐츠 타입 설정 등..
     * 
     * 3. JSP에게 전달할 모델 객체 데이터를 넘겨주는 처리 로직
     * - 라우팅 로직(포워딩, 리다이렉트) 
     */
    
    // 2번 로직 - 인코딩 처리
    request.setCharacterEncoding("UTF-8");
    response.setCharacterEncoding("UTF-8");
    response.setContentType("text/html");
    
    // 1번 로직 - 요청 경로별 개별 컨트롤러로 분기
    
      // 1-1. 사용자의 요청 URI 값 확인 ex. lh:80/mouse-api/mice일 경우,
       String path = request.getPathInfo();
       System.out.println(path); // /mice
       
       // 1-2. URI별 특정 요청을 수행할 개별 컨트롤러 불러오기
       MouseController controller 
         = controllerMap.get(path); // ex) MouseListController
       
       // 1-3. 실제 개별 컨트롤러에게 요청을 처리하도록 process() 호출
       MouseModelAndView modelAndView = controller.process(request, response);
       
    // 3번 로직 - ModelAndView 객체를 활용하여 라우팅 처리
    if (modelAndView.isRedirect()) {
      modelAndView.redirect(response);
    } else {
      modelAndView.forward(request, response);
    }
  }
  
  
  
}
