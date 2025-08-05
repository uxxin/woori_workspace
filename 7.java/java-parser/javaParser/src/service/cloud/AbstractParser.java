package service.cloud;

// 추상클래스 AbstractParser는 Parser 인터페이스를 구현했음.

/**
 * fileExtension을 field로 두고 CSVParser 등 각각에서 받아옴. 
 */
public abstract class AbstractParser implements Parser {
	protected String fileExtension;
	
	public AbstractParser(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	// 추상 클래스이기 때문에 구현이 강제되지 않음.
	// void parse(String filePath) throws ParserException;
	
	// 모든 파서 구현체들이 확장자가 유효한지 검증하는 메서드
	// -> 어떤 파서 구현체가 오더라도 확장자를 검증하는 로직은 공통이기 때문에 
	protected void validateFileExtension(String filePath) throws ParserException {
		if(!filePath.endsWith(fileExtension)) {
			throw new ParserException("유효하지 않은 파일 확장자입니다: "+filePath);
		}
	}
	
}
