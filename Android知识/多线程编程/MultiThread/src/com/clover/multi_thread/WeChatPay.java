package com.clover.multi_thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WeChatPay {
    private double[] accounts;
    private Lock payLock;
    private Condition condition;
    public WeChatPay(int n, double money) {
        accounts = new double[n];
        payLock = new ReentrantLock();
        //得到条件
        condition = payLock.newCondition();
        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = money;
        }
    }
    public void transfer(int form, int to, int amount) throws InterruptedException {
        payLock.lock();
        try {
            while (accounts[form] < amount) {
                //wait
                condition.await();
            }
            //转账操作
            accounts[form] = accounts[form] - amount;
            accounts[to] = accounts[to] + amount;
            printAccount();
            condition.signalAll();
        } finally {
            payLock.unlock();
        }
    }

    public void printAccount() {
        for (int i = 0; i < accounts.length; i++) {
            System.out.println(i + "账号的余额为：" + accounts[i]);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WeChatPay weChatPay = new WeChatPay(3, 100);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    weChatPay.transfer(0, 1, 150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        TimeUnit.MILLISECONDS.sleep(10);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    weChatPay.transfer(2, 0, 80);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
