package dev.syntax.step02_setter;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context
                = new ClassPathXmlApplicationContext("annotation-config-setter.xml");

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
