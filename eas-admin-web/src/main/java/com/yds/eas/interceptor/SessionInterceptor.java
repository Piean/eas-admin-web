package com.yds.eas.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by mogu on 2016/2/19.
 */
public class SessionInterceptor implements HandlerInterceptor{
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (!sessionVerify(httpServletRequest)) {
            //判断session超时时是否是ajax请求时，如果是ajax请求响应头会有，x-requested-with
            if (httpServletRequest.getHeader("x-requested-with") != null && httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
                //在响应头设置session状态
                httpServletResponse.setHeader("sessionstatus", "sessionOut");
                httpServletResponse.getWriter().write("登录已失效，将调用JS自动跳转至登录页...");
                httpServletResponse.getWriter().write("<script>$(function (){window.location.replace('user/login.do');});</script>");
                return false;
            }
            httpServletResponse.sendRedirect("/user/login.do");
            return false;
        } else if (!httpServletRequest.getRequestURI().startsWith("/user/") && httpServletRequest.getHeader("referer") == null){
            httpServletResponse.sendRedirect("/user/login.do");
            return false;
        }else {
            return true;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    private boolean sessionVerify(HttpServletRequest request) {
//        Object UserInfo = request.getSession().getAttribute("user");
//        if (loginResult == null) {
//            return false;
//        }else {
//            sessionCacheService.setObject(request.getSession().getId(),loginResult,request.getSession().getMaxInactiveInterval());
//            return true;
//        }
        return true;
    }
}
