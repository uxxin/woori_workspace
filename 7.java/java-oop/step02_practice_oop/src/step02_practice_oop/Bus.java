package step02_practice_oop;

/**
 * 버스 클래스

2-1. 속성 Summary
버스 번호 - int busNumber
버스 승객 수 - int numberOfPassengers;
수익금 - int sales

2-1. 역할 Summary
버스 손님 태우기 - take(int sales)
-> 요금 수령, 손님 숫자 증가

버스 승객 수 확인 - printInfo()
출력 형식: busNumber 버스의 승객은 총 numberOfPassengers 명 입니다.
 */

public class Bus {
	
	
	int busNumber;
	int numberOfPassengers;
	int sales;
	
	
	
	public Bus(int busNumber) {
		// 승객 수는 처음에 초기화할 수 없음
		this.busNumber = busNumber;
	}

	void take(int fee) {
		sales += fee;
		numberOfPassengers++;
	}
	
	void printInfo() {
		System.out.println(busNumber+" 버스의 승객은 총 "+numberOfPassengers+"명 입니다.");
	}

}
