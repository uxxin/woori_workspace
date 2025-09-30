package dev.syntax.step03_blocking_queue;

// 생산, 소비할 데이터를 추상화한 클래스
public class Message {

    private String data;

    public Message(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "data='" + data + '\'' +
                '}';
    }
}
