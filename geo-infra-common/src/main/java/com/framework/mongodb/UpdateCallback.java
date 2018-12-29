package com.framework.mongodb;

import com.mongodb.DBObject;;

/** 
 * MongoDB更新操作接口定义 
 * 
 */  
 public interface UpdateCallback {  
  
    DBObject doCallback(DBObject valueDBObject);  
}  