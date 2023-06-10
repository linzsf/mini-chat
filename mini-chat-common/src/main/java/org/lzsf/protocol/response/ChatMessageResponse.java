package org.lzsf.protocol.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ChatMessageResponse extends Response{
    private static final long serialVersionUID = -3154064416830335711L;
}
