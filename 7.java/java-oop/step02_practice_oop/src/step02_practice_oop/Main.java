package step02_practice_oop;

/**
 * 학생이 버스에 탑승해서 통근하는 프로그램
 * 
 * 추상화, 모델링 과정을 거쳐야 함
 * - 학생 클래스
 *     - 필드
 *       - 이름, 잔액(버스비)
 *     - 메서드
 *       - 버스에 탑승하기 위한 기능(메서드)
 *       - 남은 잔액 확인 기능
 *   
 * - 버스 클래스
 *     - 필드
 *       - 버스 번호(버스가 여러 대일 수 있기 때문에)
 *       - 버스의 승객 수
 *       - 버스 수익금
 *     - 메서드
 *       - 승객 탑승시키기
 *       - 승객 수 확인
 */
public class Main {

  public static void main(String[] args) {
    // 학생 인스턴스 생성
    Student yujin = new Student("유진", 3000);
    // 버스 인스턴스 생성
    Bus bus = new Bus(9711);
    // 학생이 버스에 탑승
    yujin.takeBus(bus);
    // 학생의 잔액 조회
    yujin.printInfo();
    
    // 버스의 승객 수 조회
    bus.printInfo();
  }

}