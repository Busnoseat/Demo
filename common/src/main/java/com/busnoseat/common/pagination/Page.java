package com.busnoseat.common.pagination;

import java.io.Serializable;

/**
 * The type Page.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class Page implements Serializable {
    /**  */
    private static final long serialVersionUID = -2332161085849113773L;

    private int pageSize = 10;

    private int totalPage;

    private int currPage = 1;

    private int totalRecords;

    /**
     * Instantiates a new Page.
     *
     * @param pageSize the page size
     * @param currentPage the current page
     */
    public Page(int pageSize, int currentPage) {
        this.pageSize = pageSize;
        this.currPage = currentPage;
    }

    /**
     * Instantiates a new Page.
     */
    public Page() {

    }

    /**
     * 获取显示的数据数量
     *
     * @return page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 设置显示数据数量
     *
     * @param pageSize the page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获取总页数
     *
     * @return total page
     */
    public int getTotalPage() {
        return totalPage;
    }

    /**
     * 设置总页数
     *
     * @param totalPage the total page
     */
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    /**
     * 获取当前页
     *
     * @return curr page
     */
    public int getCurrPage() {
        return currPage;
    }

    /**
     * 设置当前页
     *
     * @param currPage the curr page
     */
    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    /**
     * 获取总记录条数
     *
     * @return total records
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    /**
     * Gets offset.
     *
     * @return the offset
     */
    public int getOffset() {
        if (currPage == 0) {
            return 0;
        }
        return (currPage - 1) * pageSize;
    }

    /**
     * 获取总记录条数
     *
     * @param totalRecords the total records
     */
    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    @Override public String toString() {
        return "Page [pageSize=" + pageSize + ", totalPage=" + totalPage + ", currPage=" + currPage + ", totalRecords=" + totalRecords + "]";
    }

}
