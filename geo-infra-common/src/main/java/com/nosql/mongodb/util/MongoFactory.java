package com.nosql.mongodb.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
@Component
public class MongoFactory {
    @Autowired
    private MongoObjectParams objectParams;
    public Map<String,Object> converObjectToParams(Object object) throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        Update update = new Update();
        Map<String,String> params = objectParams.createParams(object);
        String id = params.get("id");
        Set<Map.Entry<String,String>> sets = params.entrySet();
        Iterator<Map.Entry<String,String>> iteratos = sets.iterator();
        while(iteratos.hasNext()){
            Map.Entry<String,String> entry = iteratos.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if(!key.equals("id")){
                update.set(key,value);
            }
        }
        map.put("id",id);
        map.put("update",update);
        return map;
    }
}
