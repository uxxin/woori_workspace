package dev.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import dev.bank.model.BankTransaction;
import dev.bank.parser.BankDataCSVParser;
import dev.bank.parser.BankDataParser;
import dev.bank.parser.BankDataTSVParser;
import dev.bank.service.BankProcessor;

public class BankStatementAnalyzerWithJar {
	
	public static void analyze(String filename) {
		if (filename == null) {
			System.out.println("입출금 파일명을 인자로 입력하세요. 예: bank-data.csv 또는 bank-data.txt");
			return;
		}

		final Path path = Paths.get(filename);

		try {
			List<String> lines = Files.readAllLines(path);

			if (lines.isEmpty()) {
				System.out.println("입출금 내역이 존재하지 않습니다.");
				return;
			}

			// 파일 확장자에 따라 파서 선택
			BankDataParser parser;
			if (filename.endsWith(".txt")) {
				parser = new BankDataTSVParser();
			} else if (filename.endsWith(".csv")) {
				parser = new BankDataCSVParser();
			} else {
				System.out.println("지원하지 않는 파일 형식입니다. .csv 또는 .txt 파일만 허용됩니다.");
				return;
			}

			List<BankTransaction> bankTransactions = parser.parse(lines);

			BankProcessor processor = new BankProcessor(bankTransactions);

			System.out.printf("총 입출금액은 %d원 입니다%n", processor.calculateTotalAmount());
			System.out.printf("2월의 입출금액은 %d원 입니다.%n", processor.calculateTotalInMonth(Month.FEBRUARY));

		} catch (IOException e) {
			System.out.println("입출금 파일 내역이 존재하지 않거나 읽을 수 없습니다.");
		}
	}
}
