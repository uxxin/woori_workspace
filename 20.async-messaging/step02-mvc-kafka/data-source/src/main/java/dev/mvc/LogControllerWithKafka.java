package dev.mvc;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// data-soruce 서버
// 카프카 연결용 코드

// 로그 서버로 직접 전송이 아닌 카프카 브로커를 통해 처리
@RestController
public class LogControllerWithKafka {

    // Kafka에 로그 메시지를 적재할 때 활용할 클라이언트 API
    private final KafkaTemplate<String, String> kafkaTemplate;

    public LogControllerWithKafka(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // 1.Kafka를 통해 과부하 전파 문제가 해소되는지 확인하기 위한 엔드포인트
    @PostMapping("/logs-with-kafka")
    public String sendLog(@RequestBody String log) {
        // 카프카 브로커에 토픽(log 메시지) 적재
        kafkaTemplate.send("logs-topic", log);

        System.out.println("메시지가 큐에 적재됨(queued)");

        return "success!";
    }

}
