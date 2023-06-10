package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ChatMessageRequest extends AuthRequest{
    private static final long serialVersionUID = 4546283134164238032L;

    private String fromUserName;

    private String toUserName;

    private String content;
}
