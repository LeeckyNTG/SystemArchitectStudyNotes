# Android系统启动

- ## init进程启动过程

  - 描述：init进程是Android系统中用户空间的第一个进程，进程号为1，作为第一个进程，它有着很重要的工作职责，比如创建Zygote和属性服务等。init进程是由多个源文件共同组成的，这些文件位于system/core/init中。

  - 启动电源以及系统启动
    - 当电源按下时引导芯片代码从预定义的地方（固化在ROM）开始执行。加载引导程序BootLoader到RAM中执行。
  - 引导程序BootLoader
    - BootLoader是系统启动引导程序，它的作用是把操作系统拉起来并运行。
  - Linux内核启动
    - 当内核启动时，设置缓存、被保护存储器、计划列表、加载驱动。在内核完成系统设置后，它将首先在系统文件中寻找init.rc文件，并启动init进程。
  - init进程启动
    - 主要用来初始化和启动**属性服务**，也用来启动Zygote进程。
  - 总结
    - 当按下电源时系统启动引导程序，引导程序启动Linux内核，在内核加载完后启动init进程。
  - 属性服务
    - 类似于Windows平台中的注册表管理器，用于记录用户的、软件的一些使用信息。系统重启时会根据之前的记录进行初始化工作。
    - 系统属性分为普通属性和控制属性。
      - 控制属性用于执行一些命令，比如开机的动画就使用这种属性。
      - 普通属性，存储加载一些基本信息，如手机型号，网络信息等。
  - Zygote进程
    - Zygote又被称为孵化器，在Android系统中DVM、ART、应用程序进程以及运行系统的关键服务的SystemServer进程哦都市由它创建的。它通过fork的形式来创建进程。
  - SystemServer：SystemServer进程主要用于创建系统服务，比如AMS、WMS和PMS等各种系统常见服务。

- ## Launcher启动过程

  - 描述：Launcher时系统启动的的最后一步，他是启动一个应用程序用来显示系统中已安装的应用程序。
  - 作用：
    - 作为Android系统的启动器，用于启动应用程序。
    - 作为Android系统桌面，用于显示和管理应用程序的快捷图标或者其他桌面组件。
  - 启动过程
    - 它的入口是：AMS的systemReady方法，他在SystemServer的startOtherServices方法中被调用。

- ## 总结：Android系统启动流程如下

  - 启动电源以及系统启动
  - 引导程序BootLoader
  - Linux内核启动
  - init进程启动
  - Zygote进程启动
  - SystemServer进程启动
  - Launcher启动

  

