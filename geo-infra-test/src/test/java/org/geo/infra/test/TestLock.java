package org.geo.infra.test;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {
    	Ticket ticket = new Ticket();
        new Thread(ticket, "窗口1售票").start();
        new Thread(ticket, "窗口2售票").start();
        new Thread(ticket, "窗口3售票").start();
    }

}

class Ticket implements Runnable {
    private int ticket = 20;
    private Lock lock = new ReentrantLock();

    @Override
    public void run() {
        while (true) {
            lock.lock();
            try {
                if (ticket > 0) {
                    Thread.sleep(20);
                    System.out.println(Thread.currentThread().getName()
                            + "，余票量：" + ticket--);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}