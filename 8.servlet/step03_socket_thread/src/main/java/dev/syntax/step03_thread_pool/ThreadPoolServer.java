//package dev.syntax.step03_thread_pool;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.SocketException;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
/// / TODO: 개별 클라이언트의 요청마다 스레드를 생성해서 요청을 처리할 수 있는 서버
//public class ThreadPoolServer {
//    public static void main(String[] args) {
//        final int PORT = 5555; // 서버 포트
//        ExecutorService pool = Executors.newFixedThreadPool(3);
//
//        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
//            System.out.println("서버가 시작되었습니다.");
//
//            while (true) {
//                Socket clientSocket = serverSocket.accept();
//                System.out.println("연결된 클라이언트: " + clientSocket.getPort());
//
//                // 스레드 생성(start) 대신 스레드풀에 작업 제출(execute)
//                pool.execute(new ClientHandler(clientSocket));
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // (예제 단순화를 위해 pool.shutdown()은 생략)
//    }
//}
//
//// 클라이언트의 요청을 처리하는 핸들러 (Thread 상속 → Runnable 구현)
//// start()를 직접 호출하지 않고 풀에 Runnable만 던져주면
//// 풀 안에서 알아서 스레드를 골라 run()을 실행
//class ClientHandler implements Runnable {
//
//    private final Socket clientSocket;
//
//    public ClientHandler(Socket socket) {
//        this.clientSocket = socket;
//    }
//
//    @Override
//    public void run() {
//        System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId()); // 스레드 ID 출력
//
//        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
//
//            String inputLine;
//            while ((inputLine = in.readLine()) != null) {
//                System.out.println("클라이언트 스레드 ID: " + Thread.currentThread().getId()
//                        + " 클라이언트로부터 수신: " + inputLine);
//                out.println(inputLine); // 에코 응답
//            }
//        } catch (SocketException e) {
//            System.out.println("클라이언트가 연결을 종료했습니다.");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                clientSocket.close();
//                System.out.println("클라이언트 연결 종료: " + clientSocket.getInetAddress());
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}