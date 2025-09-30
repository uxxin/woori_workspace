package dev.syntax.step02_multiple_client;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

// Runnable 인터페이스를 구현하여 스레드에서 실행될 수 있도록 함
public class ClientHandler_my implements Runnable {

    private final Socket clientSocket; // 각 스레드가 담당할 클라이언트 소켓

    // 생성자를 통해 클라이언트 소켓을 전달받음
    public ClientHandler_my(Socket socket) {
        this.clientSocket = socket;
    }

    @Override
    public void run() {
        try (
                // try-with-resources 구문을 사용하면 코드가 끝나거나 예외 발생 시 자동으로 자원을 해제해줌
                InputStream in = clientSocket.getInputStream();
                OutputStream out = clientSocket.getOutputStream()
        ) {
            int request;
            // 클라이언트가 스트림을 닫으면(-1을 반환) 루프를 종료하여 스레드를 정상적으로 끝냄
            while ((request = in.read()) != -1) {
                out.write(request); // 읽은 값을 클라이언트에게 바로 반환(echo)
                out.flush(); // 버퍼에 남아있는 데이터를 즉시 전송
            }
        } catch (Exception e) {
            System.out.println(clientSocket.getInetAddress().getHostAddress() + " 클라이언트와 통신 중 오류 발생: " + e.getMessage());
        } finally {
            try {
                // 통신이 끝나면 반드시 소켓을 닫아 리소스를 해제
                clientSocket.close();
                System.out.println(clientSocket.getInetAddress().getHostAddress() + " 클라이언트 연결이 종료되었습니다.");
            } catch (Exception e) {
                System.out.println("소켓을 닫는 중 오류 발생: " + e.getMessage());
            }
        }
    }
}