package dev.syntax.step05_overloading;

public class Main {
	public static void main(String[] args) {
		User yujin = new User("newjeans", 24);
		System.out.println(yujin.gender);
		User sooyeon = new User("newjeans", 24, Gender.FEMALE);
		System.out.println(sooyeon.gender);
	}
	
}	
