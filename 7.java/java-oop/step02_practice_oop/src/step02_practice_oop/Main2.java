package step02_practice_oop;

public class Main2 {
	public static void main(String[] args) {
		Subway number2 = new Subway(2);
		
		Student yongjun = new Student("용준", 5000);
		
		yongjun.takeSubway(number2);
		
		yongjun.printInfo();
		number2.printInfo();
	}
}
