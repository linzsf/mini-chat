package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class RegistRequest extends Request{
    private static final long serialVersionUID = 580036401509309104L;

    private String nickName;

    private String userName;

    private String passWord;
}
