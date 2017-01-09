/**
 * 
 * 厚本金融
 * Copyright (c) 2011-2016 Hang Tian Wealth Co.Ltd. All Rights Reserved.
 */
package com.busnoseat.runtime.startup.impl;

import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.busnoseat.runtime.etcd.EtcdNode;
import com.busnoseat.runtime.etcd.EtcdResult;
import com.busnoseat.runtime.startup.StartUpExecuter;
import com.google.common.util.concurrent.ListenableFuture;
import com.busnoseat.runtime.config.PropertiesContext;
import com.busnoseat.runtime.constant.HbfConstant;
import com.busnoseat.runtime.etcd.EtcdClient;
import com.busnoseat.runtime.etcd.EtcdClientException;

/**
 * 
 * @Description: 
 *
 * @author liheng
 * @version $Id: RemoteConfigInfoImpl.java, v 0.1 2016年3月13日 下午12:29:25 liheng Exp $
 */
public class RemoteConfigInfoImpl implements StartUpExecuter, HbfConstant {
    private EtcdClient etcdClient;

    /** 
     * @see StartUpExecuter#execute()
     */
    @Override
    public void execute() {
        try {
            String appName = PropertiesContext.get(APP_NAME_KEY);
            String etcdUrl = PropertiesContext.get(ETCD_URL);
            String dir = "/" + appName + "/";
            etcdClient = new EtcdClient(URI.create(etcdUrl));
            List<EtcdNode> nodeList = etcdClient.listDirectory(appName);
            if (nodeList == null) {
                logger.info("etcd不存在任何配置信息");
                return;
            }

            for (EtcdNode node : nodeList) {
                PropertiesContext.put(node.key.replace(dir, ""), node.value);

            }
            new Thread(new Runnable() {

                @Override
                public void run() {
                    while (true) {
                        try {
                            ListenableFuture<EtcdResult> future = etcdClient.watch(PropertiesContext.get(APP_NAME_KEY), null, true);
                            EtcdResult etcdResult = future.get();
                            logger.info("reload key={},value={}", etcdResult.node.key, etcdResult.node.value);
                            PropertiesContext.put(etcdResult.node.key.replace(dir, ""), etcdResult.node.value);

                        } catch (EtcdClientException e) {
                            logger.error("ReloadConfig error:", e);
                        } catch (InterruptedException e) {
                            logger.error("ReloadConfig error:", e);
                        } catch (ExecutionException e) {
                            logger.error("ReloadConfig error:", e);
                        }

                    }
                }
            }).start();
        } catch (Throwable e) {
            logger.error("初始化远程配置失败", e);
        }
    }

}
