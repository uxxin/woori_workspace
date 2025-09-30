package dev.aop;

public class AppClient {
    public static void main(String[] args) {
        GreetingService greetingService = new GreetingServiceProxy();

        greetingService.sayHello();

        // Java의 문법만 활용해서(디자인 패턴) sayHello()를 호출하면
        /*
            출력 결과
            "GreetingService로의 접근을 가로챔" -> Proxy가 수행할 로직
            "GreetingService: Hello World!"" -> 타켓이 수행할 로직
         */
    }
}
