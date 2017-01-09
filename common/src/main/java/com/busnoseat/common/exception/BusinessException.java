package com.busnoseat.common.exception;

/**
 * The type BusinessException.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class BusinessException extends Exception {
    private static final long serialVersionUID = 1L;

    private String errCode;
    private String errMsg;

    /**
     * Gets err code.
     *
     * @return the err code
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * Gets err msg.
     *
     * @return the err msg
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param message the message
     */
    public BusinessException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Business exception.
     *
     * @param errCode the err code
     * @param errMsg the err msg
     */
    public BusinessException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
