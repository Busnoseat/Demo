package com.busnoseat.common.code;

import java.io.Serializable;

/**
 * The type RspCode.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public interface RspCode extends Serializable {

    /**
     * Gets code.
     *
     * @return the code
     */
    public String getCode();

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName();

}