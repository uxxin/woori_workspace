package dev.syntax.step03_constructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context
                = new ClassPathXmlApplicationContext("annotation-config-constructor.xml");

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
