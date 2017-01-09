package com.busnoseat.common.facade.abs;

import java.io.Serializable;

/**
 * The type Base request.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class BaseRequest implements Serializable {
    /**  */
    private static final long serialVersionUID = -7437852465328524521L;
    /** 请求号*/
    private String reqId;
    /** 项目名*/
    private String appName;
    /** Ip*/
    private String ip;

    /**
     * Getter method for property <tt>reqId</tt>.
     *
     * @return property value of reqId
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * Setter method for property <tt>reqId</tt>.
     *
     * @param reqId value to be assigned to property reqId
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * Gets app name.
     *
     * @return the app name
     */
    public String getAppName() {
        return appName;
    }

    /**
     * Sets app name.
     *
     * @param appName the app name
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }
}
