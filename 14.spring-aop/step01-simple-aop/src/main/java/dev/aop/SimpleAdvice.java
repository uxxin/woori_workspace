package dev.aop;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

// 이후에 작성할 LoggingAspect와 유사한 역할을 수행하는 클래스
public class SimpleAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

    Logger logger = Logger.getLogger("SimpleAdvice");
    /**
     * MethodBeforeAdvice.java
     * 대상 메서드(target),
     * 여기서는 UserController가 호출되기 전에 실행할 내용을 작성할 수 있도록 제공하는 클래스
     */
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        logger.info("UserController.getUser() 호출 전");
    }

    // TODO: AfterReturningAdvice 인터페이스를 구현하여 메서드 호출 이후 동작하도록 작성
        // 파라미터에 전달되는 Method라는 타입 메서드를 활용하여 반환값(List<User>)을 출력
    @Override
    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
        logger.info("UserController.getUser() 호출 후");
        // 호출된 메서드의 이름이 getUsers인 경우,
        if(method.getName().equals("getUsers")){ // method.getName() - 런타임 시점의 메서드 이름을 추출
            System.out.println(returnValue);
        }
    }


}
