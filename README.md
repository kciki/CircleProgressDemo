﻿# CircleProgressDemo

  环形的自定义进度条

# 效果图

![效果预览](https://github.com/kciki/CircleProgressDemo/blob/master/gif/play.gif)

# Gradle快速集成

  在项目的build.gradle中添加如下代码:

```

allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```



  在moudle中的build.gradle添加依赖：

```

    dependencies {

             implementation 'com.github.kciki:CircleProgressDemo:v0.1'

     }

```

# 使用方法：

  在xml直接引用：

``` html

<com.example.circleprogressview.CircleProgressView
    android:id="@+id/circle_progress_view"
    android:layout_width="200dp"
    android:layout_height="200dp"
    app:start_angle="45"
    app:ring_width="12dp"
    app:start_color="#ff00ff"
    app:end_color="#00ffff"
    app:text_size="48sp"
    app:is_need_ring_bg="true"
    app:ring_bg_color="#989898"
    app:sweep_angle="180"/>

```



在代码中使用：

``` java

CircleProgressView circleProgressView = findViewById(R.id.circle_progress_view);
circleProgressView.startAnim();
```

# 属性说明

|属性名称|作用|
|:---|:---|
|start_color|进度条颜色渐变开始的颜色|
|end_color|进度条颜色渐变结束的颜色|
|text_color|中间文字的颜色|
|text_size|中间文字的百分比值的字体大小|
|ring_width|进度条环形的宽度|
|start_angle|进度条的起始角度0-360|
|sweep_angle|进度条扫过的角度0-360|
|is_need_ring_bg|进度条是否需要背景|
|ring_bg_color|进度条背景的颜色|



