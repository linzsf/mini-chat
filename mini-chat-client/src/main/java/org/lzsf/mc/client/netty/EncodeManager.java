package org.lzsf.mc.client.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.lang3.SerializationUtils;
import org.lzsf.protocol.Packet;

public class EncodeManager {

    public static final Integer MAGIC_NUMBER = 0x1234567;

    public static ByteBuf encode(Packet packet) {
        ByteBuf byteBuf = Unpooled.buffer();
        byte[] packetBytes = SerializationUtils.serialize(packet);
        byteBuf.writeInt(MAGIC_NUMBER);
        byteBuf.writeInt(packetBytes.length);
        byteBuf.writeBytes(packetBytes);
        return byteBuf;
    }
}
