package dev.syntax.step04_java_nio;

/*
    java.nio.channels.Channel

    파일 및 소켓과 같이 I/O 작업을 수행할 수 있는 엔티티에 대한
    연결을 수행하는 역할인 Channel 클래스를 정의,

    다중화된(multiplexed) 논블로킹 I/O 작업을 위한 Selector 클래스를 정의함
    -> SelectableChannel

    A channel that can be multiplexed via a Selector.
    -> Selector를 통해 멀티플렉싱으로 동작하도록 하기 위해 필요한 채널

    이하 클래스들은 논블로킹이 가능하도록 Selector와 함께 사용될 수 있도록 상속받은 하위 클래스
 * -> XxxChannel 'extends AbstractSelectableChannel, SelectableChannel'
 *
 * DatagramChannel
 * A selectable channel for datagram-oriented sockets.
 * -> UDP 프로토콜 기반 통신 프로그램 구현 시 활용
 *
 * FileChannel
 * A channel for reading, writing, mapping, and manipulating a file.
 * -> 파일 입출력 기반 통신 프로그램 구현 시 사용
 *
 * ServerSocketChannel -> 이번 구현에서 활용되는 API
 * A selectable channel for stream-oriented listening sockets.
 * -> TCP 프로토콜 기반 통신 프로그램 구현 시 사용
 */

public class ChannelOverview {
    // 자바코드를 작성하진 않음
}
