package dev.aop;

import dev.aop.config.BeanConfig;
import dev.aop.controller.OwnerController;
import dev.aop.model.Owner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Java 파일 기반으로 Bean 설정 파일 구성(config/BeanConfig.java)
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);

        // OwnerController 빈 가져오기
        OwnerController controller
                = context.getBean(OwnerController.class);

        // controller.getOwners();

        Owner owner = new Owner(1, "gugu");
        controller.addOwner(owner);

    }
}
