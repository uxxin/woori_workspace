package service.cloud;

public interface Parser {
	/**
	 * 파일 경로를 전달받아 파싱 작업을 수행
	 * @param filePath - 파일 경로(ex. "bank-data.csv"
	 * @throws ParserException - 파일 확장자가 다른 경우
	 */
	void parse(String filePath) throws ParserException;
}
