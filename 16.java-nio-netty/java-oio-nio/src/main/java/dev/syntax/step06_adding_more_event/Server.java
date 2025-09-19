package dev.syntax.step06_adding_more_event;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * 현재 서버는 클라이언트의 연결 요청에 대한 이벤트만 처리할 수 있음
 *
 * TODO: 따라서 추가적으로 클라이언트가 전송한 데이터를 읽을 수 있는 처리(Read)
 * 읽은 데이터를 그대로 클라이언트에게 반환(Write)할 수 있는 처리
 */
public class Server {

    /*
        keepDataTrack
        클라이언트로부터 수신된 데이터를 추적, 관리하는 역할
        각 클라이언트의 채널을 의미하는 SocketChannel을 key로 가지며,
        해당 클라이언트로부터 수신된 데이터를 저장하는 리스트(List<byte[]>)를 값으로 갖는 HashMap 객체
        각 클라이언트가 보낸 메시지는 byte[] 형태로 리스트에 저장됨
     */
    private static Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();

    /*
        IO 작업 시 데이터의 임시 저장소 역할인 버퍼 생성
     */
    private static ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

    public static void main(String[] args) {
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

                // 클라이언트의 연결을 대기할 포트번호 지정(생성)
                InetSocketAddress inetSocketAddress
                        = new InetSocketAddress(DEFAULT_PORT);

                // 생성한 포트를 서버 소켓 채널에 바인딩
                serverSocketChannel.bind(inetSocketAddress);
                // -> serverSocketChannel 객체가 지정된 포트로부터 클라이언트의 연결을 받을 수 있게 됨

                // 현재 생성된 서버 소켓 채널(serverSocketChannel)을 Selector 객체에 등록
                // SelectionKey.OP_ACCEPT - 셀렉터가 감지할 이벤트들 중에서 서버가 클라이언트의 연결 요청 수락
                // 두 번째 인자는 채널에서 발생하는 이벤트들 중 셀렉터를 통해 확인하고자(알림받고자) 하는 이벤트의 종류를 전달할 때 사용
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                /*
                    SelectionKey
                    채널이 Selector로 등록될 때마다 채널은 java.nio.channels.SelectionKey 클래스의 인스턴스를 통해 표현됨
                    이 인스턴스를 SelectionKey라고 함
                    -> 서로 다른 채널에 속한 클라이언트들의 요청을 정렬하기 위해 셀렉터가 사용하는 헬퍼 객체

                    각 헬퍼(key)는 단 하나의 클라이언트 서브 리퀘스트로 표현되며,
                    클라이언트와 요청 유형(연결, 읽기, 쓰기 등)을 식별하는 정보가 들어있음

                    SelectionKey.OP_ACCEPT - 수락 가능, 연관된 클라이언트가 연결을 요청함
                    SelectionKey.OP_CONNECT - 연결 가능, 서버가 연결을 수락함
                    SelectionKey.OP_READ - 읽기 가능, 읽기 연산을 가리킴
                    SelectionKey.OP_WRITE - 쓰기 가능, 쓰기 연산을 가리킴
                 */


                System.out.println("서버는 클라이언트의 접속 대기 중..");
                while (true) {
                    selector.select(); // 클라이언트의 유입 이벤트가 올 때까지 대기(기다림)

                    // SelectionKey 목록 조회
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> keys = selectionKeys.iterator();

                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();

                        // 같은 키가 반복해서 오는 것을 막기 위해 처리한 키는 제거, IO 이벤트가 발생한 채널에서 동일한 이벤트가 감지되는 것을 방지하기 위함
                        keys.remove();

                        if (!key.isValid()) { // 키가 유효한지 확인, 키가 취소되거나 키의 채널이 닫혔거나 셀렉터가 닫혔다면 유효하지 않은 키
                            continue;
                        }

                        // 현재 키에 해당하는 채널에서 조회된 IO 이벤트의 종류가 새로운 소켓 커넥션 연결 요청인지 확인
                        if (key.isAcceptable()) { // 현재 키에 담긴 이벤트가 새로운 소켓 연결을 수락할 수 있는지?
                            // 소켓 연결 작업 수행 로직 작성 부분
                            acceptOperation(key, selector);
                        } else if (key.isReadable()) { // 조회된 IO 이벤트의 종류가 데이터 수신 요청인지 확인(키의 채널을 읽을 수 있는지 확인)
                            readOperation(key);
                        } else if (key.isWritable()) { // 조회된 IO 이벤트의 종류가 데이터 쓰기 요청인지 확인(키의 채널에 쓸 수 있는지 확인)
                            writeOperation(key);
                        }
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /*
        키의 채널(여기서는 ServerSocketChannel)이 새 소켓 연결을 수락할 수 있는 경우,
        처리할 수행 로직이 담긴 메서드
     */
    private static void acceptOperation(SelectionKey key, Selector selector) throws IOException {
        // 클라이언트의 연결 요청 이벤트가 발생한 채널은 항상 ServerSocketChannel이기 때문에
        // 이벤트가 발생한 채널을 ServerSocketChannel 타입으로 캐스팅
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

        // ServerSocketChannel을 활용하여 클라이언트의 연결을 수락하고, 연결된 클라이언트 SocketChannel 객체를 가져옴
        SocketChannel clientSocketChannel
                = serverSocketChannel.accept();// accept()는 연결된 클라이언트 소켓 객체를 반환함
        System.out.println("clientSocketChannel = " + clientSocketChannel);

        // 연결된 클라이언트 소켓 모드를 논블로킹 모드로 설정
        clientSocketChannel.configureBlocking(false);
        System.out.println(clientSocketChannel.getRemoteAddress() + " 로부터 클라이언트가 연결됨");

        // IO 처리를 위해 채널을 셀렉터에 등록
        keepDataTrack.put(clientSocketChannel, new ArrayList<byte[]>());
        // 클라이언트 채널에서 OP_READ가 발생하면 감지하여 처리할 수 있도록
        clientSocketChannel.register(selector, SelectionKey.OP_READ);
    }

    /*
        Key가 속한 채널의 데이터를 서버가 읽을 수 있는 경우 수행할 처리 로직
        → 클라이언트로부터 수신한 데이터를 읽어들임
     */
    private static void readOperation(SelectionKey key) {
        try {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            // 이전에 저장되었던 데이터를 지우고, 새로운 데이터를 받을 준비
            // 버퍼의 포지션을 0으로 설정, limit을 버퍼의 용량으로 설정하여 새로운 데이터를 쓸 수 있게 해줌
            buffer.clear();

            // numRead - 클라이언트로부터 읽은 데이터의 바이트 길이를 나타내는 임시 변수
            int numRead = -1;

            try {
                // 채널에 할당된 버퍼를 통해 바이트 값을 읽어들임, read()는 블로킹 방식으로 동작하기 때문에 클라이언트가 데이터를 보내기 전까지 호출한 스레드는 블로킹됨
                numRead = socketChannel.read(buffer); // 읽은 바이트 수를 반환, 읽을 수 있는 데이터가 없는 경우 -1을 반환(* -1은 클라이언트가 연결을 종료했음을 의미)
            }
            catch (IOException e) {
                System.err.println("데이터 읽기 에러!");
            }

            if (numRead == -1) { // 클라이언트가 연결을 종료했을 경우,
                keepDataTrack.remove(socketChannel);
                System.out.println("클라이언트 연결(Connection) 종료 : "
                        + socketChannel.getRemoteAddress());
                socketChannel.close();
                key.cancel();
                return;
            }

            // 클라이언트로부터 받은 데이터를 읽어서 클라이언트에게 그대로 응답해주는 처리 로직
            byte[] data = new byte[numRead]; // 읽은 데이터를 byte[] 타입 배열인 data 변수에 복사
            System.arraycopy(buffer.array(), 0, data, 0, numRead);

            System.out.println(new String(data, "UTF-8")
                    + " from " + socketChannel.getRemoteAddress()); // 문자열로 변환하여 콘솔에 출력

            // 클라이언트에게 응답 처리
            doEchoJob(key, data);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
        Key가 속한 채널에 서버가 데이터를 쓸 수 있는 경우 수행할 처리 로직
        → 클라이언트에게 데이터 응답
     */
    private static void writeOperation(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        List<byte[]> channelData = keepDataTrack.get(socketChannel);

        Iterator<byte[]> its = channelData.iterator(); // 특정 클라이언트 채널이 가진 데이터를 추출하기 위한 이터레이터 생성

        while (its.hasNext()) { // 이터레이터를 순회하면서 데이터 추출
            byte[] it = its.next();
            its.remove();
            socketChannel.write(ByteBuffer.wrap(it));
        }

        // 관심 이벤트를 읽기 가능한 상태로 변경
        // 즉, 클라이언트에게 데이터를 전송한 후 서버는 다시 클라이언트로부터 수신할 데이터를 기다릴 준비가 되었음을 나타냄
        // 이 설정을 통해 서버는 클라이언트가 보낸 새로운 메시지를 읽기 위해 다시 준비됨
        key.interestOps(SelectionKey.OP_READ);
    }


    /*
        클라이언트로부터 수신한 데이터를 다시 그대로 클라이언트에게 응답하기 위한 처리 로직
     */
    private static void doEchoJob(SelectionKey key, byte[] data) {
        // 특정 클라이언트에 해당하는 소켓채널을 불러옴
        SocketChannel socketChannel = (SocketChannel) key.channel();

        // keepDataTrack에서 현재 클라이언트에 해당하는 채널과 관련된 데이터를 담고 있는 리스트를 가져옴
        List<byte[]> channelData
                = keepDataTrack.get(socketChannel);// 특정 클라이언트가 가진 데이터 추출

        channelData.add(data); // 클라이언트로부터 수신한 데이터를 byte[]에 추가

        /*
        SelectionKey가 감지해야할 IO이벤트의 관심사를 설정,
        여기서는 쓰기 가능한 상태(OP_WRITE)에 대한 관심사를 추가

        정리하면, 클라이언트로부터 수신한 데이터를 keepDataTrack에 추가한 후,
        서버가 클라이언트에게 데이터를 쓰기 위한 준비가 되었음을 나타냄

        따라서 이후 Selector가 SelectionKey에 대해 OP_WRITE 이벤트가 발생했을 때,
        클라이언트 소켓 채널에 데이터를 쓰기 위한 처리를 수행할 수 있게 됨
         */
        key.interestOps(SelectionKey.OP_WRITE);

    }
}


