package dev.syntax.bank;

import dev.syntax.bank.model.BankTransaction;
import dev.syntax.bank.parser.BankDataParser;
import dev.syntax.bank.service.BankProcessor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BankStatementAnalyzerWithJar {

    private final BankDataParser csvParser;
    private final BankDataParser tsvParser;

    // 생성자 주입
    public BankStatementAnalyzerWithJar(@Qualifier("csvParser") BankDataParser csvParser,
                                        @Qualifier("tsvParser") BankDataParser tsvParser) {
        this.csvParser = csvParser;
        this.tsvParser = tsvParser;
    }

    public void analyze(String filename) {
        if (filename == null || filename.isEmpty()) {
            System.out.println("입출금 파일명을 인자로 입력하세요. 예: bank-data.csv 또는 bank-data.txt");
            return;
        }

        // 클래스패스에서 리소스 읽기
        try (InputStream in = BankStatementAnalyzerWithJar.class
                .getClassLoader()
                .getResourceAsStream(filename)) {

            if (in == null) {
                System.out.println("리소스에서 " + filename + "을(를) 찾을 수 없습니다.");
                return;
            }

            List<String> lines;
            try (BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
                lines = br.lines().collect(Collectors.toList());
            }

            if (lines.isEmpty()) {
                System.out.println("입출금 내역이 존재하지 않습니다.");
                return;
            }

            // 파일 확장자에 따라 Parser 선택
            BankDataParser parser = filename.endsWith(".csv") ? csvParser : tsvParser;

            List<BankTransaction> bankTransactions = parser.parse(lines);

            // Processor는 Analyzer 안에서 직접 생성 => TODO: DI로 바꾸기
            BankProcessor processor = new BankProcessor(bankTransactions);

            System.out.printf("총 입출금액은 %d원 입니다%n", processor.calculateTotalAmount());
            System.out.printf("2월의 입출금액은 %d원 입니다.%n", processor.calculateTotalInMonth(Month.FEBRUARY));

        } catch (IOException e) {
            System.out.println("입출금 파일을 읽는 중 오류가 발생했습니다.");
        }
    }
}
