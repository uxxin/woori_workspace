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


public class User {
	
	private String username;
	private String password;
	
	public String getUserName() {
		return this.username;
	}
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public void changePassword(String oldPassword, String newPassword) {
		
		if( this.password.equals(oldPassword) ) {
			this.password = newPassword;
			System.out.println("비밀번호가 변경되었습니다.");
		}
		else {
			System.out.println("기존 비밀번호와 일치하지 않습니다.");
		}
	}
	
	public void login(String inputPassword) {
		if(inputPassword == password) {
			System.out.println("로그인 성공");
		}
		else {
			System.out.println("로그인 실패");
		}
		
	}

}
