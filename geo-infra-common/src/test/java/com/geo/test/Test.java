package com.geo.test;

import org.apache.logging.log4j.LogManager;  
import org.apache.logging.log4j.Logger;  
 
public class Test {  
    private static Logger logger = LogManager.getLogger("HelloWorld");  
    public static void main(String[] args){  
        Test.logger.info("hello,world");  
        Test.logger.error("There is a error here");  
    }  
 
} 