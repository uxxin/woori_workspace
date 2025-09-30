package dev.syntax.step07_hello_netty;

// DiscardServerHandler.java

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// 채널 파이프라인에 등록할 핸들러 객체
// 특정 IO 이벤트가 발생했을 때 처리할 로직을 작성하는 부분
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 채널에 데이터 읽기가 발생했을 때 처리할 로직 작성 부분
        System.out.println("channelRead() 호출됨");

        if(true){
            throw new RuntimeException("테스트용 예외 메시지");
        }
    }

    // 채널 Inbound 관련해서 예외가 발생했을 경우 처리할 로직 작성 부분
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("예외 발생! 원이: " + cause.getMessage());
        cause.printStackTrace();
        ctx.close();
    }
}
