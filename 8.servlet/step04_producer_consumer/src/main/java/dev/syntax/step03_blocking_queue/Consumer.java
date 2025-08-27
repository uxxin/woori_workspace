package dev.syntax.step02_custom_implement;

// 큐에서 꺼낸 데이터 소비 역할을 수행하는 클래스
public class Consumer implements Runnable {
    private final DataQueue dataQueue;

    public Consumer(DataQueue dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        //  TODO: 컨슈머 스레드가 수행할 작업
        consume();
    }

    private void consume() {
        for (int i = 0; i < dataQueue.getCapacity(); i++) {

            Message message = dataQueue.poll();
            consumeMessage(message);

            // 현실성을 위해 랜덤 시간동안 슬립
            ThreadUtil.sleep((long) (Math.random() * 100));
        }
    }

    private void consumeMessage(Message message) {
        // message가 null일 때 접근하면 NullPointer라
        if(message != null) {
            System.out.println(String.format("  [%s] 메시지 소비: %s%n",
                    Thread.currentThread().getName(), message.getData()));
        } else{
            System.out.println(String.format( "[%s] 큐가 비어있음 %n", Thread.currentThread().getName()));


        }

    }


}
