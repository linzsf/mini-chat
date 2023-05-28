package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class RegistRequest extends Packet {
    private static final long serialVersionUID = 580036401509309104L;

    private String nickName;

    private String userName;

    private String passWord;
}
