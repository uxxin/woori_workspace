package javaParser;

public class AbstractParser implements Parser {
	//Parser 인터페이스를 구현하되, parse는 구현하지 않고 parser에서 구현
	//abstract 사용
	
	String fileExtension;
	
	public AbstractParser(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public boolean validateFileExtension(String filePath) {
		String inputfileExtension = filePath.split(".")[1];
		if(inputfileExtension.equals(fileExtension)) {
			return true; 
		}
		else {
			return false;
		}
	}
}


//CSVParser csvParser = new CSVParser();

//csv.parse("data.csv")