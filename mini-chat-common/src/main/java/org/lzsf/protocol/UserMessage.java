package org.lzsf.protocol;

import lombok.Data;

@Data
public class UserMessage extends AbstractMessage{
    private Long fromUserId;

    private Long toUserId;

    private String content;
}
