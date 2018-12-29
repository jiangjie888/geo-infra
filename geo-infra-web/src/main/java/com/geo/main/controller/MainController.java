package com.geo.main.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.geo.infra.controller.dto.UserDto;
import com.geo.mvc.support.filter.FastJson;
import com.geo.mvc.viewmodel.ExtResult;

@Controller 
@RequestMapping("/main/*")
public class MainController {
    public static final String SESSION_USERID = "ACCOUNT";  
    public static final String SESSION_AUTHS = "PERMISSIONS"; 
    
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/main/index");
		return modelAndView;
		//return "redirect:/index.jsp";  
	}
	
	
	@RequestMapping("/login")
	public String login(){
		return "redirect:/login.jsp";  
	}
	
	@RequestMapping("/logincheck")
	@ResponseBody
	public ExtResult loginCheck(@FastJson UserDto u,HttpServletRequest request){
		ExtResult result = new ExtResult();

		HttpSession session = request.getSession();
        session.setAttribute(SESSION_USERID,"admin");
        
        Set<String> permissions = new HashSet<String>();
        permissions.add("User/getEntity");
        permissions.add("User/saveOrUpdate");
        
        session.setAttribute(SESSION_AUTHS,permissions);
        
		System.out.println(u.getUsername()+u.getPassword());
		
		result.setSuccess(true);
		result.setMsg(u.getUsername()+u.getPassword());
		return result;
	}
	
	
	//@RequestSecurity
    @RequestMapping(value = "loginout", method = { RequestMethod.GET,RequestMethod.POST })
    public String loginOut(HttpSession session)
    {
        session.invalidate();
        return "redirect:/login.jsp";  
        //return "redirect:https://demo.testcas.com/cas-server/logout";
        //return "redirect:https://demo.testcas.com/cas-server/logout?service=http://localhost:8080/geo-infra-web/main/login";
    }
    
    @RequestMapping(value = "test", method = { RequestMethod.GET,RequestMethod.POST })
    public String test(HttpSession session)
    {
    	String result = "192.168.100.73";
    	
        return result;
    }
}
