package dev.syntax.step03_blocking_queue;

public class Main {
    public static void main(String[] args) {
        // 데이터 큐 생성
        DataQueue dataQueue = new DataQueue(3);
        // ㄴ 여기에 5보다 작은 값 넣던 큰 값 넣던 동일하게 작동함.

        // 프로듀서 객체 및 스레드 생성
        Producer producer = new Producer(dataQueue);
        // -> Runnable이기 때문에 스레드 객체 생성 후 전달
        Thread producerThread = new Thread(producer, "Producer");

        // 컨슈머 객체 및 스레드 생성
        Consumer consumer = new Consumer(dataQueue);
        Thread consumerThread = new Thread(consumer, "Consumer");

        // 프로듀서, 컨슈머 스레드 시작
        producerThread.start();
        consumerThread.start();

    }
}
