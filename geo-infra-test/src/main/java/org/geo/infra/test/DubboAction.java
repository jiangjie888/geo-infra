package org.geo.infra.test;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.geo.infra.domain.User;
import com.geo.infra.service.IUserService;
import com.geo.infra.service.impl.UserServiceImpl;

public class DubboAction {
	
	
	@SuppressWarnings("resource")
	public void SayHello(){   
		//User userlocal = (new UserServiceImpl()).getEntity("dafadsfdsafdsa");
		//System.out.println("--------------local:"+ userlocal.getName() +":"+new Date()+"-------------------");  
		
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "applicationConsumer.xml" });  
	    context.start();  
	    IUserService demoServer = (IUserService) context.getBean("userServiceImpl");  
	    User user = demoServer.getEntity("DCD1DC115FED4622BAB43C3085B05647");
	  
	    System.out.println("--------------client:"+ user.getName() +":"+new Date()+"-------------------");  
	}
}
