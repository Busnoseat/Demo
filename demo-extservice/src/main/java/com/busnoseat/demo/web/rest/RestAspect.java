package com.busnoseat.demo.web.rest;

import com.busnoseat.common.code.CommonRspCode;
import com.busnoseat.common.exception.RepositoryException;
import com.busnoseat.common.facade.abs.BaseResponse;
import com.busnoseat.common.util.ToStringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * The type Rest aspect.
 * @Description:
 * @author liheng
 * @Date 2016/3/8
 */
@Aspect
@Component
public class RestAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Controller aspect.
     */
    @Pointcut("execution(* com.busnoseat.demo.web.rest..*.*(..))")
    public void controllerAspect() {
    }

    /**
     * Around
     * 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
     *
     * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice
     * 执行完AfterAdvice，再转到ThrowingAdvice
     * @param pjp the pjp
     * @return object
     * @throws Throwable the throwable
     */
    @Around(value = "controllerAspect()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        //1.入参打印
        StringBuilder params = new StringBuilder();
        for (Object o : pjp.getArgs()) {
            params.append(ToStringUtil.toString(o));
        }
        String className = pjp.getSignature().getDeclaringTypeName();
        String methodName = pjp.getSignature().getName(); //2.打印出参，返回结果

        logger.info("request:{}.{}-{}", className, methodName, params.toString());

        BaseResponse response = new BaseResponse();
        try {
            response = (BaseResponse) pjp.proceed();
        } catch (RepositoryException e) {
            response.setRespCode(e.getErrCode());
            response.setRespMessage("仓储异常");
            logger.error("仓储异常", e);
        } catch (IllegalArgumentException e) {
            response.setRespCode(CommonRspCode.PARAMBUG.getCode());
            response.setRespMessage(e.getMessage());
            logger.error("请求参数不合法", e);
        } catch (Throwable e) {
            response.setRespCode(CommonRspCode.SYS_ERROR.getCode());
            response.setRespMessage("系统内部异常，请稍后重试");
            logger.error("系统异常", e);
        }
        //2.打印出参，返回结果
        logger.info("reponse:{}.{}-{}", className, methodName, ToStringUtil.toString(response));
        return response;

    }
}
