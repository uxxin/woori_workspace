package service.cloud;

/**
 * Parser와 관련된 커스텀 예외
 */

public class ParserException extends RuntimeException {
  
  public ParserException(String message) {
    super(message);
  }
}
