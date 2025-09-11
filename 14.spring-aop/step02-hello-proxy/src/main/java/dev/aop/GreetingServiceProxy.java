package dev.aop;

// GreetingService 인터페이스를 구현한 Proxy 객체

// Target 객체인 GreetingServiceImpl이 동작하기 전에 (가로채서)
// 자신의 로직을 수행하는 역할
public class GreetingServiceProxy implements GreetingService {

    // Target 구현체
    private GreetingServiceImpl targetService;
    
    @Override
    public void sayHello() {
        // 실제 서비스의 생성을 지연시킴(Lazy Initialization)
        // sayHello()를 실제로 호출하기 전까지
        // 실제 서비스의 생성을 지연시킴(Lazy Initialization)
        if(targetService==null){
            targetService=new GreetingServiceImpl();
        }
        // Proxy만의 수행하고 싶은 로직..
        System.out.println("GreetingServiceImpl로의 접근을 가로챔");

        // 실제 서비스인 Target에 작업을 위임
        targetService.sayHello();
    }
}
