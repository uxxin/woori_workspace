package dev.syntax.step05_overloading;

public class User {
	String name;
	int age;
	Gender gender = Gender.NONE;
	
	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public User(String name, int age, Gender gender) {
		this.name = name;
		this.age = age;
		this.gender = gender;
	}

}
