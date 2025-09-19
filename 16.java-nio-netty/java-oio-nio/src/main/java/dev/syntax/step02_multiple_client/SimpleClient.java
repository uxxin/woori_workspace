package dev.syntax.step02_multiple_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleClient {
    public static void main(String[] args) {
        String serverAddress = "localhost"; // 서버 주소
        int port = 5555; // 서버 포트

        try (Socket socket = new Socket(serverAddress, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))) {

            System.out.println("서버에 연결되었습니다.");

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput); // 서버로 메시지 전송
                System.out.println("서버로부터 수신: " + in.readLine()); // 서버로부터 에코 수신
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
