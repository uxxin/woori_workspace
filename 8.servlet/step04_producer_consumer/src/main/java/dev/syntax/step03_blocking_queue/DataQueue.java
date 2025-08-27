package dev.syntax.step02_custom_implement;

import java.util.LinkedList;
import java.util.Queue;

// 데이터(Message)를 적재할 수 있는 고정된 사이즈(Bounded-buffer)의 큐를 가진
// 래퍼(Wrapper) 클래스
public class DataQueue {
    private final Queue<Message> queue = new LinkedList<>();
    private final int Capacity;

    public DataQueue(int Capacity) {
        this.Capacity = Capacity;
    }

    /**
     * synchronized를 사용해 producer consumer 중 하나만 스레드에 접근 할 수 있게 함.
     * -> 동시에 큐가 조작되지 않도록
     */
    // 프로듀서가 큐에 메시지를 적재
    public synchronized void add(Message message) {

        // 큐가 차 있으면 기다리기
        while (queue.size() == Capacity) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // 큐가 꽉 차지 않았으면 메세지 추가
        queue.add(message);
        notifyAll(); // 큐가 비어 기다리던 consumer 깨우기 
    }

    // 컨슈머가 큐에서 메시지를 꺼내 소비
    public synchronized Message poll() {
        // 큐가 비어있으면 기다리기
        while(queue.size() == 0) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        
        //큐가 비지 않았으면 꺼내기 
        System.out.print(String.format("현재 큐 상태: %s%n", getQueueElements()));
        Message poppedMessage = queue.poll();
        notifyAll(); //큐가 꽉차 기다리던 producer 깨우기
        return poppedMessage;
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    // 큐 상태 확인
    public synchronized String getQueueElements() {
        return queue.toString();
    }

    public int getCapacity() {
        return Capacity;
    }

}
