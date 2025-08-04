package step02_practice_oop;

//Bus, subway를 추상화한 클래스
public class Transportation {
	int transportNumber;
	int numberOfPassengers;
	int sales;
	
	public void take(int fee) {
		sales += fee;
		numberOfPassengers++;
	}
	
	public void printInfo() {
		System.out.println(transportNumber+" 지하철의 승객은 총 "+numberOfPassengers+"명 입니다.");
	}
}
