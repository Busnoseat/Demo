package com.busnoseat.common.pagination;

import java.util.Properties;

import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

/**
 * mybatis 分页拦截器
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class }) }) public class PaginationInterceptor
        implements Interceptor {

    static int INDEX_MAPPED_STATEMENT = 0;
    static int INDEX_PARAMETER = 1;
    static int INDEX_ROW_BOUNDS = 2;
    static int INDEX_RESULT_HANDLER = 3;

    private Dialect dialect;

    /**
     * @see org.apache.ibatis.plugin.Interceptor#intercept(org.apache.ibatis.plugin.Invocation)
     */
    @SuppressWarnings("rawtypes") @Override public Object intercept(Invocation invocation) throws Throwable {

        MappedStatement mappedStatement = this.getMappedStatement(invocation);
        Object parameter = this.getParameter(invocation);
        if (!(parameter instanceof ParamMap)) {
            return invocation.proceed();
        }
        ParamMap map = (ParamMap) parameter;

        int offset = 1;
        int limit = 10;
        boolean directInvo = true;

        for (Object param : map.values()) {
            if (param instanceof Page) {
                offset = ((Page) param).getOffset();
                limit = ((Page) param).getPageSize();
                directInvo = false;
            }
        }
        if (directInvo) {
            return invocation.proceed();
        }
        if (dialect.supportsLimit() && (offset != RowBounds.NO_ROW_OFFSET || limit != RowBounds.NO_ROW_LIMIT)) {
            BoundSql boundSql = mappedStatement.getBoundSql(parameter);
            String sql = boundSql.getSql().trim();

            if (dialect.supportOffsetLimit()) {
                sql = dialect.getLimitString(sql, offset, limit);
            } else {
                sql = dialect.getLimitString(sql, RowBounds.NO_ROW_OFFSET, limit);
            }

            this.setMappedStatement(invocation, this.buildMappedStatement(mappedStatement, boundSql, sql));
        }

        return invocation.proceed();
    }

    private MappedStatement buildMappedStatement(MappedStatement ms, BoundSql boundSql, String sql) {
        Builder builder = new MappedStatement.Builder(ms.getConfiguration(), ms.getId(), new BoundSqlSqlSource(ms, boundSql, sql), ms.getSqlCommandType());

        builder.resource(ms.getResource());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.fetchSize(ms.getFetchSize());
        builder.timeout(ms.getTimeout());
        builder.statementType(ms.getStatementType());
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());
        builder.keyGenerator(ms.getKeyGenerator());
        builder.keyProperty(delimitedArraytoString(ms.getKeyProperties()));
        builder.keyColumn(delimitedArraytoString(ms.getKeyColumns()));
        builder.databaseId(ms.getDatabaseId());

        return builder.build();
    }

    private MappedStatement getMappedStatement(Invocation invocation) {
        return (MappedStatement) invocation.getArgs()[INDEX_MAPPED_STATEMENT];
    }

    private void setMappedStatement(Invocation invocation, MappedStatement mappedStatement) {
        invocation.getArgs()[INDEX_MAPPED_STATEMENT] = mappedStatement;
    }

    private Object getParameter(Invocation invocation) {
        return invocation.getArgs()[INDEX_PARAMETER];
    }

    /**
     * @see org.apache.ibatis.plugin.Interceptor#plugin(java.lang.Object)
     */
    @Override public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    /**
     * @see org.apache.ibatis.plugin.Interceptor#setProperties(java.util.Properties)
     */
    @Override public void setProperties(Properties properties) {

    }

    private static String delimitedArraytoString(String[] in) {
        if (in == null || in.length == 0) {
            return null;
        } else {
            StringBuffer answer = new StringBuffer();
            for (String str : in) {
                answer.append(str).append(",");
            }
            return answer.toString();
        }
    }

    public static class BoundSqlSqlSource implements SqlSource {
        private final BoundSql boundSql;

        public BoundSqlSqlSource(MappedStatement ms, BoundSql boundSql, String sql) {
            this.boundSql = buildBoundSql(ms, boundSql, sql);
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }

        private BoundSql buildBoundSql(MappedStatement ms, BoundSql boundSql, String sql) {
            BoundSql newBoundSql = new BoundSql(ms.getConfiguration(), sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            for (ParameterMapping mapping : boundSql.getParameterMappings()) {
                String prop = mapping.getProperty();
                if (boundSql.hasAdditionalParameter(prop)) {
                    newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
                }
            }
            return newBoundSql;
        }

    }

    public void setDialect(Dialect dialect) {
        this.dialect = dialect;
    }

}
