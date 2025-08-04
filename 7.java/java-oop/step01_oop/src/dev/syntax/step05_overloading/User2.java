package dev.syntax.step05_overloading;

public class User2 {
	  private String nickname;
	  private String password;
	  private String email;
	  private int age;
	  
	  User2(String name, String password){
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
	}