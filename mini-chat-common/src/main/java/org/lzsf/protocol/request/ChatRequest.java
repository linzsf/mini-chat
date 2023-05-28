package org.lzsf.protocol.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString
public class ChatRequest extends AuthRequest {
    private static final long serialVersionUID = 4546283134164238032L;

    private Long fromUserId;

    private Long toUserId;

    private String content;
}