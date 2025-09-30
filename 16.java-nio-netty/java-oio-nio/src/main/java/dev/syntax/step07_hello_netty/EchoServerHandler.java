package dev.syntax.step07_hello_netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    // 서버 입장에서 채널을 통해 읽기 이벤트가 발생했을 때, 처리할 로직 작성 부분
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 채널을 통해 버퍼에서 읽은 메시지를 문자열 타입으로 변환
        String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());

        StringBuilder builder = new StringBuilder();
        builder.append("수신한 문자열 [");
        builder.append(readMessage);
        builder.append("]");
        System.out.println(builder.toString());

        // 읽은 데이터를 바로 클라이언트에게 반환
        ctx.write(msg);
    }

    // 데이터 읽기 작업이 모두 완료되었을 때 호출되는 메소드
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        // write()는 내부 버퍼에만 데이터를 기록. flush를 호출해야 실제 네트워크로 전송된다.
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close(); // 예외 발생 시 채널 연결 닫음
    }
}
