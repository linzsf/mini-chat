package org.lzsf.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class AuthResponse extends Packet {
    private static final long serialVersionUID = -900049200963193951L;
}
