package dev.syntax.totalPractice;

public class Main {
	public static void main(String[] args) {
		//오버라이딩, 오버로딩
		Face defaultFace = new Face();
		Face catFace = new Cat();
		Face rabbitFace = new Rabbit();
		
		defaultFace.printFace();
		defaultFace.printFace(false);
		catFace.printFace(true);
		rabbitFace.printFace();
		rabbitFace.printFace(true);
	}
}
