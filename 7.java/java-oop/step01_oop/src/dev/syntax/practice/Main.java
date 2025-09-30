package dev.syntax.practice;

public class Main {
	public static void main(String[] args) {
		Whale mobidik = new Whale();
		mobidik.age = 3;
		System.out.println(mobidik.age);
		mobidik.printAge();
		mobidik.baby = 10;
		System.out.println(mobidik.baby);
		mobidik.printBaby();
	}

}
