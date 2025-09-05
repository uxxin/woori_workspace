package dev.boot;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
        //linkTo: 가짜 호출 정보를 바탕으로 URI를 생성, 해당 메서드로 연결되는 링크를 생성
        // methodOn: GreetingController의 greeting()를 호출하는 것처럼 흉내냄
        // 어떤 메서드를 호출하고 싶은지 명시
        // .withSelRel(): 만들어진 URI를 바탕으로 self 프로퍼티를 추가
        // add(): 링크 객체를 현재 응답 모델인 RepresentationModel에 추가
        

        return new ResponseEntity<>(greeting, HttpStatus.OK);
    }
}