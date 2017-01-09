package com.busnoseat.common.pagination;

/**
 * The type BaseDialect.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public abstract class BaseDialect implements Dialect {
    /**
     * The constant SQL_END_DELIMITER.
     */
    protected static final String SQL_END_DELIMITER = ";";

    /**
     * Trim string.
     *
     * @param sql the sql
     * @return the string
     */
    protected String trim(String sql) {
        sql = sql.trim();
        if (sql.endsWith(SQL_END_DELIMITER)) {
            sql = sql.substring(0, sql.length() - 1 - SQL_END_DELIMITER.length());
        }
        return sql;
    }

    @Override public boolean supportsLimit() {
        return true;
    }

    @Override public boolean supportOffsetLimit() {
        return true;
    }
}