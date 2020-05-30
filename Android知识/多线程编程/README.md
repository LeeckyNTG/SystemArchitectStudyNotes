# 多线程编程

## 一、线程基础

- 线程的状态：

  - New：新创建状态。线程被创建，还没有调用start方法，在线程运行之前还有一些基础工作要做。

  - Runnable：可运行状态。一旦调用start方法，线程就处于Runnable状态。一个可运行的线程可能正在运行也可能没在运行，这取决于操作系统给线程提供的运行时间。

  - Blocked：阻塞状态。表示线程被锁阻塞，他暂时不活动。

  - Waiting：等待状态。线程暂时不活动，并且不运行任何代码，小号最少的资源直到线程调度器重新激活它。

  - Timed waiting：超时等待状态。和等待时间不同的是，它是可以在指定的时间自行返回的。

  - Terminated：终止状态。表示当前线程已经执行完毕。导致线程终止有两种情况：第一种就是run方法执行完毕正常退出；第二种就是因为没有捕获异常而终止run方法。

    ![线程状态关系图](https://img-blog.csdnimg.cn/20200513104638563.jpg)

- 线程中断

  - 在Java的早期版本中有stop方法可以终止线程，但是这个方法现在已经被弃用了。interrupt方法可以用来请求中断线程。当一个线程调用interrupt方法时，线程的中断标识位将被置位，线程会不断的检测这个中断标识位，以判断线程是否应该被中断。

  - 安全的终止线程

    - ```java
      public class StopThread_1{
          public static void main(String [] args) throws InterruptedException{
              MoonRunner runnable = new MoonRunner();
              Thread thread = new Thread(runnable,"MoonThread");
              thread.start();
              TimeUnit.MILLISECONDS.sleep(10);
              thread.interrupt();
          }
          
          public static class MoonRunner implements Runnable {
              private long i;
              @Override
              public void run() {
                  while(!Thread.currentThread().isInterrupted()){
                      i++;
                      System.out.println("i="+i);
                  }
                  System.out.println("thread stop");
              }
          }
      }
      ```

    - ```java
      public class StopThread_2 {
          public static void main(String[] args) throws InterruptedException {
              MoonRunner runnable = new MoonRunner();
              Thread thread = new Thread(runnable, "MoonThread");
              thread.start();
              TimeUnit.MILLISECONDS.sleep(10);
              runnable.cancel();
          }
          public static class MoonRunner implements Runnable {
              private long i;
              private volatile boolean on = true;
              @Override
              public void run() {
                  while (on) {
                      i++;
                      System.out.println("i=" + i);
                  }
                  System.out.println("thread stop");
              }
              public void cancel() {
                  on = false;
              }
          }
      }
      ```

## 二、线程同步

- ​	重入锁与条件对象

  重入锁ReentrantLock是Java5.0引入的，就是支持重进入的锁，他表示该锁能够支持一个线程对资源重复加锁。用ReentrantLock保护代码块的结构如下：

  ```java
  Lock mLock = new ReentrantLock();
  mLock.lock();
  try{
      ...
  }
  finally{
      mLock.unlock();
  }
  ```

  该结构确保任何时刻只有一个线程进入临界区，临界区就是在同一时刻只能有一个任务访问的代码区。

  ```java
  /**
   * 生产者消费者模型
   */
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
  ```

  