package org.lzsf.protocol.notice;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class ChatMessageNotice extends Notice {
    private static final long serialVersionUID = 3617977931139142540L;

    private String content;

    private String fromUserName;

    private String toUserName;
}
