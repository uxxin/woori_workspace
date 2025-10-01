package dev.mvc;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// logging-service
// data-source 서비스로부터 로그 데이터를 전달받아서 적재or저장하는 처리 수행
@RestController
@RequestMapping("/logs")
public class LogController {
    // 0.초기 서버 동작 테스트용
//    @PostMapping
//    public void receiveLog(@RequestBody String log) {
//        System.out.println("Received log: " + log);
//        // TODO: DB 저장 or 파일 적재
//    }

    // 1.과부하 전파 테스트
    // 인위적으로 지연 로직 추가
    @PostMapping
    public void receiveLog(@RequestBody String log) {
        try {
            Thread.sleep(1000); // 처리에 1초 걸리도록 설정
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("전달받은 로그: " + log);
    }
    // 2. 로그 서버가 다운되었을 경우 데이터가 유실됨
        // 테스트 명령어
        // 전송 도중에 로그 서버를 종료하기

}
