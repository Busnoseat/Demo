package com.busnoseat.common.facade.abs;

import java.io.Serializable;

/**
 * The type Base response.
 * @Description:
 * @author liheng
 * @Date 2016/3/8
 */
public class BaseResponse implements Serializable {

    /** 系统返回码 */
    private String respCode;
    /** 系统返回信息 */
    private String respMessage;

    /**
     * Gets resp code.
     *
     * @return the resp code
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * Sets resp code.
     *
     * @param respCode the resp code
     */
    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    /**
     * Getter method for property <tt>respMessage</tt>.
     *
     * @return property value of respMessage
     */
    public String getRespMessage() {
        return respMessage;
    }

    /**
     * Setter method for property <tt>respMessage</tt>.
     *
     * @param respMessage value to be assigned to property respMessage
     */
    public void setRespMessage(String respMessage) {
        this.respMessage = respMessage;
    }

}
