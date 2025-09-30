package dev.syntax;

public class Main {
	public static void main(String[] args) {
		//학생 인스턴스 생성
		Student yujin = new Student("유진", 3000);
		
		//버스 인스턴스 생성
		Transportation bus = new Bus(9711);
		
		yujin.take(bus);
		
		bus.printInfo();
	}
}
