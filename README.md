# Taobao Union App 开发记录
## 参考资料  
  ButterKnife https://github.com/JakeWharton/butterknife
  
## 开发日志
  2020:10:21 创建项目，初始化环境，绘制mainActivity,使用BottomNavigationView作为底部导航栏  
  2020:10:22 使用Retrofit框架实现 获取商品分类的功能  
  2020:10:23 获取商品分类后的UI显示(TabLayout ViewPager); 根据网络获取结果加载动态View; 实现网络错误时点击网络错误界面重新加载事件  
  2020:10:26 实现根据分类ID 获取数据，定义接口与回调接口，获取数据后根据状态回调UI接口  
  2020:10:27 数据回来后显示在RecycleView List上，ViewPager实现轮播图显示图片  
  2020:10:29 NestedScrollView 实现上滑，添加ToastUtils解决Toast重复弹出的问题，申请更小size的网络图片,解决NestedScrollView 嵌套RecyclerView 导致RecyclerView适配器一直为每个item创建onCreateViewHolder { newInnerHolder(itemView) },导致内存过大  
  2020:11:03 实现轮播图无限轮播功能，定义获取淘口令的接口 callback presenter presenterImpl  
  
  
