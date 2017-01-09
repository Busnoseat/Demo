package com.busnoseat.common.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.core.LayoutBase;

/**
 * The type LayoutLog.
 * @Description:
 * @author liheng
 * @Date 2016 /3/8
 */
public class LayoutLog extends LayoutBase<ILoggingEvent> {
    private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * @see ch.qos.logback.core.Layout#doLayout(java.lang.Object)
     */
    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder sbuf = new StringBuilder();
        sbuf.append(sdf.format(new Date(event.getTimeStamp())));
        sbuf.append(" ");
        sbuf.append(event.getLevel());
        sbuf.append(" [");
        sbuf.append(event.getThreadName());
        sbuf.append("] ");
        sbuf.append(event.getLoggerName());
        sbuf.append(" - ");
        sbuf.append(event.getFormattedMessage());
        sbuf.append(System.getProperty("line.separator"));
        //异常打印
        if (event.getThrowableProxy() != null) {
            IThrowableProxy iThrowableProxy = event.getThrowableProxy();
            sbuf.append(iThrowableProxy.getClassName());
            sbuf.append(":");
            sbuf.append(iThrowableProxy.getMessage());
            sbuf.append(System.getProperty("line.separator"));
            for (StackTraceElementProxy stack : iThrowableProxy.getStackTraceElementProxyArray()) {
                sbuf.append(stack.getSTEAsString());
                sbuf.append(System.getProperty("line.separator"));
            }
        }
        return sbuf.toString();

    }

}
