package dev.syntax.step05_overriding;

public class User {
	  private String nickname;
	  private String password;
	  private String email;
	  private int age;
	  
	  User(String name, String password){
	    this.nickname = name;
	    this.password = password;
	  }
	  
	  public void update(String email) {
	    this.email = email;
	  }
	  
	  public void update(int age) {
	    this.age = age;
	  }
	  
	  public void printEmail() {
	    System.out.println(email);
	  }
	  
	  public void printage() {
	    System.out.println(age);
	  }

	  // 주석 처리하면 원래 있던 toString으로 출력되고 이거 주석 해제하면 이대로 format 이 형성됨. 
	@Override
	public String toString() {
		return "User [nickname=" + nickname + ", password=" + password + ", email=" + email + ", age=" + age + "]";
	}
	  
	  
	}
