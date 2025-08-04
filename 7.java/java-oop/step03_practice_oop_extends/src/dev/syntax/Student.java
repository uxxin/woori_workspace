package dev.syntax;

/**
 * 클래스 Summary
1. 학생(Student) 클래스

1-1. 속성 Summary
이름 - String name
잔액 - int balance;

1-2. 역할 Summary
버스 탑승 - takeBus(Bus bus)

잔액 확인 - printInfo()
출력 형식: "name 님의 남은 잔액은 balance 입니다."
 */


public class Student {

	
	// alt+shift+s => generate constructors using Fields
	public Student(String name, int balance) {
		super();
		this.name = name;
		this.balance = balance;
	}

	String name;
	int balance;
	
	/*
	 * public void takeBus(Bus bus) { bus.take(balance); balance -= 1000; // 자신의
	 * 잔액에서 버스비 1000원 차감 }
	 * 
	 * public void takeSubway(Subway subway) { subway.take(balance); balance -=2000;
	 * }
	 */
	
	public void take(Transportation transport) {
		transport.take(1000);
		
		balance -= 1000;
	}
	
	void printInfo() {
		System.out.println(name+ "님의 남은 잔액은 "+balance+"입니다.");
	}
}
