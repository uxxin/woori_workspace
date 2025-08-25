package dev.syntax.step02_multiple_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        // 연결할 서버의 정보
        final String SIMPLE_SERVER_IP = "127.0.0.1";
        final int SERVER_PORT = 5555;

        try {
            Socket socket = new Socket(SIMPLE_SERVER_IP, SERVER_PORT);

            try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput); // 서버로 메시지 전송
                    System.out.println("서버로부터 수신: " + in.readLine()); // 서버로부터 에코 수신
                }
            }

        } catch (IOException e) {
            System.out.println("서버 연결 대기 중");
            throw new RuntimeException(e);
        }


    }
}
