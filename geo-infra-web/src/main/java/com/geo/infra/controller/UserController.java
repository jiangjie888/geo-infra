package com.geo.infra.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.geo.framework.domainmodel.QueryResult;
import com.geo.infra.controller.dto.UserDto;
import com.geo.infra.domain.User;
import com.geo.infra.service.IUserService;
import com.geo.mvc.support.filter.Auth;
import com.geo.mvc.support.filter.FastJson;
import com.geo.mvc.viewmodel.ExtResult;


@Controller 
@RequestMapping("/user/*")
public class UserController {
	//private static final Logger log = Logger.getLogger(UserController.class);
	
	//@Autowired
	//@SuppressWarnings("restriction")
	@Resource
    private IUserService userService; 
	
	//@Reference
    //private IUserService userService1; 
	
	
	@RequestMapping("/index")
	public ModelAndView index(HttpServletRequest request){
		ModelAndView modelAndView = new ModelAndView("/infra/user");
		return modelAndView;
		//return "redirect:/view/infra/user.jsp";  
	}
	
/*	@RequestMapping("/test4")
	public index index(ModelAndView view) {
	    view.setView(new RedirectView("/index", false));
	    return view;
	}*/
	
	//获取分页列表
	@RequestMapping("/getlist")
	@ResponseBody
	public QueryResult<User> getList(HttpServletRequest request){
		int start = 1;
		int limit = 10;
		String wherejpql = "";
		Object[] queryParams = null;
		LinkedHashMap<String, String> orderby = null;
		return userService.getList(start, limit, wherejpql, queryParams,orderby);
	}
	
	//根据RKEY获取实体
	//@RequestMapping(value = "/User", method = RequestMethod.GET, consumes="application/json")
	@RequestMapping("/get")
	@ResponseBody
	@Auth(name="用户明细信息",value="User/getEntity")
	public User getEntity(HttpServletRequest request){
		String rkey = "DCD1DC115FED4622BAB43C3085B05647";
		User entity = userService.getEntity(rkey);
		System.out.println(entity.getTableName()+"---"+entity.getPrimaryKey());
		return entity;
	}
	
	
	//保存或更新
	//@RequestMapping(value = "/User", method = RequestMethod.POST, consumes="application/json")
	//@RequestMapping(value = "/User", method = RequestMethod.PUT, consumes="application/json")
	@RequestMapping("/save")
	@ResponseBody
	@Auth(name="用户保存",value="User/saveOrUpdate")
	public ExtResult saveOrUpdate(@FastJson UserDto u){
		ExtResult result = new ExtResult();
		
		/*HashMap<String, Object> params =  this.getRequestMapSingle(request);
		Object rkey = params.get("rkey");
		Object username =params.get("username");*/
		/*User entity = null;
		if(StringUtils.isEmpty(rkey)==true){
			entity = new User();
			entity.setUsername(params.get("username").toString());
		} else {
			entity.setUsername(params.get("username").toString());
		}*/
		User entity = new User();
		entity.setUsername("test");
		userService.saveOrUpdate(entity);
		
		result.setSuccess(true);
		result.setMsg("ok");
		return result;
	}
		
    @SuppressWarnings("rawtypes")
	protected HashMap<String, Object> getRequestMapSingle(HttpServletRequest request) {  
        HashMap<String, Object> conditions = new HashMap<String, Object>();  
        Map map = request.getParameterMap();  
        for (Object o : map.keySet()) {  
            String key = (String) o;  
            conditions.put(key, ((String[]) map.get(key))[0]);  
        }  
        
    	// 参数Map
        /*Map properties = request.getParameterMap();
        // 返回值Map
        Map returnMap = new HashMap();
        Iterator entries = properties.entrySet().iterator();
        Map.Entry entry;
        String name = "";
        String value = "";
        while (entries.hasNext()) {
            entry = (Map.Entry) entries.next();
            name = (String) entry.getKey();
            Object valueObj = entry.getValue();
            if(null == valueObj){
                value = "";
            }else if(valueObj instanceof String[]){
                String[] values = (String[])valueObj;
                for(int i=0;i<values.length;i++){
                    value = values[i] + ",";
                }
                value = value.substring(0, value.length()-1);
            }else{
                value = valueObj.toString();
            }
            returnMap.put(name, value);
        }
        return returnMap;
        */
        return conditions;  
        
    }  
    
	//删除
	//@RequestMapping(value = "/User", method = RequestMethod.DELETE, consumes="application/json")
	@RequestMapping("/delete")
	@ResponseBody
	public void delete(HttpServletRequest request){
		String rkey = "DCD1DC115FED4622BAB43C3085B05647";
		userService.delete(rkey);
	}

	
	//测试dubbo 客户端调用
	//@RequestMapping(value = "/User", method = RequestMethod.GET, consumes="application/json")
	/*@RequestMapping("/dubbocust")
	@ResponseBody
	public ExtResult testDubbo(HttpServletRequest request){
		ExtResult result = new ExtResult();
		String rkey = "DCD1DC115FED4622BAB43C3085B05647";
		User entity = userService1.getEntity(rkey);
		System.out.println(entity.getTableName()+"---"+entity.getPrimaryKey());

		result.setSuccess(true);
		result.setMsg(entity.getName());
		return result;
	}*/
	
/*	public string loginCheck(@RequestParam String name,@RequestParam String password){
		System.out.println(name+" : "+password);  
        //return name+" : "+password;
        MyDog dog=new MyDog();
        dog.setName("小哈");dog.setAge("1岁");dog.setColor("深灰");
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString=objectMapper.writeValueAsString(dog);
        System.out.println(jsonString);
        return jsonString;
	}*/
	
}
