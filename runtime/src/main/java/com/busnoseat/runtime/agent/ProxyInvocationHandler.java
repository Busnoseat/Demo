package com.busnoseat.runtime.agent;

import com.busnoseat.runtime.rest.RestClient;
import org.springframework.http.HttpMethod;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocationHandler implements InvocationHandler {
    private String restUrl = null;
    private Class target;

    public ProxyInvocationHandler(String object, Class target) {
        this.restUrl = object;
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RestClient restClient = new RestClient();

        //请求路径为  接口上的注解 强转注意
        RequestMapping interfaceAnnotationValues = (RequestMapping) target.getAnnotation(RequestMapping.class);
        String interfaceUrl = interfaceAnnotationValues != null && interfaceAnnotationValues.value() != null ? interfaceAnnotationValues.value()[0] : "";

        //方法上的注解
        String[] methodAnnotationValues = method.getAnnotation(RequestMapping.class).value();
        Assert.isTrue(methodAnnotationValues != null && methodAnnotationValues.length == 1, "目标方法上的requestMapping注解的value值有误");
        String url = restUrl + interfaceUrl + methodAnnotationValues[0];

        //请求类型为 从annotation里获取
        RequestMethod[] requestMethods = method.getAnnotation(RequestMapping.class).method();
        Assert.isTrue(requestMethods != null && requestMethods.length == 1, "目标接口的requestMapping注解的method值有误");
        RequestMethod requestMethod = requestMethods[0];
        HttpMethod httpMethod = changeRequestToHttpMethod(requestMethod);
        Assert.isTrue(httpMethod != null, "你的请求类型不属于 GET ,PUT, POST,DELETE的一种");

        //返回值为 从method里获取
        Class<?> returnType = method.getReturnType();

        //参数为 从method里获取
        Assert.isTrue(args != null && args.length == 1, "请求参数限制为一个");
        if (httpMethod == HttpMethod.GET) {
            return restClient.get(url, args[0], returnType);
        }
        if (httpMethod == HttpMethod.POST) {
            return restClient.post(url, args[0], returnType);
        }
        if (httpMethod == HttpMethod.PUT) {
            return restClient.put(url, args[0], returnType);
        }
        return restClient.delete(url, args[0], returnType);
    }

    /**
     * 根据RequestMethod 返回HttpMethod的类型
     */
    protected HttpMethod changeRequestToHttpMethod(RequestMethod r) {
        HttpMethod h = null;
        if (r == RequestMethod.GET) {
            h = HttpMethod.GET;
        } else if (r == RequestMethod.PUT) {
            h = HttpMethod.PUT;
        } else if (r == RequestMethod.POST) {
            h = HttpMethod.POST;
        } else if (r == RequestMethod.DELETE) {
            h = HttpMethod.DELETE;
        }
        return h;
    }
}
