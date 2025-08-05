package service.cloud;

public class Main {
	public static void main(String[] args) {
		
		// 참조변수의 타입을 인터페이스로
		Parser csvParser = new CSVParser();
		csvParser.parse("bank-data.csv");
		
		//csvParser.parse("bank-data.json");
	}
}
