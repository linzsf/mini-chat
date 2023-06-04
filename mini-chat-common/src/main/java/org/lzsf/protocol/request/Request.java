package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class Request extends Packet {
    private static final long serialVersionUID = -533540948124504576L;
}
