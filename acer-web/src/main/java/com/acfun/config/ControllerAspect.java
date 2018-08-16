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
 * 璇锋眰鏃 ュ 織鐩 戞帶  
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
     * 鏃ュ織绫诲瀷(info:鍏ュ簱,error:閿欒)
     */
    private static final String INFO = "info";
    /**
     * 鏃ュ織绫诲瀷(info:鍏ュ簱,error:閿欒)
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
        //debug妯″紡涓� 鏄惧紡鎵撳嵃寮�濮嬫椂闂寸敤浜庤皟璇�
        if (log.isDebugEnabled()) {
            log.debug("寮�濮嬭鏃�: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }

        // 鍙坊鍔犺鍙杝ession鎴栬�卼oken涓殑鐢ㄦ埛
      //  CURRENT_USER.set(TokenUtil.getUserIdByRequest());

    }


    @AfterReturning(pointcut = "ApiOperation()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        saveLogInfo(joinPoint, result, null);
    }

    private void saveLogInfo(JoinPoint joinPoint, Object result, String errorInfo) {
        //鏃ュ織绫诲瀷(info:鍏ュ簱,error:閿欒)
        String title = "";
        //璇锋眰鐨処P
        String remoteAddr = request.getRemoteAddr();
        //璇锋眰鐨勬柟娉曠被鍨�(post/get)
        String method = request.getMethod();
        try {
            title = getControllerMethodDescription2(joinPoint);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // debu妯″紡涓嬫墦鍗癑VM淇℃伅銆�
        //寰楀埌绾跨▼缁戝畾鐨勫眬閮ㄥ彉閲忥紙寮�濮嬫椂闂达級
        long beginTime = BEGIN_TIME_THREAD_LOCAL.get().getTime();
        //2銆佺粨鏉熸椂闂�
        Date endTime = DateUtil.date();
        if (log.isDebugEnabled()) {
            log.debug("璁℃椂缁撴潫锛歿}  URI: {}  鑰楁椂锛� {}   鏈�澶у唴瀛�: {}m  宸插垎閰嶅唴瀛�: {}m  宸插垎閰嶅唴瀛樹腑鐨勫墿浣欑┖闂�: {}m  鏈�澶у彲鐢ㄥ唴瀛�: {}m",
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                    request.getRequestURI(),
                    DateUtils.formatDateTime(endTime.getTime() - beginTime),
                    Runtime.getRuntime().maxMemory() / 1024 / 1024,
                    Runtime.getRuntime().totalMemory() / 1024 / 1024,
                    Runtime.getRuntime().freeMemory() / 1024 / 1024,
                    (Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory() + Runtime.getRuntime().freeMemory()) / 1024 / 1024);
        }
        log.info("\n/====================================================================" +
                        "\n璇锋眰鏉ユ簮: {}" +
                        "\n璇锋眰URL: {}" +
                        "\n璇锋眰鏂瑰紡: {}" +
                        "\n鍝嶅簲鏂规硶: {},{}" +
                        "\n璇锋眰鍙傛暟: {}" +
                        "\nAuthorization: {}" +
                        "\nUser-Agent: {}" +
                        "\n鑰楁椂: {} ms" +
                        "\n杩斿洖鏁版嵁: {}" +
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
     * 寮傚父閫氱煡
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
     * 鑾峰彇娉ㄨВ涓鏂规硶鐨勬弿杩颁俊鎭� 鐢ㄤ簬Controller灞傛敞瑙�
     *
     * @param joinPoint 鍒囩偣
     * @return 鏂规硶鎻忚堪
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ApiOperation apiOperation = method
                .getAnnotation(ApiOperation.class);
        return apiOperation.value();
    }


}

