package dev.syntax.step05_overriding;

public class Main {
	public static void main(String[] args) {
		User user = new User("이정복", "1234");
		
		user.printage();
		user.printEmail();
		
		System.out.println(user);
		System.out.println(user.toString());
		
		
	}
}	
