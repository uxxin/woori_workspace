package service.cloud;

//하위 부서에서 사용할 구현 클래스
public class CSVParser extends AbstractParser{
	
	public CSVParser() {
		super("csv"); //AbstractParser(String fileExtension) 생성자를 호출 
	}
	
	@Override
	public void parse(String filePath) throws ParserException {
		//파싱 전에 파일 확장자가 유효한지 검증
		validateFileExtension(filePath);
		
		//실제 CSV 파싱 로직 작성 부분
		System.out.println("CSV 파일 파싱 처리 수행.."+filePath);
	
	}
}
