package com.acfun.config;

import com.acfun.core.util.DateUtils;
import com.acfun.core.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.xiaoleilu.hutool.date.DateUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 请求日志监控
 * @author kwer
 */
@Aspect
@Component
@Order(1)
@Slf4j
public class ControllerAspect {

    private static final ThreadLocal<Date> BEGIN_TIME_THREAD_LOCAL =
            new NamedThreadLocal<>("ThreadLocal beginTime");
//    private static final ThreadLocal<LogInfo> logThreadLocal =
//            new NamedThreadLocal<>("ThreadLocal log");

    private static final ThreadLocal<String> CURRENT_USER = new NamedThreadLocal<>("ThreadLocal user");
    /**
     * 日志类型(info:入库,error:错误)
     */
    private static final String INFO = "info";
    /**
     * 日志类型(info:入库,error:错误)
     */
    private static final String ERROR = "error";

    @Autowired(required = false)
    private HttpServletRequest request;

    @Pointcut("@annotation(io.swagger.annotations.ApiOperation)")
    public void ApiOperation() {
    }

    @Before("ApiOperation()")
    public void before() {
        Date beginTime = DateUtil.date();
        BEGIN_TIME_THREAD_LOCAL.set(beginTime);
        //debug模式下 显式打印开始时间用于调试
        if (log.isDebugEnabled()) {
            log.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }

        // 可添加读取session或者token中的用户
      //  CURRENT_USER.set(TokenUtil.getUserIdByRequest());

    }


    @AfterReturning(pointcut = "ApiOperation()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        saveLogInfo(joinPoint, result, null);
    }

    private void saveLogInfo(JoinPoint joinPoint, Object result, String errorInfo) {
        //日志类型(info:入库,error:错误)
        String title = "";
        //请求的IP
        String remoteAddr = request.getRemoteAddr();
        //请求的方法类型(post/get)
        String method = request.getMethod();
        try {
            title = getControllerMethodDescription2(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // debu模式下打印JVM信息。
        //得到线程绑定的局部变量（开始时间）
        long beginTime = BEGIN_TIME_THREAD_LOCAL.get().getTime();
        //2、结束时间
        Date endTime = DateUtil.date();
        if (log.isDebugEnabled()) {
            log.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    request.getRequestURI(),
                    DateUtils.formatDateTime(endTime.getTime() - beginTime),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
        log.info("\n/====================================================================" +
                        "\n请求来源: {}" +
                        "\n请求URL: {}" +
                        "\n请求方式: {}" +
                        "\n响应方法: {},{}" +
                        "\n请求参数: {}" +
                        "\nAuthorization: {}" +
                        "\nUser-Agent: {}" +
                        "\n耗时: {} ms" +
                        "\n返回数据: {}" +
                        "\n====================================================================/",
                request.getRemoteAddr(),
                request.getRequestURL(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()),
                request.getHeader("Authorization"),
                request.getHeader("User-Agent"),
                (endTime.getTime() - beginTime),
                JSON.toJSONString(result)
        );

//        LogInfo logInfo = new LogInfo();
//        logInfo.setId(UuidUtils.creatUUID());
//        logInfo.setTitle(title);
//        if (errorInfo != null) {
//            logInfo.setType(ERROR);
//        } else {
//            logInfo.setType(INFO);
//        }
//        logInfo.setMethod(method);
//        logInfo.setRemoteAddr(remoteAddr);
//        logInfo.setRequestURL(request.getRequestURL().toString());
//        logInfo.setRequestParams(Arrays.toString(joinPoint.getArgs()));
//        logInfo.setDeclaringTypeName(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logInfo.setRequestHeader(StringUtils.getRequestHeader(request));
//        logInfo.setBeginTime(BEGIN_TIME_THREAD_LOCAL.get());
//        logInfo.setEndTime(endTime);
//        logInfo.setDuration(DateUtils.formatDateTime(endTime.getTime() - beginTime));
//        logInfo.setUserId(CURRENT_USER.get());
//        if (result != null) {
//            logInfo.setResult(JSON.toJSONString(result));
//        }
//        LogQueueUtil.getLogQueue().push(logInfo);
//
//        BEGIN_TIME_THREAD_LOCAL.remove();
//        CURRENT_USER.remove();
    }

    /**
     * 异常通知
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "ApiOperation()", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String errorInfo = StringUtils.getErrorInfoFromException(e);
        saveLogInfo(joinPoint, null, errorInfo);
    }


    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return 方法描述
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation apiOperation = method
                .getAnnotation(ApiOperation.class);
        return apiOperation.value();
    }


}

