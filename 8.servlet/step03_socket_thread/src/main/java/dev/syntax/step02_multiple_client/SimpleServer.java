package dev.syntax.step02_multiple_client;

import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("서버가 시작되었습니다. 클라이언트의 연결을 기다립니다...");

        // 무한 루프를 돌면서 클라이언트의 연결을 계속 받음
        while (true) {
            // accept()는 블로킹 메서드, 클라이언트가 연결 요청을 할 때까지 여기서 대기
            Socket socket = serverSocket.accept();
            System.out.println("새로운 클라이언트가 연결되었습니다: " + socket.getInetAddress().getHostAddress());

            // 연결된 클라이언트 각각에 대해 새로운 스레드를 생성하고 시작
            // ClientHandler는 Runnable 인터페이스를 구현한 클래스
            ClientHandler clientHandler = new ClientHandler(socket);
            new Thread(clientHandler).start();
        }
    }
}