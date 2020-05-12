# View的工作流程

View的工作流程，指的就是measure、layout和draw。其中measure用来测量View的宽高，layout用来确定View的位置，draw用来绘制View。

## 一、View的工作流程入口

- 每个Activity都对应一个窗口，这个窗口是PhoneWindow的实例，PhoneWindow对用的布局是DecorView，DecorView内部又分为两部分，一部分是ActionBar，另一部分是ContentParent，即Activity在setContentView()里面对应的布局。

