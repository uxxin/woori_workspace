package dev.syntax.step01_single_client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

// 소켓 기반의 네트워크 프로그램 중 서버 역할을 수행하는 클래스
public class SimpleServer {
    public static void main(String[] args) throws Exception{
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("서버가 시작되었습니다. 클라이언트의 연결 대기중..");

        // 특정 연결된 클라이언트의 소켓을 받아옴
        Socket socket = serverSocket.accept();
        // System.out.println("socket = " + socket); // soutv

        System.out.println("클라이언트가 연결되었음"); // sout

        // 서버 - 클라이언트 간 입출력 용도의 스트림
        OutputStream out = socket.getOutputStream();
        InputStream in = socket.getInputStream();

        while (true) {
            int request = in.read(); // 인풋 스트림을 통해 클라이언트가 전송한 데이터 읽기
            out.write(request); // 서버는 읽은 값을 클라이언트에게 바로 반환(echo)
        }
    }
}
