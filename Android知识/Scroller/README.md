# Scroller源码解析

## 一、简介

- Scroller类是滚动的一个封装类，可以实现匀速滚动，可以先加速后减速，可以先减速后加速等效果，所以Scroller可以帮我们实现很多的滑动效果。

- scrollBy()和scrollTo()：scrollBy(int x, int y)是滚动到（x，y）这个偏移量的点，他是相对于View的开始位置来滚动的。scrollBy(int x, int y)是相对于View的上一次位置作为基准滚动（x，y）的偏移量。

  ```java
  /**
    * Set the scrolled position of your view. This will cause a call to
    * {@link #onScrollChanged(int, int, int, int)} and the view will be
    * invalidated.
    * @param x the x position to scroll to
    * @param y the y position to scroll to
    */
  public void scrollTo(int x, int y) {
      if (mScrollX != x || mScrollY != y) {
          int oldX = mScrollX;
          int oldY = mScrollY;
          mScrollX = x;
          mScrollY = y;
          invalidateParentCaches();
          onScrollChanged(mScrollX, mScrollY, oldX, oldY);
          if (!awakenScrollBars()) {
              postInvalidateOnAnimation();
          }
      }
  }
  /**
    * Move the scrolled position of your view. This will cause a call to
    * {@link #onScrollChanged(int, int, int, int)} and the view will be
    * invalidated.
    * @param x the amount of pixels to scroll by horizontally
    * @param y the amount of pixels to scroll by vertically
    */
  public void scrollBy(int x, int y) {
      scrollTo(mScrollX + x, mScrollY + y);
  }
  ```

  

- 构造方法：

  ```java
  /**
    * Create a Scroller with the default duration and interpolator.
    */
  public Scroller(Context context) {
      this(context, null);
  }
  /**
    * Create a Scroller with the specified interpolator. If the interpolator is
    * null, the default (viscous) interpolator will be used. "Flywheel" behavior will
    * be in effect for apps targeting Honeycomb or newer.
    */
  public Scroller(Context context, Interpolator interpolator) {
      this(context, interpolator,
           context.getApplicationInfo().targetSdkVersion >= Build.VERSION_CODES.HONEYCOMB);
  }
  /**
    * Create a Scroller with the specified interpolator. If the interpolator is
    * null, the default (viscous) interpolator will be used. Specify whether or
    * not to support progressive "flywheel" behavior in flinging.
    */
  public Scroller(Context context, Interpolator interpolator, boolean flywheel) {
      mFinished = true;
      if (interpolator == null) {
          mInterpolator = new ViscousFluidInterpolator();
      } else {
          mInterpolator = interpolator;
      }
      mPpi = context.getResources().getDisplayMetrics().density * 160.0f;
      mDeceleration = computeDeceleration(ViewConfiguration.getScrollFriction());
      mFlywheel = flywheel;
      mPhysicalCoeff = computeDeceleration(0.84f); // look and feel tuning
  }
  ```

- startScroll()

  