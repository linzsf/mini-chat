package org.lzsf.mc.server.netty.handler;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.lzsf.exception.UnknownMessageType;
import org.lzsf.protocol.Packet;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class MessageDecoder extends ByteToMessageDecoder {

    public static final Integer MAGIC_NUMBER = 0x1234567;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        int packHead = byteBuf.readInt();
        if (!MAGIC_NUMBER.equals(packHead)) {
            log.error("unknown message head:" + packHead);
            throw new UnknownMessageType();
        }
        if (byteBuf.readableBytes() < 4) {
            byteBuf.resetReaderIndex();
            return;
        }
        int packLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < packLength) {
            byteBuf.resetReaderIndex();
            return;
        }
        byte[] packetBytes = new byte[packLength];
        byteBuf.readBytes(packetBytes);
        Packet packet = (Packet) SerializationUtils.deserialize(packetBytes);
        list.add(packet);
        log.info("message packet: " + packet.toString());
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
