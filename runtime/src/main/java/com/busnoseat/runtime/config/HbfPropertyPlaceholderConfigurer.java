/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.config;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.busnoseat.runtime.constant.HbfConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 
 * @Description: 
 *
 * @author liheng
 * @version $Id: HbfPropertyPlaceholderConfigurer.java, v 0.1 2016年3月13日 下午2:11:59 liheng Exp $
 */
public class HbfPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements HbfConstant {
    public Logger logger = LoggerFactory.getLogger(HBF_LOG_NAME);

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        Map<String, String> propertiesMap = PropertiesContext.getPropertiesMap();
        logger.info("系统配置详情");
        for (Entry<String, String> entry : propertiesMap.entrySet()) {
            logger.info("{}={}", entry.getKey(), entry.getValue());
        }
        props.putAll(PropertiesContext.getPropertiesMap());
        super.processProperties(beanFactoryToProcess, props);
    }
}
