package dev.syntax;

//Bus, subway를 추상화한 클래스
public abstract class Transportation {
	int transportNumber;
	int numberOfPassengers;
	int sales;
	
	
	public Transportation(int transportNumber) {
		this.transportNumber = transportNumber;
	}

	public void take(int fee) {
		sales += fee;
		numberOfPassengers++;
	}
	
	public abstract void printInfo();
	
//	public void printInfo() {
//		System.out.println("운송수단 "+transportNumber+"번의 승객은 "+numberOfPassengers+"명 이고, 수입은 "+sales+"입니다.");
//	}
}
