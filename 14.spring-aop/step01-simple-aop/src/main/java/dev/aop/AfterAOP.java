package dev.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AfterAOP {
    public static void main(String[] args) {
        ApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");
        // AOP 적용 후
        /*
            AOP를 적용하려면, 출력 로직(로깅)을 AOP를 통해 동작할 수 있도록 저굥
            -> getUsers() 호출 전/후에 로깅 로직이 실행되어야 함
                전/후 처리를 프록시(Proxy)라는 객체가 수행해줌.

            1. UserController - 실제 메서드 호출을 수행하는 대상 객체(Target)
            2. 프록시 객체 - Target의 호출 전/후에 동작하는 역할
                - 프록시 객체 생성 방법?
                    여기서는 스프링의 내부 클래스인 ProxyFactoryBean을 활용
            3. 프록시 객체가 어느 시점에 어떻게 동작할지를 설정하는 코드
         */
        UserController controller = (UserController) context.getBean("proxyFactoryBean");
        //System.out.println("controller = " + controller);


        // getUsers() API 호출
        controller.getUsers();
        // getUsers()가 호출될 때, 타겟 메서드가 호출되기 전에
        // 프록시가 SimpleAdvice의 작업을 수행하고,
        // 타켓 객체의 메서드인 getUsers()를 호출함.
    }
}
