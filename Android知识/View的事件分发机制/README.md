# View的事件分发机制

## 一、源码解析Activity的构成

![Activity的构成](https://upload-images.jianshu.io/upload_images/7010367-4969918bb6d51d75.png?imageMogr2/auto-orient/strip|imageView2/2/w/530/format/webp)

- 点击事件用MotionEvent来表示，当一个事件产生后，事件最先传递到Activity。

- setContentView(int layoutResID)，这个方法是由Activity类里面提供的，他的具体实现是有两部分组成，一个是调用getWindow().setContentView(layoutResId)，另一个是调用initWindowDecorActionBar()方法。具体代码如下：

  ```java
  //Activity类里面的setContentView方法
  public void setContentView(@LayoutRes int layoutResID) {
      getWindow().setContentView(layoutResID);
      initWindowDecorActionBar();
  }
  
  /**
    *initWindowDecorActionBar的官方解释为：创建一个新的ActionBar，用视图初始化ActionBar，并设置mActionBar
    */
  private void initWindowDecorActionBar() {
      Window window = getWindow();
      window.getDecorView();
      if (isChild() || !window.hasFeature(Window.FEATURE_ACTION_BAR) || mActionBar != null) {
          return;
      }
  
      mActionBar = new WindowDecorActionBar(this);
      mActionBar.setDefaultDisplayHomeAsUpEnabled(mEnableDefaultActionBarUp);
  
      mWindow.setDefaultIcon(mActivityInfo.getIconResource());
      mWindow.setDefaultLogo(mActivityInfo.getLogoResource());
  }
  ```

- getWindow()获取到mWindow， mWindow 是Activity中的一个全局变量，他的赋值及其初始化在attach方法中：（mWindow = new PhoneWindow(this, window, activityConfigCallback);），也就是说mWindow是一个PhoneWindow对象。

  ```java
  final void attach(Context context, ActivityThread aThread,
                    Instrumentation instr, IBinder token, int ident,
                    Application application, Intent intent, ActivityInfo info,
                    CharSequence title, Activity parent, String id,
                    NonConfigurationInstances lastNonConfigurationInstances,
                    Configuration config, String referrer, IVoiceInteractor voiceInteractor,
                    Window window, ActivityConfigCallback activityConfigCallback, IBinder assistToken) {
      attachBaseContext(context);
      mFragments.attachHost(null /*parent*/);
      mWindow = new PhoneWindow(this, window, activityConfigCallback);
      mWindow.setWindowControllerCallback(this);
      mWindow.setCallback(this);
      mWindow.setOnWindowDismissedCallback(this);
      mWindow.getLayoutInflater().setPrivateFactory(this);
      ...
  }
  ```

## 二、源码解析View的事件分发机制

- 当点击事件产生后，事件首先会传递给当前的Activity，这时会调用Activity的dispatchTouchEvent()方法，具体的时间处理工作都是交给Activity中的PhoneWindow来完成，PhoneWindow再把事件处理工作交给DecorView，之后DecorView将工作交给根ViewGroup。

  ```java
  /**Activity中的dispatchTouchEvent()*/
  public boolean dispatchTouchEvent(MotionEvent ev) {
      if (ev.getAction() == MotionEvent.ACTION_DOWN) {
          onUserInteraction();
      }
      if (getWindow().superDispatchTouchEvent(ev)) {
          return true;
      }
      return onTouchEvent(ev);
  }
  ```

  

