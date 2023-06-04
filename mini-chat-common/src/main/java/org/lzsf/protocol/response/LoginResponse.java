package org.lzsf.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class LoginResponse extends Response{

    private static final long serialVersionUID = -1711952349816083578L;

    private String jwtToken;
}
