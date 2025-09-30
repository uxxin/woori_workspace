package dev.syntax.step07_hello_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.nio.NioIoEvent;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/*
    Discard: 폐기하다
    클라이언트의 요청만 받고, 아무것도 하지 않는 서버
    → 서버 실행까지만 작성해보는 실습
 */
public class DiscardServer {
    public static void main(String[] args) throws InterruptedException {
        // 여기서는 서버 실행에만 중점을 두고 먼저 작성

        // EventLoopGroup 객체 생성
        // Boss(Master) - 주로 클라이언트 연결 요청 처리 담당(Accept 처리)
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);

        // Worker - 데이터 송수신 처리 담당(Read/Write 처리 담당)
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            // 서버 초기화용도의 부트스트랩(Bootstrap) 객체 생성(헬퍼 클래스)
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 부트스트랩 객체를 통해 각종 네트워크와 관련된 옵션 설정들을 적용할 수 있음
            bootstrap.group(bossGroup, workerGroup) // 이벤트 루프 설정
                    .channel(NioServerSocketChannel.class) // 소켓의 모드 설정(블로킹 or 논블로킹?)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        // 새로 연결이 들어오면(SocketChannel이 생성되면), 실행될 초기화 로직 정의
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 파이프라인: 소켓 채널에서 IO이벤트들이 흐르는 경로
                            // -> 파이프라인에 핸들러를 연결(등록)할 수 있음
                            // 핸들러? - 각 IO 이벤트별로 처리할 로직(작업)을 작성하는 부분

                            // 채널 파이프라인 생성
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // 생성한 핸들러 객체를 파이프라인에 등록
                            pipeline.addLast(new DiscardServerHandler());
                        }
                    });

            // 서버 실행 준비(포트 지정)
            ChannelFuture future = bootstrap.bind(8888).sync(); // bind() 작업이 완료될 때까지 스레드 대기
            future.channel().closeFuture().sync(); // 서버 채널이 닫힐 때까지 대기(서버 실행 유지하기 위함)

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }
}
