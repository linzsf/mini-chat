package org.lzsf.protocol.response;

public enum ResponseCode {

    SUCCESS(200),

    AUTH_FAIL(403),

    ERROR(500),

    LOGIN_FAIL(-400),
    ;

    private Integer code;

    ResponseCode(Integer code) {
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
