package dev.aop;

// 타겟 객체의 인터페이스
// 추가적으로 Proxy도 Target과 동일한 인터페이스르 구현할 예정
// -> 그래야 클라이언트 입장에서 Target을 호출하는 것처럼 보임.
public interface GreetingService {

    // 메서드 시그니처 정의
    void sayHello();
}
