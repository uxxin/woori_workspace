package dev.syntax.step02_multiple_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

// TODO: 개별 클라이언트의 요청마다 스레드를 생성해서 요청을 처리할 수 있는 서버
public class SimpleServer {
    public static void main(String[] args) {
        // 기본적인 서버연결을 위한 서버 소켓 객체 생성
        final int PORT = 5555; // 서버 포트
        // try-with-resources(Java 7~), 자동으로 자원 해제(with AutoCloseable 인터페이스)
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버가 시작되었습니다.");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("연결된 클라이언트: " + clientSocket.getPort());
                
                // TODO: 클라이언트와의 통신을 수행하는 스레드 생성 및 실행
                    // 각각의 개별 클라이언트 요청(clientSocket)을 수행할 스레드에게 소켓을 전달
                
                ClientHandler clientHandler = new ClientHandler(clientSocket); // 스레드 생성하면서 소켓 전달
                clientHandler.start(); // 스레드 작업 수행 시작(run() 호출됨)

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
            // 스레드 객체는 클라이언트로부터 받은 요청 처리 로직 수행
    }
}

// 별도의 파일로 분리하지 않고, 내부 클래스로 작성

// 클라이언트의 요청을 처리하는 핸들러 스레드
class ClientHandler extends Thread {

    // 특정 클라이언트의 요청에 대한 소켓 객체
    private Socket clientSocket;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        // 스레드가 수행할 작업 작성 부분
        System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId()); // 스레드 ID 출력

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId() + " 클라이언트로부터 수신: " + inputLine);
                out.println(inputLine); // 클라이언트에게 에코
            }
        } catch (SocketException e) {
            System.out.println("클라이언트가 연결을 종료했습니다.");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("클라이언트 연결 종료: " + clientSocket.getInetAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}