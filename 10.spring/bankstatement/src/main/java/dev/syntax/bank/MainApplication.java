package dev.syntax.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApplication {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("파일명을 인자로 입력하세요.");
            return;
        }

        ApplicationContext context =
                new AnnotationConfigApplicationContext("dev.syntax.bank");

        BankStatementAnalyzerWithJar analyzer =
                context.getBean(BankStatementAnalyzerWithJar.class);

        analyzer.analyze(args[0]);
    }
}
