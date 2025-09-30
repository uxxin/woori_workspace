package dev.syntax.step07_hello_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

// 클라이언트의 메세지를 폐기하지 않고, 응답하는 서버
public class EchoServer {
    // TODO: DiscardServer와 동일
    public static void main(String[] args) throws Exception {
        // bossGroup: 클라이언트의 연결 요청을 수락. 스레드 1개로 충분
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        // workerGroup: 연결된 클라이언트의 소켓 I/O(데이터 읽기/쓰기)를 처리.
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup) // EventGroup 할당
                    .channel(NioServerSocketChannel.class) // Non-blocking I/O 서버 소켓 채널 사용
                    .handler(new LoggingHandler(LogLevel.INFO)) // ⭐ BossGroup 동작 로깅하기
                    .childHandler(new ChannelInitializer<SocketChannel>() { //Worker Group이 처리할 핸들러 설정
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();

                            // ⭐ 내부 동작 볼 수 있게 LoggingHandler 추가
                            pipeline.addLast(new LoggingHandler(LogLevel.INFO));

                            // 실제 비즈니스 로직 처리 핸들러
                            pipeline.addLast(new EchoServerHandler());
                        }
                    });

            // 서버 바인딩 및 시작
            ChannelFuture future = bootstrap.bind(8080).sync();
            future.channel().closeFuture().sync();

        } finally{
            // 리소스 정리
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
