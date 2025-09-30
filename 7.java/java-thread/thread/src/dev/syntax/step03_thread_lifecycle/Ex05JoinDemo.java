package dev.syntax.step03_thread_lifecycle;

public class Ex05JoinDemo {

  public static void main(String[] args) {
    Thread thread = new Thread(() -> {
      try {
        System.out.println("스레드가 5초 동안 동작");
        Thread.sleep(5000);
        System.out.println("스레드 동작 완료");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    thread.start();

    try {
      //Thread.sleep(1000); 
      Thread.currentThread().isAlive(); // join을 걸기 전 메인 스레드의 상태는 살아 있음(true)
      System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다.");
      
      thread.join(); // 블로킹 메서드
      /*
             * join(0) 내부 브레이크포인트 걸기
             * while (isAlive()) {
                 wait(0);
               }
             */
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }

    System.out.println("메인 스레드가 계속 진행합니다");

  }
}
