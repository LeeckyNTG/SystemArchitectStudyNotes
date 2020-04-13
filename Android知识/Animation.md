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
    -  ValueAnimator.ofInt（int values） 
    -  ValueAnimator.ofFloat（float values） 
    -  ValueAnimator.ofObject（int values） 