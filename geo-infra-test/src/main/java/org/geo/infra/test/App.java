package org.geo.infra.test;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 *
 */
public class App{
   
	
	 /*@Override
	 public void run() {
	        while (true) {
	            synchronized (this) {
	                if (i == 0) {
	                    break;
	                }
	                DubboAction dat = new DubboAction();  
	                System.out.println("--------------------测试1111111------------------------------------");  
	                dat.SayHello();  
	                System.out.println(Thread.currentThread().getName() + "=========" + i--);
	            }
	        }
	        countDownLatch.countDown(); 
	 }*/

    public static void main( String[] args ) throws InterruptedException
    {
    	ClassLocationUtils.where(org.apache.logging.slf4j.Log4jLoggerFactory.class);
        System.out.println( "Hello World!" );
        
        //DubboAction dat = new DubboAction();  
        //System.out.println("--------------------测试1111111------------------------------------");  
        //dat.SayHello();  
        
/*        long startTime=System.currentTimeMillis();   //获取开始时间
        int i=0;  
        while(i++<1000){  
        	DubboAction dat = new DubboAction();  
            System.out.println("--------------------测试1111111------------------------------------");  
            dat.SayHello();  
            //Thread.sleep(3000);
        }
        long endTime=System.currentTimeMillis(); //获取结束时间
*/        
        
        //开启三个线程
    	int i = 1000;
    	int threadNumber = 3;     
    	final CountDownLatch countDownLatch = new CountDownLatch(threadNumber);  
    	
    	
        long startTime=System.currentTimeMillis();   //获取开始时间
        new Thread(new Runnable(){  
            public void run(){  
            	System.out.println("--------------------测试1111111------------------------------------");  
            	int i=0;  
                while(i++<500){   
                    System.out.println(Thread.currentThread().getName()+":A:"+i);  
                    DubboAction dat = new DubboAction(); 
                    dat.SayHello();  
                }  
                countDownLatch.countDown();  
            }  
        }).start();  
  
        new Thread(new Runnable(){            
            public void run() {  
            	System.out.println("--------------------测试22222------------------------------------");  
            	int j=0;  
                while(j++<500){  
                    System.out.println(Thread.currentThread().getName()+":B:"+j);   
                    DubboAction dat = new DubboAction(); 
                    dat.SayHello();  
                }  
                countDownLatch.countDown();  
            }  
        }).start();   
        
         try {  
            countDownLatch.await();  
        } catch (InterruptedException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }     

        long endTime=System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
}
