package dev.syntax.step03_thread_lifecycle;

/**
 * 스레드 생명주기 - RUNNABLE
 * 
 * RUNNABLE
 * - 실행 대기 상태(OS 스케줄러에 의해 CPU 자원을 사용하길 기다리는 상태)
 * - 실제 실행 중인 상태(RUNNING)
 * Java에서는 두 상태 모두 RUNNABLE로 통칭
 * 
 * 
 * RUNNABLE 상태 표현이 애매하지만, 
 * 스레드가 실행 중이거나 실행 간으한 상태 두 가지 모두를 의미
 * OS 스케줄러는 멀티 스레드로 동작하는 환경에서 일반적으로 각 스레드에게 고정된 시간을 할당해서
 * 실행 가능 상태(RUNNABLE)와 실행 중인 상태(RUNNING)을 오가도록 스케줄링
 * 
 * 스레드가 실제로 실행 상태(RUNNING)으로 전환되려면 현재 스레드가 반드시 실행 대기 상태를 걸쳐야 함.(RUNNABLE)
 */

public class Ex02Runnable {
	public static void main(String[] args) {
		//스레드 객체 생성
		Thread thread = new Thread(()->{
			System.out.println("생성된 스레드 ID: "+Thread.currentThread().getId());
		});
		
		thread.start();
		
		//스레드 객체의 상태 확인 - thread.getState()
		System.out.println(thread.getState()); //RUNNABLE
		
	}
}
