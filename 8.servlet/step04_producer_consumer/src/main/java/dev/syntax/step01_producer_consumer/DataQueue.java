package dev.syntax.step01_producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

// 데이터(Message)를 적재할 수 있는 고정된 사이즈(Bounded-buffer)의 큐를 가진
// 래퍼(Wrapper) 클래스
public class DataQueue {
    private final Queue<Message> queue = new LinkedList<>();

    // 프로듀서가 큐에 메시지를 적재
    public void add(Message message) {
        queue.add(message);
    }

    // 컨슈머가 큐에서 메시지를 꺼내 소비
    public Message poll() {
        System.out.print(String.format("  현재 큐 상태: %s%n", getQueueElements()));
        Message poppedMessage = queue.poll();
        return poppedMessage;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // 큐 상태 확인
    public String getQueueElements() {
        return queue.toString();
    }

}
