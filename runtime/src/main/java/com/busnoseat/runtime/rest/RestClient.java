package com.busnoseat.runtime.rest;

import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.busnoseat.common.util.JsonUtil;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.busnoseat.common.facade.abs.BaseRequest;
import com.busnoseat.common.util.GUID;
import com.busnoseat.common.util.ToStringUtil;
import com.busnoseat.runtime.config.PropertiesContext;
import com.busnoseat.runtime.constant.HbfConstant;

/**
 * The type Rest client.
 *
 * @author liheng
 * @Description: rest client
 * @Date 2016 /3/8
 */
public class RestClient {
    /**
     * The Logger.
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RestTemplate template = new RestTemplate();
    private int connectTimeout = 2000;
    private int readTimeout = 5000;
    private GUID guid = new GUID();
    private HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory() {

        /**
         * @see HttpComponentsClientHttpRequestFactory#createHttpUriRequest(HttpMethod, URI)
         */
        @Override
        protected HttpUriRequest createHttpUriRequest(HttpMethod httpMethod, URI uri) {
            if (HttpMethod.DELETE == httpMethod) {
                return new HttpEntityEnclosingDeleteRequest(uri);
            }
            if (HttpMethod.GET == httpMethod) {
                return new HttpEntityEnclosingGetRequest(uri);
            }
            return super.createHttpUriRequest(httpMethod, uri);
        }

    };
    private HttpHeaders headers = new HttpHeaders();

    /**
     * Instantiates a new Rest client.
     */
    public RestClient() {
        requestFactory.setConnectTimeout(connectTimeout);
        requestFactory.setReadTimeout(readTimeout);
        template.setRequestFactory(requestFactory);
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter mappingJacksonHttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJacksonHttpMessageConverter.getObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        messageConverters.add(mappingJacksonHttpMessageConverter);
        template.setMessageConverters(messageConverters);
        template.setErrorHandler(new DefaultResponseErrorHandler());

        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

    }

    /**
     * 拼接url形式的get请求
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param responseType the response type
     * @return the t
     */
    public <T> T get(String url, Object request, Class<T> responseType) {
        handleRequest(request);
        url = buildGetUrl(url, JsonUtil.toMap(request));
        logger.info("path={},method=get,request={}", url, ToStringUtil.toString(request));
        long startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
        T t = template.getForObject(url, responseType);
        long endMili = System.currentTimeMillis();
        logger.info("path={},method=get,time={}ms,response={}", url, endMili - startMili, ToStringUtil.toString(t));
        return t;
    }

    private String buildGetUrl(String url, Map<String, String> map) {
        StringBuffer sb = new StringBuffer();
        sb.append(url);
        sb.append("?");
        for (Map.Entry<String, ?> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
            sb.append("&");
        }
        return sb.toString();
    }

    /**
     * Get t.
     *
     * @param <T>     the type parameter
     * @param url     the url
     * @param request the request
     * @param ref     the ref
     * @return the t
     */
    public <T> T get(String url, Object request, ParameterizedTypeReference<T> ref) {
        return exchange(url, request, HttpMethod.GET, ref);
    }

    /**
     * Delete t.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param responseType the response type
     * @return the t
     */
    public <T> T delete(String url, Object request, Class<T> responseType) {
        return exchange(url, request, HttpMethod.DELETE, responseType);
    }

    /**
     * Post t.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param ref the ref
     * @return the t
     */
    public <T> T post(String url, Object request, ParameterizedTypeReference<T> ref) {
        return exchange(url, request, HttpMethod.POST, ref);
    }

    /**
     * Post t.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param responseType the response type
     * @return the t
     */
    public <T> T post(String url, Object request, Class<T> responseType) {
        return exchange(url, request, HttpMethod.POST, responseType);
    }

    /**
     * Put t.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param responseType the response type
     * @return the t
     */
    public <T> T put(String url, Object request, Class<T> responseType) {
        return exchange(url, request, HttpMethod.PUT, responseType);
    }

    /**
     * Exchange t.
     *
     * @param <T>          the type parameter
     * @param url          the url
     * @param request      the request
     * @param httpMethod   the http method
     * @param responseType the response type
     * @return the t
     */
    private <T> T exchange(String url, Object request, HttpMethod httpMethod, Class<T> responseType) {
        handleRequest(request);
        HttpEntity<Object> entity = new HttpEntity<Object>(request, headers);
        URI uri = URI.create(url);
        logger.info("path={},method={},request={}", uri.getPath(), httpMethod.name(), ToStringUtil.toString(request));
        long startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
        ResponseEntity<T> reponse = template.exchange(uri, httpMethod, entity, responseType);
        long endMili = System.currentTimeMillis();
        T body = reponse.getBody();
        logger.info("path={},method={},time={}ms,response={}", uri.getPath(), httpMethod.name(), endMili - startMili, ToStringUtil.toString(body));
        return body;
    }

    /**
     * Exchange t.
     *
     * @param <T>        the type parameter
     * @param url        the url
     * @param request    the request
     * @param httpMethod the http method
     * @param ref        the ref
     * @return the t
     */
    private <T> T exchange(String url, Object request, HttpMethod httpMethod, ParameterizedTypeReference<T> ref) {
        handleRequest(request);
        HttpEntity<Object> entity = new HttpEntity<Object>(request, headers);
        URI uri = URI.create(url);
        logger.info("path={},method={},request={}", uri.getPath(), httpMethod.name(), ToStringUtil.toString(request));
        long startMili = System.currentTimeMillis();// 当前时间对应的毫秒数
        ResponseEntity<T> reponse = template.exchange(uri, httpMethod, entity, ref);
        long endMili = System.currentTimeMillis();
        T body = reponse.getBody();
        logger.info("path={},method={},time={}ms,response={}", uri.getPath(), httpMethod.name(), endMili - startMili, ToStringUtil.toString(body));
        return body;
    }

    private void handleRequest(Object request) {
        if (request instanceof BaseRequest) {
            if (((BaseRequest) request).getReqId() != null) {
                ((BaseRequest) request).setReqId(guid.next().toString());
            }
            ((BaseRequest) request).setAppName(PropertiesContext.get(HbfConstant.APP_NAME_KEY));
            try {
                String ip = InetAddress.getLocalHost().getHostAddress();
                ((BaseRequest) request).setIp(ip);
            } catch (UnknownHostException e) {
                logger.info("get ip error", e);
            }
        }
    }

    /**
     * Getter method for property <tt>connectTimeout</tt>.
     *
     * @return property value of connectTimeout
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Setter method for property <tt>connectTimeout</tt>.
     *
     * @param connectTimeout value to be assigned to property connectTimeout
     */
    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
        requestFactory.setConnectTimeout(connectTimeout);
    }

    /**
     * Getter method for property <tt>readTimeout</tt>.
     *
     * @return property value of readTimeout
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Setter method for property <tt>readTimeout</tt>.
     *
     * @param readTimeout value to be assigned to property readTimeout
     */
    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        requestFactory.setReadTimeout(readTimeout);
    }

    /**
     * The type Http entity enclosing delete request.
     */
    class HttpEntityEnclosingDeleteRequest extends HttpEntityEnclosingRequestBase {

        /**
         * Instantiates a new Http entity enclosing delete request.
         *
         * @param uri the uri
         */
        public HttpEntityEnclosingDeleteRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "DELETE";
        }
    }

    /**
     * The type Http entity enclosing get request.
     */
    class HttpEntityEnclosingGetRequest extends HttpEntityEnclosingRequestBase {

        /**
         * Instantiates a new Http entity enclosing get request.
         *
         * @param uri the uri
         */
        public HttpEntityEnclosingGetRequest(final URI uri) {
            super();
            setURI(uri);
        }

        @Override
        public String getMethod() {
            return "GET";
        }
    }

}
