package dev.syntax;

public class Subway extends Transportation {
	int subwayNumber;
	int numberOfPassengers;
	int sales;
	
	
	public Subway(int transportNumber) {
		super(transportNumber);
	}

	public void take(int fee) {
		sales += fee;
		numberOfPassengers++;
	}
	
	public void printInfo() {
		System.out.println(subwayNumber+" 지하철의 승객은 총 "+numberOfPassengers+"명 입니다.");
		System.out.println(sales);
	}
}
