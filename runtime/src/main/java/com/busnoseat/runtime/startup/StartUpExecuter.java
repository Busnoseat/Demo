/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.busnoseat.runtime.constant.HbfConstant;

/**
 * 
 * @Description: 
 *
 * @author liheng
 * @version $Id: StartUpExecuter.java, v 0.1 2016年3月13日 下午12:22:37 liheng Exp $
 */
public interface StartUpExecuter extends HbfConstant {
    public Logger logger = LoggerFactory.getLogger(HBF_LOG_NAME);

    public void execute();
}
