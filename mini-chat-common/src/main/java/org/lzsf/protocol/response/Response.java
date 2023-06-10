package org.lzsf.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.lzsf.protocol.Packet;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public abstract class Response extends Packet {
    private static final long serialVersionUID = 5596106337052616867L;

    private Integer result = ResponseCode.SUCCESS.getCode();

    private String requestId;

    public boolean isSuccess() {
        return ResponseCode.SUCCESS.getCode().equals(this.result);
    }
}
