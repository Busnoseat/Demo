package com.busnoseat.common.pagination;

/**
 * The interface Dialect.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public interface Dialect {
    /**
     * 是否支持分页
     *
     * @return boolean
     */
    public boolean supportsLimit();

    /**
     * 是否支持位移分页
     *
     * @return boolean
     */
    public boolean supportOffsetLimit();

    /**
     * 获取分页sql
     *
     * @param sql 原sql
     * @param offset 位移量
     * @param maxRow 最大记录数
     * @return limit string
     */
    public String getLimitString(String sql, int offset, int maxRow);
}
