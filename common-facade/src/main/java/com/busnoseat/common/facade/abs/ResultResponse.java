package com.busnoseat.common.facade.abs;


/**
 * The type ResultResponse.
 *
 * @author xubo
 * @Description:
 * @Date 2016/12/6
 */
public class ResultResponse<T> extends BaseResponse {
    private T result;

    public ResultResponse(String respCode, String respMessage, T result) {
        this(respCode, respMessage);
        this.result = result;
    }

    public ResultResponse(String respCode, String respMessage) {
        this.setRespCode(respCode);
        this.setRespMessage(respMessage);
    }

    public ResultResponse(T result) {
        this.result = result;
    }

    public ResultResponse() {

    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
