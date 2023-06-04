package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class AuthRequest extends Request{
    private static final long serialVersionUID = -2160317000376692123L;

    /**
     * jwt token
     */
    private String token;


}
