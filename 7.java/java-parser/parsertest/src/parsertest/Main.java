package parsertest;

import service.cloud.CSVParser;

public class Main {
	public static void main(String[] args) {
		//CSVParser를 사용하고 싶은경우
		CSVParser csvParser = new CSVParser();
		csvParser.parse("bank-data.csv");
	}

}

