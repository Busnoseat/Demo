package com.busnoseat.common.facade.abs;


/**
 * The type PageResponse.
 *
 * @author xubo
 * @Description:
 * @Date 2016/12/6
 */
public class PageResponse<T> extends ResultResponse {
    private Integer totalRecord;
    private Integer totalPage;

    public PageResponse(String respCode, String respMessage, Object result) {
        super(respCode, respMessage, result);
    }

    public PageResponse() {

    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
