# 去趣 QuQu Travel

一个模仿旅游 App 的 Android 示例项目，项目名为「去趣」。

## 当前页面

- 首页：首页 Banner、搜索框、热门玩法、精选目的地、今日推荐
- 目的地 Tab：城市/省份目的地入口
- 订单 Tab：订单空状态
- 我的 Tab：收藏、优惠券、客服入口

## 技术栈

- Java
- 原生 Android View
- Gradle Android Plugin 8.5.2
- minSdk 23
- targetSdk 35

## 如何运行

1. 使用 Android Studio 打开本仓库根目录。
2. 等待 Gradle Sync 完成。
3. 选择模拟器或真机运行 `app` 模块。

## 后续可优化方向

- 把 MainActivity 中的页面拆成 Fragment
- 底部 Tab 替换成 BottomNavigationView
- 增加圆角背景 drawable
- 接入真实旅游数据接口
- 首页图片替换成真实景点图片
