package dev.syntax.step03_thread_pool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

// 스레드 풀을 통해 클라이언트의 요청 처리 수행하는 서버
public class SimpleServer {
    // 처리 가능한 스레드 수 2개
    private static final int MAX_THREADS = 2;
    // 대기 큐 사이즈 1개
    private static final int MAX_QUEUE_SIZE = 1;
    // -> 4번째 클라이언트의 요청은 연결이 종료되도록

    private static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(
                    MAX_THREADS, // CoreSize
                    MAX_THREADS, // MaxSize(Core == Max)
                    0L, TimeUnit.MILLISECONDS,
                    new ArrayBlockingQueue<>(MAX_QUEUE_SIZE) // 대기 큐
            );

    public static void main(String[] args) {
        int PORT = 5555;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("서버 시작, 포트: " + PORT);

            while (true) {

                // 특정 클라이언트 연결 수락
                Socket clientSocket = serverSocket.accept();
                String client
                        = clientSocket.getInetAddress().getHostAddress()
                        + ":" + clientSocket.getPort();
                System.out.println("[ACCEPT] " + client);

                // 스레드 풀에게 특정 클라이언트 소켓(이벤트, 작업) 전달
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                try {
                    threadPool.execute(clientHandler);
                    // 스레드 풀의 활성 스레드, 큐의 사이즈 등을 로깅
                    System.out.println("[ENQUEUED/STARTED] " + client +
                            " | 활성 스레드(Active)=" + threadPool.getActiveCount() +
                            " 대기 큐(Work queue)=" + threadPool.getQueue().size());
                } catch (RejectedExecutionException e) {
                    // 스레드 2개가 활성화되어 있고, 대기 큐(1)도 가득찬 경우,
                    System.out.println("[REJECTED] 큐 한도 초과, 클라이언트 연결 종료: " + client +
                            " | active=" + threadPool.getActiveCount() +
                            " queue=" + threadPool.getQueue().size());
                    close(clientSocket);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void close(Socket s) {
        try { s.close(); } catch (IOException ignored) {}
    }
}

class ClientHandler implements Runnable {
    private final Socket clientSocket;

    ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        String client = clientSocket.getInetAddress().getHostAddress() + ":" + clientSocket.getPort();
        System.out.println("[START] 스레드 작업 시작" + Thread.currentThread().getId() + " for " + client);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("[RECV] " + client + " -> " + line);
                out.println(line);
            }
        } catch (IOException e) {
            System.out.println("[CLOSED BY CLIENT] " + client);
        } finally {
            try { clientSocket.close(); } catch (IOException ignored) {}
            System.out.println("[END] " + client);
        }
    }
}
