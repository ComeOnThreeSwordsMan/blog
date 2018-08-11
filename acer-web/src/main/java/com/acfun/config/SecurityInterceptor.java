package com.acfun.config;

import com.xiaoleilu.hutool.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限拦截器
 *
 * @author kwer
 * @Date 2018/4/23/023 15:04
 */
public class SecurityInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
// 1
        boolean b = hasPermission(handler);
        if (!b) {
            httpServletResponse.setStatus(HttpStatus.HTTP_UNAUTHORIZED);
            httpServletResponse.getWriter().write("{\"code\": 401, \"msg\": \"无权限访问\"｝");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {
//        System.out.printf("postHandle");
        // 2
    }


    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {
//   3
//     System.out.printf("postHandle");
    }


    /**
     * 权限校验
     *
     * @param handler
     * @return
     */
    private boolean hasPermission(Object handler) {
        return true;
    }
}
