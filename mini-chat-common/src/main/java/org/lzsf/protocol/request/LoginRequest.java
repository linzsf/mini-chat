package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class LoginRequest extends Request{
    private static final long serialVersionUID = -5391203036562526234L;

    private String userName;

    private String password;
}
