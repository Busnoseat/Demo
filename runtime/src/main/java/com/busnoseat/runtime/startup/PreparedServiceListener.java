/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.startup;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.busnoseat.runtime.startup.impl.RemoteConfigInfoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.busnoseat.runtime.constant.HbfConstant;
import com.busnoseat.runtime.startup.impl.LocalConfigInfoImpl;

/**
 * 
 * @Description: Web容器启动预加载项
 *
 * @author liheng
 * @version $Id: PreparedServiceListener.java, v 0.1 2016年3月13日 下午1:46:25 liheng Exp $
 */
public class PreparedServiceListener implements ServletContextListener, HbfConstant {
    private Logger logger = LoggerFactory.getLogger(HBF_LOG_NAME);

    /**
     * 启动待执行的列表
     */
    private volatile List<StartUpExecuter> executers;

    private volatile boolean started = false;

    /**
     * 注册所有启动执行项到executers按从上下顺序排列，先加入的先执行
     */
    private synchronized void initExecuters() {
        executers.add(new LocalConfigInfoImpl());
        executers.add(new RemoteConfigInfoImpl());

    }

    /** 
     * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.info("系统初始化开始");
        if (executers == null) {
            executers = new LinkedList<StartUpExecuter>();
            synchronized (executers) {
                if (!started) {
                    initExecuters();
                    executeAll();
                    started = true;
                }
            }
        }
    }

    /**
     * 按数组坐标顺序执行execute方法
     */
    private synchronized void executeAll() {
        for (StartUpExecuter executer : executers) {
            executer.execute();
        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        executers = null;
        started = false;
    }

}
