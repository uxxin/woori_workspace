package step02_practice_oop;

public class Subway {
	int subwayNumber;
	int numberOfPassengers;
	int sales;
	
	
	
	public Subway(int subwayNumber) {
		super();
		this.subwayNumber = subwayNumber;
	}

	void take(int fee) {
		sales += fee;
		numberOfPassengers++;
	}
	
	void printInfo() {
		System.out.println(subwayNumber+" 지하철의 승객은 총 "+numberOfPassengers+"명 입니다.");
	}
}
