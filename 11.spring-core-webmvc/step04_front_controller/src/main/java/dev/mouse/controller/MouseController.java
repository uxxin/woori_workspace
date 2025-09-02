package dev.mouse.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.mouse.util.MouseModelAndView;

/*
 * MouseInsert, List와 같은 개별 컨트롤러 객체들을 추상화한 인터페이스
 */
public interface MouseController {
  // 요청 경로별 세부 로직 처리를 수행할 추상 메서드
  // List는 Mouse 목록 조회 로직을 구현하는 부분이 될 것
  MouseModelAndView process(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException;
}
