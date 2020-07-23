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
  public class WeChatPay {.
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

- 同步方法

  Lock和Condition接口为程序员提供了高度的锁控制，然而大多数情况并不需要那样的控制。从Java1.0开始，Java中的每一个对象都有一个内部锁，如果一个方法使用synchronized关键字声明，那么对象的锁将保护整个方法。对于上面的例子我们可以使用synchronized改为如下：

  ```java
  public synchronized void transfer(int form, int to, int amount) throws InterruptedException {
          payLock.lock();
      while (accounts[form] < amount) {
          //wait,将一个线程添加带等待集合中
          wait();
      }
      //转账操作
      accounts[form] = accounts[form] - amount;
      accounts[to] = accounts[to] + amount;
      printAccount();  
      //notifyAll或者notify方法解除等待线程的等待状态
      notifyAll();
  } 
  ```

- 同步代码块

  基本使用方法如下，Object类型的lock对象放入同步代码块中，为的是使用Object类所持有的锁。同步代码块是非常脆弱的，通常不推荐使用。一般实现同步最好用java.util.concurrent包下提供的类，比如阻塞队列。如果同步方法适合你的程序，那么尽量使用同步方法，这样可以减少编写代码的数量，减少出错率。如果特别需要使用Lock/Condition结构提供的独有特性时，才使用Lock/Condition。

  ```java
  public class Alipay{
      private double [] accounts;
      private Object lock = new Object();
      public Alipay(int n,double money){
          accounts = new double[n];
          for(int i=0; i<accounts.length; i++){
              accounts[i] = money;
          }
      }
      public void transfer(int from, int to, int amount){
          synchronized(lock){
              //转账操作
              accounts[from] = accounts[from] - amount;
              accounts[to] = accounts[to] + amount;
          }
      }
  }
  ```

- volatile

  有时仅仅为了读写一个或者两个实例域就使用同步的话，显得开销过大；而volatile关键字为实例域的同步访问提供了免锁的机制，如果声明一个域为volatile，那么编译器和虚拟机就知道该域是可能被另一个线程并发更新的。

  - Java内存模型

    Java中的堆内存用来存储对象实例，堆内存是被所有线程共享的运行时内存区域，因此，它存在内存可见性的问题。而局部变量、方法定义的参数则不会在线程之间共享，不会有内存可见性问题，也不受内存模型的影响。Java内存模型定义了线程和主存之间的抽象关系：线程之间共享变量存储在主存中，每个线程都有一个私有本地内存，本地内存存储了该线程共享变量的副本。如下图所示，线程A和线程B之间要通信的话，必须经历下面两个步骤：

    1. 线程A把线程A本地内存中更新过的共享变量刷新到主存去。
    2. 线程B到主存中去读取线程A之前更新过的共享变量。

    ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200717162750311.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI4Njk1NTkz,size_16,color_FFFFFF,t_70#pic_center)

  - 原子性、可见性和有序性

    1. 原子性，对基本数据类型变量的读取和赋值操作时原子性操作，即这些操作是不可被中断的，那么执行结束，要么完全不执行。
    2. 可见性，是指线程之间的可见性，一个线程修改状态对另一个线程是可见的。也就是一个线程修改的结果，另一个线程马上就能看到。当一个共享变量被volatile修饰时，他会保证修改的值立即被更新到主存，所以对其他线程是可见的。当其他需要读取该数据时，其他线程会到主存去读取新值。而普工共享变量不能保证可见性，因为普通共享变量被修改后，并不会立即写入到主存中，何时被写入也不确定。
    3. 有序性，Java内存模型中允许编译器和处理器对指令进行重新排序，虽然重排序过程不会影响到单线程执行的正确性，但是会影响到多线程并发执行的正确性。这时可以通过volatile来保证有序性，除了volatile，也可以通过synchronized和Lock来保证有序性。

  - volatile关键字

    当一个共享变量被volatile修饰之后，其就具备了两个含义，一个是线程修改了变量的值时，变量的新值对其他线程是立即可见的。另一个就是禁止使用指令重排序。

  - volatile不保证原子性，如下代码，每次执行结果都不一样，就是因为inc++是三个操作，而volatile不保证原子性。

    ```java
    public class VolatileTest {
        public volatile int inc = 0;
        public void increase() {
            inc ++;
        }
        public static void main(String [] args) {
            final VolatileTest test = new VolatileTest();
            for (int i=0; i<10; i++) {
                new Thread() {
                    public void run() {
                        for (int j=0; j<1000; j++){
                            test.increase();
                        }
                    }
                }.start();
            }
        }
        //如果有子线程就让出资源，保证所有子线程都执行完
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(test.inc);
    }
    ```

  - 正确的使用volatile关键字

    synchronized关键字可以防止多可线程同时执行一段代码，那么这会很影响程序的执行效率。而volatile关键字在某些情况下性能优于synchronized。但是要注意volatile不能代替synchronized关键字，因为volatile关键字无法保证操作的原子性。通常来说，使用volatile必须具备以下两个条件：

    1. 对变量的写操作不会依赖当前值。
    2. 该变量没有包含在具有其他变量的不变式中。

  - volatile的使用场景

    1. 状态标志

       ```java
       volatile boolean shutdownRequested;
       ...
       public void shutdown(){
           shutdownRequested = ture;
       }
       
       public void doWork(){
           while (!shutdownRequested){
               ...
           }
       }
       ```

    2. 双重检查模式（DCL）

       ```java
       public class Singleton{
           private volatile static Singleton instance = null;
           public static Singleton getInstance() {
               if (instance == null) {
                   synchronized(Singleton.class) {
                       if (instance == null){
                           instance = new Singleton();
                       }
                   }
               }
               return instance;
           }
       }
       ```



## 三、阻塞队列

- 阻塞队列简介

  阻塞队列常用于生产者和消费者的场景，生产者是往队列里添加元素的线程，消费者是从队列里拿元素的线程。阻塞队列就是生产者存放元素的容器，而消费者也只从容器里拿元素。

  - 常见的阻塞场景
    1. 当队列中没有数据的情况下，消费者的所有线程都会被自动阻塞，直到有数据放入队列。
    2. 当队列中填满数据的情况下，生产者的所有线程都会被自动阻塞，直到队列中有空的位置。
  - BlockingQueue的核心方法
    1. 放入数据
       - offer（anObject）：表示如果可能的话，将anObjece加到BlockingQueue里。即如果BlockingQueue可以容纳，则返回true，否则返回false。
       - offer（E o,long timeout,TimeUnit init）:可以设定等待时间。如果在指定时间内还不往队列中加入BlockingQueue，则返回失败。
       - put（anObject）：将anObject加到BlockingQueue里。如果BlockingQueue没有空间，则调用此方法的线程阻断，直到BlockingQueue里面有空间再继续。
    2. 获取数据
       - poll（long time，TimeUnit unit）：从BlockingQueue中取出一个队首的对象。如果在指定时间内，队列一旦有数据可取，则立即返回队列中的数据：否则直到时间超时还没有数据可取，返回失败。
       - take()：取走BlockingQueue里面排在首位的对象。若BlockingQueue为空，则阻断进入等待状态，直到BlockingQueue有新的数据被加入。
       - drainTo()：一次性从BlockingQueue获取所有可用的数据对象（还可以指定获取数据的个数）。通过该方法，可以提升获取数据的效率；无须多次分批加锁或释放锁。