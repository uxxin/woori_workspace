package dev.syntax.step05_overloading;

public class LoggerMain {
	public static void main(String[] args) {
		Logger logger = new Logger();
		logger.log("서버 시작되었습니다.");
		logger.log(new RuntimeException("데이터베이스 연결 실패"));
		logger.log("AUTH", "사용자 로그인 성공");
		logger.log(new User2("양유진", "1234"));
	}
}
