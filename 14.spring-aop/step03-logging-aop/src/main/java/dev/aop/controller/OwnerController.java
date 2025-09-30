package dev.aop.controller;

import dev.aop.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component // 빈으로 등록
@Slf4j // Lombok 기반 Slf4j 로깅
public class OwnerController {
    public void getOwners() {
        //log.info("-- getOwners() called");
        // 컨트롤러 처리 로직..
    }

    public void addOwner(Owner owner) {
        log.info("-- addOwner() called");
        // 컨트롤러 처리 로직..
    }

    // ...

}
