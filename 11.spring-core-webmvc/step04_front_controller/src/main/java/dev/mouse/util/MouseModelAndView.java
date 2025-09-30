package dev.mouse.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 개별 컨트롤러가 수행 중인 포워드/리다이렉트 등의 처리를 수행하는 래퍼(Wrapper) 클래스
public class MouseModelAndView {

  // Model 객체를 담을 필드
  private Map<String, Object> model;
  // ex. req.setAttribute("mouseList", mice);를 간이로 표현한 필드

  // View 페이지 경로
  private String viewPath;
  // ex. "/WEB-INF/mouseList.jsp"

  private boolean isRedirect; // true일 경우 리다이렉트, false - 포워드

  // 기본 생성자
  public MouseModelAndView() {
    this.model = new HashMap<>();
  }

  // 특정 키에 대한 모델 데이터를 추가하는 메서드
  public void addObject(String key, Object value) {
    this.model.put(key, value);
  }

  // 모델 데이터를 반환하는 메서드
  public Map<String, Object> getModel() {
    return model;
  }

  // 특정 키에 대한 모델 데이터를 반환하는 메서드
  public Object getObject(String key) {
    return model.get(key);
  }

  // View 경로의 Getter 메서드
  public String getViewPath() {
    return viewPath;
  }

  // View 경로의 Setter 메서드
  public void setViewPath(String viewPath) {
    this.viewPath = viewPath;
  }

  public boolean isRedirect() {
    return isRedirect;
  }

  public void setRedirect(boolean isRedirect) {
    this.isRedirect = isRedirect;
  }

  private List<String> getAllKeys() {
    // keySet()을 사용하여 모든 키를 Set으로 가져온 후, 이를 List로 변환
    Set<String> keys = model.keySet();
    return new ArrayList<>(keys);
  }

  public void redirect(HttpServletResponse response) throws IOException {
    response.sendRedirect(viewPath);
  }

  public void forward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    // 모든 키에 해당하는 value들을 setAttribute에 바인딩
    List<String> keys = getAllKeys();

    for (String key : keys) {
      request.setAttribute(key, model.get(key));
    }

    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response);
  }
}
