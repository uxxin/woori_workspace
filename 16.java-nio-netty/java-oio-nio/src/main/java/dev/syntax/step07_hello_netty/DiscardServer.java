package dev.syntax.step07_hello_netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiscardServer {
    public static void main(String[] args) throws InterruptedException {
        // 여기서는 서버 실행에만 중점을 두고 먼저 작성

        // EventLoopGroup 객체 생성
            // Boss(Master) - 주로 클라이언트 연결 요청 처리 담당
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        
        // Worker - 데이터 송수신 처리 담당
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        
        try{
            // 서버 초기화 용도의 부트스트랩(Bootstrap) 객체 생성
            ServerBootstrap bootstrap = new ServerBootstrap();

            // 부트스트랩 객체를 통해 각종 네트워크와 관련된 옵션 설정들을 적용할 수 있음
            bootstrap.group(bossGroup,workerGroup) // 이벤트 루프 설정
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            // TODO: pipeline.addLast(
                        }
                    });
            // 서버 실행 준비(포트 지정)
            ChannelFuture future = bootstrap.bind(8888).sync();
            future.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
