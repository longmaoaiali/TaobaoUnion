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
  2020:11:04 Retrofit网络框架实现获取淘口令，并显示数据在activity UI上; 实现自定义LoadingView转圈圈的UI
  2020:11:05 实现获取优惠券的网络请求，集成自定义的LoadingView, 定义精选功能的接口  
  2020:11:09 实现网络请求数据的显示，并且更新精选内容的Bean类(web请求返回数据与文档描述不符，根据实际返回结果进行bean类更新)  
  2020:11:10 实现特惠界面的数据请求与显示  
  2020:11:11 实现搜索界面的接口 实现层与view callback  
  2020:11:12 搜索界面调用presenter测试并调试解BUG; 自定义的TextFlowLayout控件显示推荐的搜索热词  
  2020:11:16 添加删除搜索历史click事件；searchResult recycler 显示; EditTexts搜素关键词  
  2020:11:17 添加EditText点击事件并发起搜索; 实现Fragment跳转和APP主题变灰(UI颜色矩阵饱和度变为0)  
  2020:11:18 引入开源工具库 RxTools，实现淘宝扫描二维码，扫描完成后跳转到领券界面  
  
