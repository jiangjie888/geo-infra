package com.geo.mvc.viewmodel;


/**
 * @author jjie
 * 请求返回的标准格式
 */
public class ExtResult {
	private Boolean success;
	private String msg;
	private String rkey;
    
    public void setSuccess(Boolean success){
    	this.success = success;
    }
    
    public Boolean getSuccess(){
        return success;
    }
    
    public void setMsg(String msg){
    	this.msg = msg;
    }
    
    public String getMsg(){
        return msg;
    }
    
    public void setRkey(String rkey){
    	this.rkey = rkey;
    }
    
    public String getRkey(){
    	return rkey;
    }

}
