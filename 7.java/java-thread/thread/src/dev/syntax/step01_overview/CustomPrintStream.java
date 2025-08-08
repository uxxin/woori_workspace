package dev.syntax.step01_overview;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * PrintStream 클래스를 상속한 서브 클래스
 */
public class CustomPrintStream extends PrintStream {

	public CustomPrintStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}
	
//	//출력문을 오버라이딩(println(boolean x))
	//서로 다른 스레드들이 동시에 println()에 접근하면서 스레드 안전성 문제(Thread safety issue)가 발생할 수 있음. 
//	public void println(boolean x) {
//		//System.out.println("내가 만든 출력문");
//		print(x);
//		newLine();
//	}
	
	public void println(boolean x) {
		// 25, 26번 라인을 한 시점에 하나의 스레드만 접근할 수 있는 임계 영역(Critical Section)으로 지정
		synchronized(this) {
			print(x);
			newLine();
		}
	}
	
	
	//한줄 개행시키는 내부 메서드(private)
	private void newLine() {
		print("\n");
	}
	
	@Override
	public void print(boolean x) {
	   super.print(x);
	}
}
