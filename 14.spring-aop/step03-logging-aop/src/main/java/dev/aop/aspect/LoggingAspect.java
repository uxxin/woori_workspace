package dev.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * LoggingAspect
 * 로깅에 대한 부가 기능을 바라보는 하나의 관점(Aspect) 클래스
 * 그 관점을 비즈니스 로직으로부터 별도의 파일로 분리한 것
 *
 * Aspect에서 활용되는 용어
 * Advice - 횡단 관심사를 구현하는 Aspect의 메서드
 *  - 여기서는 logBefore()를 의미
 *
 * JoinPoint - Advice를 적용할 메서드
 *
 *
 * Pointcut - 각 Advice에서 해당 Advice를 적용할 메서드를 구별시켜주는 표현식
 *  - 여기서는 logBefore()의 메서드 레벨에 작성된 @Before()내부의 표현식
 */
@Aspect // 스프링에게 이 클래스가 AOP에서 사용할 하나의 Aspect라고 명시
@Component // AOP 프레임워크가 이 Aspect를 사용할 수 있도록 하기 위해 빈으로 등록
@Slf4j // 컨트롤러에서 분리될 로깅 코드가 필요하기 때문에 추가
public class LoggingAspect {

    // Target 메서드 호출 전에 동작

    // **포인트컷 식 작성 예시
    // execution: 메서드의 이름 패턴을 활용하겠다는 의미
    // execution(* : 메서드의 반환타입은 무엇을 사용하든 관계 없음
    // dev.aop.controller: 해당 패키지의 하위에 속한 것만 적용되어야 함
    // *Controller: 클래스의 이름이 XxController로 끝나야 함
    // *( ): 메서드의 파라미터 역시 무엇을 사용하든 관계 없음
    // (..): Target 메서드가 파라미터를 받을 수도 있고, 안받을 수도 있음(상관없음)
    @Before(value = "execution(* dev.aop.controller.*Controller.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        // Proxy가 수행할 AOP만의 부가 로직 작성 부분(로깅 로직)
        // TODO: 호출된 메서드의 이름?을 로깅하려면?
        log.info("-- {}' {} 호출됨",
                joinPoint.getTarget().getClass().getSimpleName(), // 클래스 이름
                joinPoint.getSignature().getName() // 메서드 이름
        );
        // -> "OwnerController's getOwners() 호출됨" 처럼 출력됨

        // TODO: 메서드의 인수로 전달된 값도 로깅하고 싶을 경우?
        // TODO: (ex. addOwner(Owner owner))와 같은 상황에서 owner를 로깅
        // 만약 Controller 직접 작성했다면 log.info(owner)처럼 되어있을 코드
        // JoinPoint의 메서드를 활용
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            log.info("args[{}]", args[i]);
        }

    }
}
