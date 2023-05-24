package org.lzsf.mc.server.netty.handler;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        int msgLen = byteBuf.readInt();
        if (byteBuf.readableBytes() < msgLen) {
            byteBuf.resetReaderIndex();
            return;
        }
        int classNameLen = byteBuf.readInt();
        String className = byteBuf.readCharSequence(classNameLen, StandardCharsets.UTF_8).toString();
        byte[] msgBytes = new byte[msgLen - classNameLen];
        byteBuf.readBytes(msgBytes);
        Object message = JSON.parseObject(new String(msgBytes), Class.forName(className));
        list.add(message);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info(cause.getMessage());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(ctx.channel().remoteAddress().toString());
    }
}
