package com.framework.mongodb;

import java.util.*;
import com.mongodb.*;

/** 
 * MongoDB更新封装实体 
 * 
 */  
public class MongoDBUpdate extends MongoDBEntity {  
  
    /** 
     * where查询Map 
     */  
    private Map<String, Object> whereMap;  
  
    /** 
     * value查询Map 
     */  
    private Map<String, Object> valueMap;  
  
    public Map<String, Object> getWhereMap() {  
        return whereMap;  
    }  
  
    public void setWhereMap(Map<String, Object> whereMap) {  
        this.whereMap = whereMap;  
    }  
  
    public Map<String, Object> getValueMap() {  
        return valueMap;  
    }  
  
    public void setValueMap(Map<String, Object> valueMap) {  
        this.valueMap = valueMap;  
    }  
}  