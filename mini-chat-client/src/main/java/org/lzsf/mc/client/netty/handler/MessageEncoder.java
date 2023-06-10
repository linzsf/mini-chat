package org.lzsf.mc.client.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.commons.lang3.SerializationUtils;
import org.lzsf.protocol.Packet;

public class MessageEncoder extends MessageToByteEncoder<Packet> {

    public static final Integer MAGIC_NUMBER = 0x1234567;

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        byte[] packetBytes = SerializationUtils.serialize(msg);
        out.writeInt(MAGIC_NUMBER);
        out.writeInt(packetBytes.length);
        out.writeBytes(packetBytes);
    }
}
