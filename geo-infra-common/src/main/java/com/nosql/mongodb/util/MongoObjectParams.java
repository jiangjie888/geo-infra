package com.nosql.mongodb.util;

import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
@Component
public class MongoObjectParams {
    private  String javaType = "java";
    /**
     * 获取查询的参数
     *
     * @param object
     * @return
     * @throws Exception
     */
    public  Map<String, String> createParams(Object object) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        setIntoParams(params,object, null);
        return params;
    }
    private  void setIntoParams(Map<String, String> params,Object object, String fatherName) throws IllegalAccessException,
            Exception {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field file : fields) {
            boolean accessFlag = file.isAccessible();
            file.setAccessible(true);
            String name = file.getName();
            Object value = file.get(object);
            if(file.getType().getName().equals("java.lang.Class")){
                break;
            }else if(file.getType().getName().contains(javaType)){
                if(fatherName != null && !fatherName.equals(" ")){
                    name = fatherName+"."+name;
                }
                if(value != null){
                    params.put(name, value+"");
                }
            }else{
                if(value != null){
                    setIntoParams(params,file.get(object), name);
                }
            }
            file.setAccessible(accessFlag);
        }
    }
}