

# Android动画

### 一、AlphaAnimation( 渐变动画 )

- AlphaAnimation类是Android系统中的透明变化动画类，用于控制View对象的透明度变化，该类继承于Animation。
- 参数说明：
  - fromAlpha：开始时刻的透明度，取值范围为（0-1）
  - toAlpha：结束时刻透明度，取值范围为（0-1）

```java
AlphaAnimation animation = new AlphaAnimation(1, 0);
image.startAnimation(animation);
animation.setFillAfter(true);   //动画结束后保持状态
animation.setDuration(2000);
```

### 二、RotateAnimation( 旋转动画 )

- RotateAnimation是Android系统中的旋转变化动画，用于控制View的旋转变化。
- 参数说明：
  - fromDegrees：旋转的起始角度
  - toDegrees：旋转结束角度
  - pivotXType：有参数：RELATIVE_TO_SELF相对于自己，RELATIVE_TO_PARENT相对于父控件
  - pivotXValue：对象所在点的X坐标的相对比例，取值范围为（0-1）
  - pivotYType：有参数：RELATIVE_TO_SELF相对于自己，RELATIVE_TO_PARENT相对于父控件
  - pivotYValue：对象所在点的Y坐标的相对比例，取值范围为（0-1）

```java
 //参数解释分别为：旋转起始角度，旋转结束角度，相对与自身，x轴方向的一半，相对于自身，y轴方向的一半
 RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, 										                   Animation.RELATIVE_TO_SELF, 0.5f);
 image.startAnimation(animation);
 animation.setFillAfter(true);   //动画结束后保持状态
 animation.setDuration(2000);
```

### 三、TranslateAnimation（移动动画）

- TranslateAnimation是Android系统中的移动动画，用于控制View的移动过度变化。
- 参数说明：
  - fromXDelta：动画开始时的X坐标
  - toXDelta：动画结束时的X坐标
  - fromYDelta：动画开始时的Y坐标
  - toYDelta：动画结束时的Y坐标

```java
//起始x轴，最终x轴，起始y轴，最终y轴
TranslateAnimation animation = new TranslateAnimation(0, 80, 0, 80);
image.startAnimation(animation);
animation.setFillAfter(true);   //动画结束后保持状态
animation.setDuration(2000);
```

### 四、ScaleAnimation（缩放动画）

- ScaleAnimation是Android系统中的缩放动画，用于控制View的缩放过度变化
- 参数变化：
  - fromX：开始时的X比例
  - toX：结束时的X比例
  - fromY：开始时的Y比例
  - toY：结束时的Y比例

```java
ScaleAnimation animation = new ScaleAnimation(1, 0.5f, 1, 0.5f);
image.startAnimation(animation);
animation.setFillAfter(true);   //动画结束后保持状态
animation.setDuration(2000);
```

### 五、混合动画（就是使用AnimationSet 共用动画补间）

```java
AnimationSet animationSet = new AnimationSet(true);////共用动画补间
animationSet.setDuration(1000);
AlphaAnimation alphaAnimationFrom = new AlphaAnimation(1, 0);
alphaAnimationFrom.setFillAfter(true);   //动画结束后保持状态
alphaAnimationFrom.setDuration(1000);
//参数解释分别为：旋转起始角度，旋转结束角度，相对与自身，x轴方向的一半，相对于自身，y轴方向的一半
RotateAnimation animation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
image.startAnimation(animation);
animation.setFillAfter(true);   //动画结束后保持状态
animation.setDuration(1000);
animationSet.addAnimation(alphaAnimationFrom);
animationSet.addAnimation(animation);
image.startAnimation(animationSet);

```

### 六、属性动画

- 简介：属性动画是在Android 3.0（API 11）以后才提供的一种全新动画模式，在属性动画之前Android提供了逐帧动画和补间动画，但是这两个动画都有一个通用的缺点就是只能作用在View上，无法对View的属性进行操作，特别是在自定义View的时候，我们要对某个Point进行动画设置等。

