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

- ObjectAnimator是属性动画中最重要的类，创建一个ObjectAnimator只需要通过其静态工厂类直接返回一个ObjectAnimator对象。

