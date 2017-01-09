package com.busnoseat.common.exception;

/**
 * The type RepositoryException.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class RepositoryException extends RuntimeException {

    /**  */
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
     * Instantiates a new Repository exception.
     *
     * @param message the message
     */
    public RepositoryException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Repository exception.
     *
     * @param errCode the err code
     * @param errMsg the err msg
     */
    public RepositoryException(String errCode, String errMsg) {
        super(errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }
}