- 工作原理：在一定时间间隔内，通过不断对值进行改变，并不断将该值附给对象的属性，从而实现该属性上的动画效果，具体的工作原理如：

  ![](https://img-blog.csdnimg.cn/20200410144057663.jpg?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI4Njk1NTkz,size_16,color_FFFFFF,t_70)

  

- ValueAnimation类：
  
  - 定义：属性动画机制中最核心的一个类
    
  - 实现动画的原理： 通过不断控制 值 的变化，再不断手动赋给对象的属性，从而实现动画效果。
  
  - 重要方法：
  
    - ValueAnimator.ofInt（int start, int end） :在addUpdateListener监听中animation.getAnimatedValue()的值从start变为end
  
      ```java
      // 步骤1：设置动画属性的初始值 & 结束值
      // ofInt（）作用有两个
      // 1. 创建动画实例
      // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
      // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
      // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
      // 关于自定义插值器我将在下节进行讲解
      // 下面看看ofInt()的源码分析 ->>关注1
      ValueAnimator anim = ValueAnimator.ofInt(start, end);
      //步骤2：设置动画的播放各种属性
      anim.setDuration(2000);//设置动画延迟播放时间
      anim.setRepeatCount(1);//设置动画重复播放次数 = 重放次数+1，当设置播放次数为ValueAnimator.INFINITE时,动画无限重复
      // 设置重复播放动画模式
      // ValueAnimator.RESTART(默认):正序重放
      // ValueAnimator.REVERSE:倒序回放
      anim.setRepeatMode(ValueAnimator.RESTART);
      // 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
      // 设置 值的更新监听器
      // 即：值每次改变、变化一次,该方法就会被调用一次
      anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
          @Override
          public void onAnimationUpdate(ValueAnimator animation) {
              Integer currentValue = (Integer) animation.getAnimatedValue();
              // 获得改变后的值
              System.out.println(currentValue);
              // 输出改变后的值
              // 步骤4：将改变后的值赋给对象的属性值，下面会详细说明
              image.setAlpha((float)currentValue / 100f);
              // 步骤5：刷新视图，即重新绘制，从而实现动画效果
              image.setRotation((float)currentValue * 3.6f);
              image.requestLayout();
          }
      });
      anim.start();
      // 启动动画
      ```

    
  
  - ValueAnimator.ofFloat（float start, float end） :同上
  
    ```java
    // 步骤1：设置动画属性的初始值 & 结束值
    // ofInt（）作用有两个
    // 1. 创建动画实例
    // 2. 将传入的多个Int参数进行平滑过渡:此处传入0和1,表示将值从0平滑过渡到1
    // 如果传入了3个Int参数 a,b,c ,则是先从a平滑过渡到b,再从b平滑过渡到C，以此类推
    // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置，即默认设置了如何从初始值 过渡到 结束值
    // 关于自定义插值器我将在下节进行讲解
    // 下面看看ofInt()的源码分析 ->>关注1
    ValueAnimator anim = ValueAnimator.ofFloat(start, end);
    //步骤2：设置动画的播放各种属性
    anim.setDuration(2000);//设置动画延迟播放时间
    anim.setRepeatCount(1);//设置动画重复播放次数 = 重放次数+1，当设置播放次数为ValueAnimator.INFINITE时,动画无限重复
    // 设置重复播放动画模式
    // ValueAnimator.RESTART(默认):正序重放
    // ValueAnimator.REVERSE:倒序回放
    anim.setRepeatMode(ValueAnimator.RESTART);
    // 步骤3：将改变的值手动赋值给对象的属性值：通过动画的更新监听器
    // 设置 值的更新监听器
    // 即：值每次改变、变化一次,该方法就会被调用一次
    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Float currentValue = (Float) animation.getAnimatedValue();
            // 获得改变后的值
            System.out.println(currentValue);
            // 输出改变后的值
            // 步骤4：将改变后的值赋给对象的属性值，下面会详细说明
            image.setAlpha((float) currentValue / 100f);
            // 步骤5：刷新视图，即重新绘制，从而实现动画效果
            image.setRotation((float) currentValue * 3.6f);
            image.requestLayout();
        }
    });
    anim.start();
    // 启动动画
    ```
  
    
  
  - ValueAnimator.ofObject(TypeEvaluator evaluator, Object... values)：
  
    ```java
    @Override
    protected void onDraw(Canvas canvas) {
        if (currentPoint == null) {
            currentPoint = new Point(RADIUS, RADIUS);
            // 创建一个点对象(坐标是(70,70))
    
            // 在该点画一个圆:圆心 = (70,70),半径 = 70
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
            // (重点关注)将属性动画作用到View中
            // 步骤1:创建初始动画时的对象点  & 结束动画时的对象点
            Point startPoint = new Point(RADIUS, RADIUS);// 初始点为圆心(70,70)
            Point endPoint = new Point(700, 700);// 结束点为(700,1000)
    
            // 步骤2:创建动画对象 & 设置初始值 和 结束值
            ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
            // 参数说明
            // 参数1：TypeEvaluator 类型参数 - 使用自定义的PointEvaluator(实现了TypeEvaluator接口)
            // 参数2：初始动画的对象点
            // 参数3：结束动画的对象点
    
            // 步骤3：设置动画参数
            anim.setDuration(5000);
            // 设置动画时长
            // 步骤3：通过 值 的更新监听器，将改变的对象手动赋值给当前对象
            // 此处是将 改变后的坐标值对象 赋给 当前的坐标值对象
            // 设置 值的更新监听器
            // 即每当坐标值（Point对象）更新一次,该方法就会被调用一次
            anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    currentPoint = (Point) animation.getAnimatedValue();
                    // 将每次变化后的坐标值（估值器PointEvaluator中evaluate（）返回的Piont对象值）到当前坐标值对象（currentPoint）
                    // 从而更新当前坐标值（currentPoint）
    
                    // 步骤4：每次赋值后就重新绘制，从而实现动画效果
                    invalidate();
                    // 调用invalidate()后,就会刷新View,即才能看到重新绘制的界面,即onDraw()会被重新调用一次
                    // 所以坐标值每改变一次,就会调用onDraw()一次
                }
            });
    
            anim.start();
            // 启动动画
        } else {
            // 如果坐标值不为0,则画圆
            // 所以坐标值每改变一次,就会调用onDraw()一次,就会画一次圆,从而实现动画效果
    
            // 在该点画一个圆:圆心 = (30,30),半径 = 30
            float x = currentPoint.getX();
            float y = currentPoint.getY();
            canvas.drawCircle(x, y, RADIUS, mPaint);
        }
    
    }
    ```
  
- ObjectAnimator类：
  
  - 简介：ObjectAnimator继承自ValueAnimator，实现原理是直接对对象的属性进行改变操作，从而实现动画效果。
  
  -  与ValueAnimator的区别： ValueAnimator类是先改变值，然后手动赋值给对象的属性从而实现动画；是间接对对象属性进行操作， ObjectAnimator类是先改变值，然后自动赋值给对象的属性从而实现动画；是直接对对象属性进行操作。
  
    | 属性         | 作用                         | 数值类型 |
    | ------------ | ---------------------------- | -------- |
    | Alpha        | 控制View的透明度             | float    |
    | TranslationX | 控制X方向的位移              | float    |
    | TranslationY | 控制Y方向的位移              | float    |
    | ScaleX       | 控制X方向的缩放倍数          | float    |
    | ScaleY       | 控制Y方向的缩放倍数          | float    |
    | Rotation     | 控制以屏幕方向为轴的旋转度数 | float    |
    | RotationX    | 控制以X轴为轴的旋转度数      | float    |
    | RotationY    | 控制以Y轴为轴的旋转度数      | float    |
  
    ```java
    animatorAlpha = ObjectAnimator.ofFloat(image, "alpha", 1f, 0f, 1f);
    animatorAlpha.setDuration(5000);
    animatorAlpha.start();
    
    animatorRotation = ObjectAnimator.ofFloat(image, "rotation", 0f, 360f);
    animatorRotation.setDuration(5000);
    animatorRotation.start();
    
    float curTranslationX = image.getTranslationX();
    animatorTranslationX = ObjectAnimator.ofFloat(image, "translationX", curTranslationX, curTranslationX + 300, curTranslationX);
    animatorTranslationX.setDuration(5000);
    animatorTranslationX.start();
    
    float curTranslationY = image.getTranslationY();
    animatorTranslationY = ObjectAnimator.ofFloat(image, "translationY", curTranslationY, curTranslationY + 300, curTranslationY);
    animatorTranslationY.setDuration(5000);
    animatorTranslationY.start();
    
    animatorScaleX = ObjectAnimator.ofFloat(image, "scaleX", 1f, 3f, 1f);
    animatorScaleX.setDuration(5000);
    animatorScaleX.start();
    
    animatorScaleY = ObjectAnimator.ofFloat(image, "scaleY", 1f, 3f, 1f);
    animatorScaleY.setDuration(5000);
    animatorScaleY.start();
    
    animColor = ObjectAnimator.ofObject(view,"color",new ColorEvaluator(),"#0000FF","#FF0000");
    animColor.setDuration(5000);
    animColor.start();
    ```
  
- 插值器与估值器的区别：

| 类型                    | 定义                                           | 作用                                                         | 应用场景                           | 备注                                               |
| ----------------------- | ---------------------------------------------- | ------------------------------------------------------------ | ---------------------------------- | -------------------------------------------------- |
| 插值器（Interpolator）  | 一个辅助动画实现的接口                         | 设置属性值从初始值过渡到结束值的变化规律，如匀速，加速，减速等等，即确定了动画效果变化模式，如匀速加速变化等等 | 实现非线性运动的动画效果           | Android内置了9种插值器的实现，可自定义插值器的实现 |
| 估值器（TypeEvaluator） | 一个辅助动画插值器的接口（属性动画特有的属性） | 设置属性值从初始值到结束值的变化具体数值                     | 协助插值器实现非线性运动的动画效果 | Android内置了三种估值器的实现，可自定义估值器实现  |

### 七、代码实现

- github地址：[Android动画代码实现]( https://github.com/LeeckyNTG/Animation )

  