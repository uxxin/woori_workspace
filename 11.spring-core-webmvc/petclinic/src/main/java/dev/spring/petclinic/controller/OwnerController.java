package dev.spring.petclinic.controller;

/*
    Owner 도메인과 관련된 요청 처리를 수행하는 컨트롤러
 */

import dev.spring.petclinic.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 빈으로 등록
@RequestMapping("/owners") //lh:8080/owners로 요청 시 OwnerController로 호출
// -> 기본 경로 지정
@Slf4j // Slf4j로 로깅(Lombok)
public class OwnerController {
    // TODO: Owner 검색 페이지를 렌더링하는 핸들러
    @GetMapping("/find") // /owners/find 경로로 요청 시 동작할 핸들러 메서드
    public String findOwners(Model model) {

        log.info("GET:owners/find");

        // findOwners.html 8번 라인의 owner라는 모델 객체를 전달해야 파싱이 제대로 됨.

        // 비어있는 Owner 객체 생성 후,  Model 객체에 담아 findOwners.html에 전달
        Owner owner = Owner.builder().build();
        model.addAttribute("owner", owner);


        return "owners/findOwners";
        // templates(접두사 경로)/owners/findOwners.html
        // ->Thymeleaf 기준

    }
}
