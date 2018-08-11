package com.acfun.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

/**
 * 认证过滤 验证非登录请求
 *
 * @author kwer
 * @date 2017年12月12日 10:18:56
 */
@Order(1)//越小越先执行
@WebFilter(filterName = "oauthFilter", urlPatterns = "/*")
@Slf4j
public class OauthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (checkUri(httpServletRequest)) {
//        if(true){//开发暂时使用
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            Resource resource = new ClassPathResource("static/index.html");
            httpServletResponse.getWriter().write(IOUtils.toString(resource.getInputStream(), "UTF-8"));
//            httpServletRequest.getRequestDispatcher("/tokenError").forward(servletRequest,servletResponse);
//            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/tokenError");
        }
    }


    @Override
    public void destroy() {

    }

    /**
     * 需要忽略的请求
     *
     * @param uri
     * @return
     */
    private boolean isIgnoredTokenUri(String uri) {
        String pattern = "(/swagger.*)|(/favicon.ico)|(/v2.*)|(/.*.js)|(/.*.css)|(/.*.png)|(/.*.map)|(/.*.html)|(/.*.gif)|(.*download.*)|(/.*.svg)|(/.*.jpg)";
        return Pattern.matches(pattern, uri);
    }

    /**
     * 校验请求是否有效
     *
     * @param httpServletRequest
     * @return
     */
    private boolean checkUri(HttpServletRequest httpServletRequest) {
        String uri = httpServletRequest.getRequestURI();
        if (!isIgnoredTokenUri(uri)) {
            log.info("监听请求:{}", uri);
        }
        return true;
    }

    /***
     * 获取 request 中 json 字符串的内容
     *
     * @param request
     * @return : <code>byte[]</code>
     * @throws IOException
     */
    public static String getRequestJsonString(HttpServletRequest request)
            throws IOException {
        String submitMehtod = request.getMethod();
        // GET
        if (submitMehtod.equals("GET")) {
            return new String(request.getQueryString().getBytes("iso-8859-1"),"utf-8").replaceAll("%22", "\"");
            // POST
        } else {
            return getRequestPostStr(request);
        }
    }

    /**
     * 描述:获取 post 请求的 byte[] 数组
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static byte[] getRequestPostBytes(HttpServletRequest request)
            throws IOException {
        int contentLength = request.getContentLength();
        if(contentLength<0){
            return null;
        }
        byte buffer[] = new byte[contentLength];
        for (int i = 0; i < contentLength;) {

            int readlen = request.getInputStream().read(buffer, i,
                    contentLength - i);
            if (readlen == -1) {
                break;
            }
            i += readlen;
        }
        return buffer;
    }

    /**
     * 描述:获取 post 请求内容
     * <pre>
     * 举例：
     * </pre>
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestPostStr(HttpServletRequest request)
            throws IOException {
        byte buffer[] = getRequestPostBytes(request);
        String charEncoding = request.getCharacterEncoding();
        if (charEncoding == null) {
            charEncoding = "UTF-8";
        }
        return new String(buffer, charEncoding);
    }

}