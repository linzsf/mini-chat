package org.lzsf.protocol.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class Notice extends Packet {
    private static final long serialVersionUID = -1119444243434579510L;
}
