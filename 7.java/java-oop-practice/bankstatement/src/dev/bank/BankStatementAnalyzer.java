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
public class BankStatementAnalyzer {
	
	private static final String RESOURCES = "resources/";

	public static void main(String[] args) { // .txt만 쓰면 ["txt"], txt csv로 실행하면 csv는 args[1]로 받을 수 있음
		String fileExtension = args[0]; // 실행 옵션으로 값을 전달받기
		System.out.println(fileExtension);
		
		// 1. 입출금 내역 파일 읽어들이기
			final Path path = Paths.get(RESOURCES + "bank-data" + fileExtension);
			System.out.println(path);
			
			try {
				List<String> lines = Files.readAllLines(path);
				
				if (lines.isEmpty()) { // 입출금 내역 데이터가 비어있을 경우, ex) empty-bank-data.csv
					System.out.println("입출금 내역이 존재하지 않습니다.");
					return; // main()를 종료
				}
				
				// TODO: 조건식을 통해 txt, csv를 구분하여 확장자에 맞는 Parser 구현체가 동작하도록
				BankDataParser parser = new BankDataCSVParser();
				if (args[0].equals(".txt"))
					parser = new BankDataTSVParser();
				
				// 2. 전체 입출금 내역 조회
				List<BankTransaction> bankTransactions = parser.parse(lines);
				
				// 3. 입출금 내역 연산 처리를 위해 BankProcessor 객체 생성
				BankProcessor processor = new BankProcessor(bankTransactions);
				
				// 3. 콘솔로 입출금 내역 결과 출력
				String result = String.format("총 입출금액은 %d원 입니다", processor.calculateTotalAmount());
				System.out.println(result);
				
				String resultForMonth 
					= String.format("2월의 입출금액은 %d원 입니다.", processor.calculateTotalInMonth(Month.FEBRUARY));
				System.out.println(resultForMonth);
				
			} catch (IOException e) {
				System.out.println("입출금 파일 내역이 존재하지 않습니다.");
			}
	}

}

