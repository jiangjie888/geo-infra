package com.framework.mongodb;

import com.mongodb.DBCursor;
/** 
 * 查询转换接口定义 
 * 
 */  
public interface MongoDBCursorPreparer {  
  
    DBCursor prepare(DBCursor cursor);  
}  

