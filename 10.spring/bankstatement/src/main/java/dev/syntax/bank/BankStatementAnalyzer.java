package dev.syntax.bank;
import dev.syntax.bank.model.BankTransaction;
import dev.syntax.bank.parser.BankDataCSVParser;
import dev.syntax.bank.parser.BankDataParser;
import dev.syntax.bank.parser.BankDataTSVParser;
import dev.syntax.bank.service.BankProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

public class BankStatementAnalyzer {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("입출금 파일명을 인자로 입력하세요. 예: bank-data.csv 또는 bank-data.txt");
            return;
        }

        String filename = args[0]; // 예: bank-data.csv, bank-data.txt

        // 클래스패스(resources)에서 읽기
        try (InputStream in = BankStatementAnalyzer.class
                .getClassLoader()
                .getResourceAsStream(filename)) {

            if (in == null) {
                System.out.println("리소스에서 " + filename + "을(를) 찾지 못했습니다.");
                return;
            }

            List<String> lines;
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(in, StandardCharsets.UTF_8))) {
                lines = br.lines().collect(Collectors.toList());
            }

            if (lines.isEmpty()) {
                System.out.println("입출금 내역이 존재하지 않습니다.");
                return;
            }

            // 확장자에 따라 파서 선택
            BankDataParser parser = getParserByFilename(filename);

            List<BankTransaction> bankTransactions = parser.parse(lines);

            BankProcessor processor = new BankProcessor(bankTransactions);

            System.out.printf("총 입출금액은 %d원 입니다%n", processor.calculateTotalAmount());
            System.out.printf("2월의 입출금액은 %d원 입니다.%n", processor.calculateTotalInMonth(Month.FEBRUARY));

        } catch (IOException e) {
            System.out.println("입출금 파일을 읽는 중 오류가 발생했습니다.");
        }
    }

    private static BankDataParser getParserByFilename(String filename) {
        if (filename.endsWith(".txt")) {
            return new BankDataTSVParser();
        } else if (filename.endsWith(".csv")) {
            return new BankDataCSVParser();
        } else {
            throw new IllegalArgumentException("지원하지 않는 파일 형식입니다. (.csv 또는 .txt만 허용)");
        }
    }
}