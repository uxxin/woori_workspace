package dev.syntax.user_capsulation;

//요구사항
//
//User 클래스 캡슐화하기
//
//User 클래스는 회원 객체를 모델링한 클래스이다.
//
//필드 summary
//- username - 회원 아이디 
//- password - 비밀번호
//
//메서드 summary
//- setPassword(String oldPassword, String newPassword) - 비밀번호 변경
//- login(String inputPassword) - 로그인
//
//클래스 요구사항
//1. username 값은 외부에서 어디든지 접근하여 조회할 수 있어야 함
//2. username과 password는 객체를 생성할 때만 초기화할 수 있음
//3. 회원 객체 생성 이후 username은 절대 변경 불가 
//3. 비밀번호는 변경할 수 있는데, 비밀번호를 변경하려면 기존 패스워드와 새로운 패스워드를 전달받아야 함
//- 전달받은 패스워드가 기존 패스워드와 일치하면 새로운 패스워드로 비밀번호가 변경됨
//
//4. 회원은 비밀번호를 입력받아 로그인을 수행할 수 있음
//로그인 검증 과정 역시 전달받은 패스워드와 기존 패스워드가 일치하면 true를 반환하며 로그인이 성공함

public class Main {
	public static void main(String[] args) {
		// User 객체 생성
	    User yujin = new User("yujin", "dbwls1114!");
	    // 사용자 이름 조회
		System.out.println(yujin.getUserName());

	    // 비밀번호는 직접 접근 불가(컴파일 에러 확인하기)-마우스 올려서 확인하기 
//		System.out.println(yujin.password);
		// -> 외부에서 User 클래스의 password 필드는 보이지 않음 (접근 불가), private
	    
	    // 비밀번호 변경 시도(잘못된 비밀번호 전달 시)
	      // "기존 비밀번호가 일치하지 않습니다." 출력
		yujin.changePassword("dbwls!", "uxxinida1");

	    // 비밀번호 변경 시도(유효한 비밀번호 전달 시)
	      // "비밀번호가 변경되었습니다." 출력
		yujin.changePassword("dbwls1114!", "uxxinida!");
	    
	    // 로그인 검증
	      // true일 경우 로그인 성공, false일 경우 로그인 실패 출력
		yujin.login("uxxinida!");

	}
	
}
