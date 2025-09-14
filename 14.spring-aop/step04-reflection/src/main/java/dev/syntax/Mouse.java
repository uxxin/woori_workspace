package dev.syntax;

public class Mouse {
	public int age;
	private String name;
	
	public Mouse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Mouse(int age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
