package org.lzsf.protocol;

import lombok.Data;

@Data
public class MessageProtocol {

    private Integer length;

    private Integer classNameLen;

    private String className;

    private byte[] message;
}
