package org.geo.infra.test;

public class RunnableTest implements Runnable {
    private int tick = 12;

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (tick == 0) {
                    break;
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "=========" + tick--);
            }
        }
    }
    public static void main(String[] args) {
        RunnableTest runnableTest=new RunnableTest();
        new Thread(runnableTest).start();
        new Thread(runnableTest).start();


    }
}