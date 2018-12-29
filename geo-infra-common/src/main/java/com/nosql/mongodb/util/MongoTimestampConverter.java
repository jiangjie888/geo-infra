package com.nosql.mongodb.util;

import org.springframework.core.convert.converter.Converter;
import java.sql.Timestamp;
import java.util.Date;
public class MongoTimestampConverter implements Converter<Date,Timestamp> {
    @Override
    public Timestamp convert(Date date) {
        if(null != date){
            return new Timestamp(date.getTime());
        }
        return null;
    }
}