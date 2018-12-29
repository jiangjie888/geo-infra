package com.geo.framework.common;

import javax.persistence.Table;

import org.apache.commons.beanutils.*;

public class EOUtil {
	private Object obj;
	
	public EOUtil(Object obj){
		this.obj = obj;
	}
	
    
    /**
     * 得到所有可持久化字段的名称
     * @return 名称列表
     */
	public String[] getAttributeNames(){
		//通过PropertyUtils的describe方法把entity的所有属性与属性值封装进Map中
        //Map map = PropertyUtils.describe(obj);
        
		java.lang.reflect.Field[] fields = obj.getClass().getDeclaredFields();
		int len = fields.length;
		String [] arr = new String[len];
		for (int i=0; i< len; i++) {
			String name = fields[i].getName();
			arr[i] = name;
		}
		return  arr;
	}
	
	public String beanToString() {
		
//		 Class<?> sc = clazz.getSuperclass();  
//	        Field[] sfields = sc.getDeclaredFields();  
//	        if (sfields.length > 0) {  
//	            getParamAndValue(clazs, sc, isOutputNull);  
//	        }  
//	        Field[] fields = clazz.getDeclaredFields();  
//	        for (Field f : fields) {  
//	            f.setAccessible(true);  
//	            try {  
//	                if (null != f.get(clazs)||isOutputNull){  
//	                    sb.append(f.getName() + "=" + f.get(clazs) + "\n");  
//	                }  
//	            } catch (IllegalArgumentException | IllegalAccessException e) {  
//	                e.printStackTrace();  
//	            }  
//	        }  
		String result = "";
	    try{
	    	StringBuilder sb = new StringBuilder();
			String [] fields = this.getAttributeNames();
	        for (int i = 0; i < fields.length; i++) {
	        	String name = fields[i];
	            if(PropertyUtils.isReadable(obj, name) && PropertyUtils.isWriteable(obj, name)) {
	            	sb.append(name + "=" + BeanUtils.getProperty(obj, name) + "\n");  
	                //System.out.println(name + "___" + BeanUtils.getProperty(obj, name));
	            }
	        }
	        result = sb.toString();
	    } catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return result;
	}
	
	
	/**
     * 得到某个字段的值
     * @param attributeName 字段名
     * @return  值
     */
    public Object getAttributeValue(String attributeName) {
    	Object result = null;
    	try{
    		result = PropertyUtils.getProperty(obj, attributeName);
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
	}
	
    
    /**
     * 设置某个字段的值
     * @param attributeName 字段名
     * @param value  值
     */
    public void setAttributeValue(String attributeName , Object value){
    	try{
    		PropertyUtils.setProperty(obj, attributeName, value);
    	} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * 获得实体对应的表名
     * @return
     */
    public String getTableName() {
		String result = "";
		Table table = obj.getClass().getAnnotation(Table.class);  
		result = table.name();
		return result;
	}
}
