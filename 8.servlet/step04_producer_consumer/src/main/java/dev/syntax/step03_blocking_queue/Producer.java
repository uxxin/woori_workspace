package dev.syntax.step03_blocking_queue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

// 데이터(Message) 생산 역할을 수행하는 클래스(스레드)
public class Producer implements Runnable {
    private final DataQueue dataQueue;

    public Producer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        // TODO: 프로듀서가 수행할 작업
        produce(); // 데이터 생산, 큐에 적재
    }

    // 데이터 생산 후 큐에 적재
    private void produce() {
        for (int i = 0; i < dataQueue.getCapacity(); i++) {

            Message message = produceMessage();
            dataQueue.add(message);

            // 현실성을 위해 랜덤 시간 동안 슬립
            ThreadUtil.sleep((long) (Math.random() * 100));
        }
    }

    private Message produceMessage() {
        Message message = new Message(generateUniqueString());

        System.out.println(String.format("[%s] 메시지 생산: %s%n", Thread.currentThread().getName(), message.getData()));

        return message;
    }


    private String generateUniqueString() {
        // 현재 시간으로부터 고유한 문자열 생성
        SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
        String timePart = sdf.format(new Date());

        // 뒤에 1~2개의 랜덤 대문자 알파벳 추가
        Random rand = new Random();
        int randomLength = rand.nextInt(2) + 1;
        StringBuilder randomChars = new StringBuilder();

        for (int i = 0; i < randomLength; i++) {
            char randomChar = (char) ('A' + rand.nextInt(26)); // A-Z 범위의 대문자 랜덤 선택
            randomChars.append(randomChar);
        }

        return timePart + "-" + randomChars.toString();
    }
}
