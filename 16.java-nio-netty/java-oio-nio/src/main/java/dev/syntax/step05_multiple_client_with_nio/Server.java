package dev.syntax.step05_multiple_client_with_nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {
    public static void main(String[] args) {
        // 서버의 기본 포트
        final int DEFAULT_PORT = 5555;

        try {
            // Selector 생성
            Selector selector = Selector.open();

            // TCP 통신을 위한 ServerSocketChannel을 생성
            ServerSocketChannel serverSocketChannel
                    = ServerSocketChannel.open();

            // Selector와 ServerSocketChannel이 성공적으로 열렸는지 확인
            if (serverSocketChannel.isOpen() && selector.isOpen()) {
                // 생성한 논블로킹 소켓인 ServerSocketChannel을 논블로킹 모드로 설정
                serverSocketChannel.configureBlocking(false);

                // 클라이언트의 연결을 대기할 포트 번호 지정(생성)
                InetSocketAddress inetSocketAddress
                        = new InetSocketAddress(DEFAULT_PORT); // 5555번 포트에서 대기

                // 생성한 포트를 서버 소켓 채널에 바인딩
                serverSocketChannel.bind(inetSocketAddress);
                // -> serverSocketChannel 객체가 지정된 포트로부터 클라이언트의 연결을 받을 수 있게 됨

                // 현재 생성한 서버 소켓 채널(serverSocketChannel)을 Selector에 등록
                // SelectionKey.OP_ACCEPT - 셀렉터가 감지할 이벤트들 중에서 서버가 클라이언트의 연결 요청을 수락하는 이벤트
                // 두 번째 인자는 채널에서 발생하는 이벤트들 중 셀렉터를 통해 확인하고자(알림받고자)하는 이벤트의 종류를 전달할 때 사용
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                System.out.println("서버는 클라이언트의 접속 대기 중..");

                while (true) {
                    // 클라이언트의 유입 이벤트가 올 때까지 대기(기다림)
                    selector.select();

                    // 클라이언트가 전송한 이벤트가 무엇인지 확인하기 위해 SelectionKey 목록 조회
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> keys = selectionKeys.iterator();

                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();

                        // 현재 키에 해당하는 채널에서 조회된 IO 이벤트의 종류가 새로운 소켓 커넥션 연결 요청인지 확인
                        if (key.isAcceptable()) { // 현재 키에 담긴 이벤트가 새로운 소켓 연결을 수락할 수 있는지?
                            // 소켓 연결 작업 수행 로직 작성 부분

                            // TODO: 구현 예정
                            acceptOperation(key, selector);
                        }
//                        else (key.isReadable()) {
//                            // TODO: IO 이벤트가 읽기 이벤트인지?
//                            // readOperation(key, selector); // 읽기 작업과 관련된 로직 처리
//                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*
        키의 채널(여기서는 ServerSocketChannel)이
        새로운 (클라이언트의) 소켓 연결을 수락(Accept)할 수 있는 경우,

        처리할 수행 로직이 담긴 메서드
     */
    private static void acceptOperation(SelectionKey key, Selector selector) throws IOException {

        // 1. key에서 채널을 꺼냄

        // 클라이언트의 연결 요청 이벤트가 발생한 채널은 항상 ServerSocketChannel이기 때문에
        // 이벤트가 발생한 채널을 ServerSocketChannel로 다운 캐스팅
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        // 꺼낸 채널을 통해 클라이언트의 연결을 수락하고, 연결된 클라이언트의 SocketChannel 객체를 가져옴
        SocketChannel clientSocketChannel = serverSocketChannel.accept();
        // -> accept()는 연결된 클라이언트 소켓 객체를 반환함

        // 연결된 클라이언트의 소켓 모드를 논블로킹 모드로 설정
        clientSocketChannel.configureBlocking(false);
        System.out.println(clientSocketChannel.getRemoteAddress() + " 로 부터 클라이언트가 연결됨");
    }
}
