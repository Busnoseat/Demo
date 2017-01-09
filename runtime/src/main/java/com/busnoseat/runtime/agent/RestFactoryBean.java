package com.busnoseat.runtime.agent;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * The type
 *
 * @author xubo
 * @Description:
 * @Date 2016/4/19
 */
public class RestFactoryBean<T> extends DynamicProxy implements FactoryBean<T>, InitializingBean {
    // 被代理的对象
    private  Class<T> target;

    // 请求路径
    private String  restUrl;

    // 生成的代理对象
    private T proxyObj;

    @Override
    public void afterPropertiesSet() throws Exception {
            final ClassLoader loader=target.getClassLoader();
            final Class<?>[] interfaces=new Class<?>[]{target};
            final java.lang.reflect.InvocationHandler h = new ProxyInvocationHandler(restUrl,target);
            proxyObj= newProxyInstance(loader, interfaces, h);
    }

    @Override
    public  T getObject() throws Exception {
        return proxyObj;
    }

    @Override
    public Class<?> getObjectType() {
        return  this.target ;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setTarget(Class<T> target) {
        this.target = target;
    }

    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }
}