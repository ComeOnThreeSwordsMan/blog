package com.acfun.core.util;
/**
 * session工具类
 *@Author an
 *@Date 2018-1-16
 **/
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpSession;

import com.acfun.common.constant.SessionKey;
import com.acfun.core.entity.user.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;

public class SessionUtil {  
    /** 
     * 全局删除id标示 
     */  
    public static String GLOB_DELETE_ID_VAL = "globDeleteIdVal";  
    /**
     * 获取request
     * @return
     */
    public static HttpServletRequest getRequest(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }  
      /**
       * 获取session
       * @return
       */
    public static HttpSession getSession(){  
        return getRequest().getSession();
    }  
      /**
       * 获取真实路径
       * @return
       */
    public static String getRealRootPath(){  
        return getRequest().getServletContext().getRealPath("/");  
    }  
      /**
       * 获取ip
       * @return
       */
    public static String getIp() {  
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder  
                .getRequestAttributes();  
        if(servletRequestAttributes!=null){  
            HttpServletRequest request = servletRequestAttributes.getRequest();  
            return request.getRemoteAddr();  
        }  
        return null;  
    }  
      /**
       * 获取session中的Attribute
       * @param name
       * @return
       */
    public static Object getSessionAttribute(String name){  
        HttpServletRequest request = getRequest();  
        return request == null?null:request.getSession().getAttribute(name);  
    }  
      /**
       * 设置session的Attribute
       * @param name
       * @param value
       */
    public static void setSessionAttribute(String name,Object value){  
        HttpServletRequest request = getRequest();  
        if(request!=null){  
            request.getSession().setAttribute(name, value);   
        }  
    }  
    /**
     * 获取request中的Attribute
     * @param name
     * @return
     */
    public static Object getRequestAttribute(String name){  
        HttpServletRequest request = getRequest();  
        return request == null?null:request.getAttribute(name);  
    }  

    /**
     * 设置request的Attribute
     * @param name
     * @param value
     */
    public static void setRequestAttribute(String name,Object value){  
        HttpServletRequest request = getRequest();  
        if(request!=null){  
            request.setAttribute(name, value);    
        }  
    }  
    /**
     * 获取上下文path
     * @return
     */
    public static String getContextPath() {  
        return getRequest().getContextPath();  
    }  
  /**
   * 删除session中的Attribute
   * @param name
   */
    public static void removeSessionAttribute(String name) {  
        getRequest().getSession().removeAttribute(name);  
    }
    public static SysUser getUser() {
        Object user = getSession().getAttribute(SessionKey.USER);
        if(user==null){
            return null;
        }
        return (SysUser)user;
    }

    public static Long getUserId() {
        Object user = getSession().getAttribute(SessionKey.USER);
        if(user==null){
            return null;
        }
        SysUser u =  (SysUser)user;
        return u.getId();
    }

    public static String getUserName() {
        Object user = getSession().getAttribute(SessionKey.USER);
        if(user==null){
            return null;
        }
        SysUser u =  (SysUser)user;
        return u.getUsername();
    }

    public static void setUserMap(Map<String, Object> map) {
        SysUser user = SessionUtil.getUser();
        if(user!=null){
            map.put(SessionKey.USER,user);
        }
    }
    public static void setUser(SysUser user) {
        getSession().setAttribute(SessionKey.USER,user);
    }

} 