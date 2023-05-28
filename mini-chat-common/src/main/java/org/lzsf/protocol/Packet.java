package org.lzsf.protocol;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public abstract class Packet implements Serializable {

    private static final long serialVersionUID = -1156112695593659425L;
    /**
     * 消息类型
     */
    public byte command;
}
