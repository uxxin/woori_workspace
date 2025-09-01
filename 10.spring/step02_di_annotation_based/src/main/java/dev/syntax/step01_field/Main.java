package dev.syntax.step01_field;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {

        var context
                = new ClassPathXmlApplicationContext("annotation-config-field.xml");

        TapeReader reader = context.getBean(TapeReader.class);

        reader.test();
    }
}
