package com.geo.mvc.support.filter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;  
import org.springframework.web.method.HandlerMethod;  
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.geo.mvc.viewmodel.ExtResult;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;  
import java.util.Set;  

//HandlerInterceptorAdapter是继承了HandlerInterceptor 的抽象类
public class AuthInterceptor extends HandlerInterceptorAdapter {  
    public static final String SESSION_USERID = "ACCOUNT";  
    public static final String SESSION_AUTHS = "PERMISSIONS"; 
    
	@Override  
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        boolean flag = true;  
        String fullPath = request.getRequestURI();
        //String queryString = request.getQueryString();
        String contextPath = request.getContextPath();
        String controllerPath = fullPath.replace(contextPath, "");
        System.out.println(fullPath);
        //ystem.out.println(queryString);
        System.out.println(contextPath);
        System.out.println(controllerPath);
        //公开访问的，不进行拦截控制
        String[] nohandle = { "/main/login", "/main/logincheck" };
        System.out.println(ArrayUtils.contains(nohandle, controllerPath));
        
        //URL:login.jsp是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制  
        if(ArrayUtils.contains(nohandle, controllerPath)){  
        	return true;
        }
        
        if (handler instanceof HandlerMethod) {  
            //Session检测  
            HttpSession session = request.getSession();
            Object account = session.getAttribute(SESSION_USERID);
            if(account==null){
            	//跳转到登录界面
            	/*response.setStatus(HttpStatus.FORBIDDEN.value());  
                response.setContentType("text/html; charset=UTF-8");  
                PrintWriter out=response.getWriter();  
                out.write("{\"result\":false,\"msg\":\"您的会话不存在，请先登录!\"}");  
                out.flush();  
                out.close();*/  
                request.getRequestDispatcher("/login.jsp").forward(request, response);  
                flag = false;
            } else {
	            Auth auth = ((HandlerMethod) handler).getMethod().getAnnotation(Auth.class);  
	            if (auth != null) {// 有权限控制的就要检查  
	            	if(authorizeCore(request,auth)==false){
	            		//ExtResult result = new ExtResult();
	            		response.setStatus(HttpStatus.FORBIDDEN.value());  
	                    response.setContentType("text/html; charset=UTF-8");  
	                    PrintWriter out=response.getWriter();  
	                    out.write("{\"result\":\false,\"msg\":\"抱歉,你不具有当前操作的权限!\"}");  
	                    out.flush();  
	                    out.close();  
	                    flag = false;  
	            	}
	            }  
            }
        }  
        return flag;  
    }  
    
    
    //权限判断业务逻辑
    protected boolean authorizeCore(HttpServletRequest request,Auth auth)
    {
    	HttpSession session = request.getSession();
        /*Object account = session.getAttribute(SESSION_USERID);
        if(account==null){
        	//request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);  
        	return false;
        }*/

        String v = auth.value();
        if (StringUtils.isEmpty(v)==false) {  
            Set<String> permissions = (Set<String>) session.getAttribute(SESSION_AUTHS);  
            if (!permissions.contains(v)) {// 提示用户没权限  
                return false;
            }  
        }  
        //記錄用戶操作
        return true;
    }
    
//    //③ 当前URI资源是否需要登录才能访问  
//    private boolean isURILogin (String requestURI, HttpServletRequest request) {  
//            if (request.getContextPath().equalsIgnoreCase(requestURI)  
//                    || (request.getContextPath() + "/").equalsIgnoreCase(requestURI))  
//                return true;  
//            for (String uri : INHERENT_ESCAPE_URIS) {  
//                if (requestURI != null && requestURI.indexOf(uri) >= 0) {  
//                    return true;  
//                }  
//            }  
//            return false;  
//        }  
}  