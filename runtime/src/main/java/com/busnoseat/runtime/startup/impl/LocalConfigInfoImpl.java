/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.startup.impl;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.busnoseat.runtime.startup.StartUpExecuter;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.busnoseat.runtime.config.PropertiesContext;
import com.busnoseat.runtime.constant.HbfConstant;

/**
 * 
 * @Description: 
 *
 * @author liheng
 * @version $Id: LocalConfigInfoImpl.java, v 0.1 2016年3月13日 下午12:25:33 liheng Exp $
 */
public class LocalConfigInfoImpl implements StartUpExecuter, HbfConstant {


    @Override
    public void execute() {
        Properties fileInProp = new Properties();
        Properties fileOutProp = new Properties();
        try {
            Resource r = new ClassPathResource(LOCAL_PROPERTIES_FILE_NAME);
            fileInProp.load(r.getInputStream());
            Set<Entry<Object, Object>> entries = fileInProp.entrySet();
            for (Entry<Object, Object> entry : entries) {
                PropertiesContext.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            logger.error("初始化config.properties失败", e);
        }
        InputStream  input=null;
        try {
             input=new FileInputStream(LOCAL_PROPERTIES_PATH+PropertiesContext.get(APP_NAME_KEY)+"/"+LOCAL_PROPERTIES_FILE_NAME);
            fileOutProp.load(input);
            Set<Entry<Object, Object>> entries = fileOutProp.entrySet();
            for (Entry<Object, Object> entry : entries) {
                PropertiesContext.put((String) entry.getKey(), (String) entry.getValue());
            }
        } catch (Exception e) {
            logger.error("初始化:/data/config/{}/config.properties失败,{}",PropertiesContext.get(APP_NAME_KEY),e.getMessage());
        }finally {
            if(input!=null){
                try {
                    input.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
    }
}
