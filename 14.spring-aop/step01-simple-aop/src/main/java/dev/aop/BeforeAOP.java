package dev.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
    Spring 레거시 환경에서 AOP를 적용하는 간단한 예시
    - beans.xml이라는 xml 설정 파일로 구성
 */
public class BeforeAOP
{
    public static void main( String[] args )
    {
        ApplicationContext context  = new ClassPathXmlApplicationContext("beans.xml");

        // AOP 적용 전, 출력 로직(로깅)을 Controller에서 분리하기 어려움
        UserController controller = context.getBean(UserController.class);

        // getUsers() API 호출
        controller.getUsers();
    }
}
