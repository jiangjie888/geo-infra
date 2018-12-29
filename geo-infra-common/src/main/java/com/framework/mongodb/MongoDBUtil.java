package com.framework.mongodb;

import java.util.*;

import org.bson.types.ObjectId;

import com.mongodb.*;
import com.mongodb.client.MongoDatabase;

/** 
 * MongoDBCRUD操作封装工具类 
 */  
/*public class MongoDBUtil {  
  
    *//** 
     * 按主键查询单个实体 
     * 
     * @param id            主键 
     * @param mongoDBCursor 查询实体 
     * @return DBObject 
     *//*  
    public static DBObject findById(MongoDBCursor mongoDBCursor, String id) {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("_id", new ObjectId(id));  
        mongoDBCursor.setFieldMap(map);  
        return findOne(mongoDBCursor);  
    }  
  
    *//** 
     * 按条件查询单个 
     * 
     * @param mongoDBCursor 查询实体 
     * @return DBObject 
     *//*  
    public static DBObject findOne(MongoDBCursor mongoDBCursor) {  
        DBObject dbObject = getMapped(mongoDBCursor.getFieldMap());  
        return MongoDBCommonUtil.getCollection(mongoDBCursor).findOne(dbObject);  
    }  
  
    *//** 
     * 查询全部 
     * 
     * @param mongoDBCursor 查询实体 
     *//*  
    public static List<DBObject> findAll(MongoDBCursor mongoDBCursor) {  
        mongoDBCursor.setFieldMap(new HashMap<String, Object>());  
        return find(mongoDBCursor);  
    }  
  
  
    *//** 
     * 按条件查询 
     * 支持skip，limit,sort 
     * 
     * @param mongoDBCursor 查询实体 
     *//*  
    public static List<DBObject> find(MongoDBCursor mongoDBCursor) {  
        DBObject dbObject = getMapped(mongoDBCursor.getFieldMap());  
        BasicDBObjectBuilder customField = null;  
        //自定义查询字段  
        if(mongoDBCursor.getCustomFieldMap() != null && mongoDBCursor.getCustomFieldMap().size() > 0) {  
            customField = new BasicDBObjectBuilder();  
            for(Map.Entry<String, Object> field : mongoDBCursor.getCustomFieldMap().entrySet()) {  
                customField.add(field.getKey(),field.getValue());  
            }  
        }  
        return find(mongoDBCursor, dbObject, customField);  
    }  
  
    *//** 
     * 查询（私有方法,检查是否含有skip，limit，sort） 
     * 
     * @param dbObject      查询条件 
     * @param mongoDBCursor 查询实体 
     *//*  
    private static List<DBObject> find(final MongoDBCursor mongoDBCursor, DBObject dbObject,BasicDBObjectBuilder customField) {  
        MongoDBCursorPreparer cursorPreparer = mongoDBCursor == null ? null : new MongoDBCursorPreparer() {  
        	@SuppressWarnings("unused")
			public DBCursor prepare(DBCursor dbCursor) {  
                if (mongoDBCursor == null) {  
                    return dbCursor;  
                }  
                if (mongoDBCursor.getLimit() <= 0 && mongoDBCursor.getSkip() <= 0 && mongoDBCursor.getSortObject() == null) {  
                    return dbCursor;  
                }  
                DBCursor cursorToUse = dbCursor;  
                if (mongoDBCursor.getSkip() > 0) {  
                    cursorToUse = cursorToUse.skip(mongoDBCursor.getSkip());  
                }  
                if (mongoDBCursor.getLimit() > 0) {  
                    cursorToUse = cursorToUse.limit(mongoDBCursor.getLimit());  
                }  
                if (mongoDBCursor.getSortObject() != null) {  
                    cursorToUse = cursorToUse.sort(mongoDBCursor.getSortObject());  
                }  
                return cursorToUse;  
            }  
        };  
        return find(mongoDBCursor, dbObject, cursorPreparer,customField);  
    }  
  
    *//** 
     * 查询（私有方法，真正的查询操作） 
     * 
     * @param query       查询条件 
     * @param mongoDBCursor  查询实体 
     * @param cursorPreparer 查询转换接口 
     *//*  
    private static List<DBObject> find(MongoDBCursor mongoDBCursor, DBObject query, MongoDBCursorPreparer cursorPreparer,BasicDBObjectBuilder customField) {  
        DBCursor dbCursor = null;  
        if(customField == null) {  
            dbCursor = MongoDBCommonUtil.getCollection(mongoDBCursor).find(query);  
        } else {  
            dbCursor = MongoDBCommonUtil.getCollection(mongoDBCursor).find(query,customField.get());  
        }  
        if (cursorPreparer != null) {  
            dbCursor = cursorPreparer.prepare(dbCursor);  
        }  
        return dbCursor.toArray();  
    }  
  
    *//** 
     * Count查询 
     * 
     * @param mongoDBCursor 查询实体 
     * @return 总数 
     *//*  
    public static long count(MongoDBCursor mongoDBCursor) {  
        DBObject dbObject = getMapped(mongoDBCursor.getFieldMap());  
        return MongoDBCommonUtil.getCollection(mongoDBCursor).count(dbObject);  
  
    }  
  
    *//** 
     * 把参数Map转换DBObject 
     * 
     * @param map 查询条件 
     * @return DBObject 
     *//*  
    private static DBObject getMapped(Map<String, Object> map) {  
        DBObject dbObject = new BasicDBObject();  
        Iterator<Map.Entry<String, Object>> iterable = map.entrySet().iterator();  
        while (iterable.hasNext()) {  
            Map.Entry<String, Object> entry = iterable.next();  
            Object value = entry.getValue();  
            String key = entry.getKey();  
            if (key.startsWith("$") && value instanceof Map) {  
                BasicBSONList basicBSONList = new BasicBSONList();  
                Map<String, Object> conditionsMap = ((Map) value);  
//                Set<String> keys = conditionsMap.keySet();  
                for (String k : conditionsMap.keySet()) {  
                    Object conditionsValue = conditionsMap.get(k);  
                    if (conditionsValue instanceof Collection) {  
                        conditionsValue = convertArray(conditionsValue);  
                    }  
                    DBObject dbObject2 = new BasicDBObject(k, conditionsValue);  
                    basicBSONList.add(dbObject2);  
                }  
                value = basicBSONList;  
            } else if (value instanceof Collection) {  
                value = convertArray(value);  
            } else if (!key.startsWith("$") && value instanceof Map) {  
                value = getMapped(((Map) value));  
            }  
            dbObject.put(key, value);  
        }  
        return dbObject;  
    }  
  
    *//** 
     * 转换成Object[] 
     * 
     * @param value 待转换实体 
     * @return Object[] 
     *//*  
    private static Object[] convertArray(Object value) {  
        Object[] values = ((Collection) value).toArray();  
        return values;  
    }  
  
    *//** 
     * 添加操作 
     * 
     * @param mongoDBEntity 实体 
     *//*  
    public static void add(MongoDBEntity mongoDBEntity) {  
        DBObject dbObject = new BasicDBObject(mongoDBEntity.getFieldMap());  
        MongoDBCommonUtil.getCollection(mongoDBEntity).insert(dbObject);  
    }  
  
    *//** 
     * 批量处理添加操作 
     * 
     * @param list          批量字段数据 
     * @param mongoDBEntity 实体 
     *//*  
    public static void add(MongoDBEntity mongoDBEntity, List<Map<String, Object>> list) {  
        for (Map<String, Object> map : list) {  
            mongoDBEntity.setFieldMap(map);  
            add(mongoDBEntity);  
        }  
    }  
  
    *//** 
     * 删除操作 
     * 
     * @param mongoDBEntity 实体 
     *//*  
    public static void delete(MongoDBEntity mongoDBEntity) {  
        DBObject dbObject = new BasicDBObject(mongoDBEntity.getFieldMap());  
        MongoDBCommonUtil.getCollection(mongoDBEntity).remove(dbObject);  
    }  
  
    *//** 
     * 删除操作,根据主键 
     * 
     * @param id            主键 
     * @param mongoDBEntity 实体 
     *//*  
    public static void delete(MongoDBEntity mongoDBEntity, String id) {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("_id", new ObjectId(id));  
        mongoDBEntity.setFieldMap(map);  
        delete(mongoDBEntity);  
    }  
  
    *//** 
     * 删除全部 
     * 
     * @param mongoDBEntity 实体 
     *//*  
    public static void deleteAll(MongoDBEntity mongoDBEntity) {  
        MongoDBCommonUtil.getCollection(mongoDBEntity).drop();  
    }  
  
    *//** 
     * 修改操作 
     * 会用一个新文档替换现有文档,文档key结构会发生改变 
     * 比如原文档{"_id":"123","name":"zhangsan","age":12}当根据_id修改age 
     * value为{"age":12}新建的文档name值会没有,结构发生了改变 
     * 
     * @param mongoDBUpdate 更新实体 
     *//*  
    public static void update(MongoDBUpdate mongoDBUpdate) {  
        executeUpdate(mongoDBUpdate, new UpdateCallback() {  
            public DBObject doCallback(DBObject valueDBObject) {  
                return valueDBObject;  
            }  
        });  
    }  
  
    *//** 
     * 修改操作,使用$set修改器 
     * 用来指定一个键值,如果键不存在,则自动创建,会更新原来文档, 不会生成新的, 结构不会发生改变 
     * 
     * @param mongoDBUpdate 更新实体 
     *//*  
    public static void updateSet(MongoDBUpdate mongoDBUpdate) {  
        executeUpdate(mongoDBUpdate, new UpdateCallback() {  
            public DBObject doCallback(DBObject valueDBObject) {  
                return new BasicDBObject("$set", valueDBObject);  
            }  
        });  
    }  
  
    *//** 
     * 修改操作,使用$inc修改器 
     * 修改器键的值必须为数字 
     * 如果键存在增加或减少键的值, 如果不存在创建键 
     * 
     * @param mongoDBUpdate 更新实体 
     *//*  
    public static void updateInc(MongoDBUpdate mongoDBUpdate) {  
        executeUpdate(mongoDBUpdate, new UpdateCallback() {  
            public DBObject doCallback(DBObject valueDBObject) {  
                return new BasicDBObject("$inc", valueDBObject);  
            }  
        });  
    }  
  
    *//** 
     * 修改(私有方法) 
     * 
     * @param mongoDBUpdate  更新实体 
     * @param updateCallback 更新回调 
     *//*  
    private static void executeUpdate(MongoDBUpdate mongoDBUpdate, UpdateCallback updateCallback) {  
        DBObject whereDBObject = new BasicDBObject(mongoDBUpdate.getWhereMap());  
        DBObject valueDBObject = new BasicDBObject(mongoDBUpdate.getValueMap());  
        valueDBObject = updateCallback.doCallback(valueDBObject);  
        MongoDBCommonUtil.getCollection(mongoDBUpdate).update(whereDBObject, valueDBObject);  
    }  
  
  
    public static void main(String[] args) {  
        try {  
            //获取操作DB  
        	DB db = MongoDBCommonUtil.getDB();  
            MongoDBCursor mongoDBCursor = new MongoDBCursor();  
            mongoDBCursor.setDb(db);;     //赋值DB  
            mongoDBCursor.setCollectionName("lagd_data_dictionary");   //赋值集合名  
            //封装查询条件  
            Map<String, Object> fieldMap = new HashMap<String, Object>();  
            fieldMap.put("type","dataSource");  
            mongoDBCursor.setFieldMap(fieldMap);  
            //赋值skip  
            mongoDBCursor.setSkip(1);  
            //赋值limit  
            mongoDBCursor.setLimit(1);  
            //封装Sort  
            Map<String, Object> sortMap = new LinkedHashMap<String, Object>();  
            sortMap.put("key",1);  
            mongoDBCursor.setSort(sortMap);  
            //自定义查询字段  
            Map<String, Object> customFieldMap =  new LinkedHashMap<String, Object>();  
            customFieldMap.put("type","1");  
            customFieldMap.put("key","1");  
            customFieldMap.put("value","1");  
            mongoDBCursor.setCustomFieldMap(customFieldMap);  
            //查询  
            List<DBObject> result = MongoDBUtil.find(mongoDBCursor);  
            for(DBObject dbObject : result){  
                for(String key : dbObject.keySet()){  
                    System.out.println("键：" + key + ";  值：" + dbObject.get(key));  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    
}  */