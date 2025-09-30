package dev.syntax.step04_java_nio;

/*
    java.nio.channels.Selector

    Selector
    A multiplexor of SelectableChannel objects.
    -> 논블로킹 IO (멀티플렉서)를 관리하는 핵심 컴포넌트
 */

import java.io.IOException;
import java.nio.channels.Selector;

public class SelectorOverview {
    public static void main(String[] args) {
        try {
            Selector selector = Selector.open();

            System.out.println("selector = " + selector);
            // 각 OS 적용 셀렉터 구현체가 생성됨
            /*
                windows, selector = sun.nio.ch.WEPollSelctorImpl@cac736f
             */
            // Selector가 열려있는지 확인할 수 있는 메서드, 열려있을 경우 true를 반환
            // close() 메서드를 호출하기 전까지 셀렉터는 열린 채로 유지된
            System.out.println("selector.select() = " + selector.select());

            // 클라이언트로부터 유입 이벤트를 기다림
            selector.select(); // 사용자의 연결이 올 때까지 스레드는 해당 라인에서 블로킹됨
            // -> 프로그램이 종료되지 않음
        }
        catch(IOException e) {
            throw new RuntimeException(e);

        }
    }
}
