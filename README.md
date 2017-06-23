# AntCloud

“蚁云”,该项目由retrofit2.0+rxjava1.0结合使用封装的网络库。
#### 功能优势
<pre>
1.支持添加网络拦截器
2.已经默认设置cookie持久化管理
3.支持设置请求缓存，减少用户流量
4.支持多interface-service
</pre>
#### 导入
#### 初始化
一般都放在Application onCreate 方法中进行初始化
<pre>
AntCloud.getInstance().init(context,baseUrl);
</pre>

#### 使用
<pre>
1.如何定义Service 参考retrofit 官方
http://square.github.io/retrofit/
<code>
public interface ApiService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
</code>

2. 获取Service
ApiService service = AntCloud.getInstance().createService(Service.class);

3.获取网络请求
service.getTopMovie(0,10)

4.链接rxjava1.0
<code>
service.getTopMovie().compose(compose(AntSchedulers.<HttpResult<List<Subject>>>defaultSchedulers()).subscribe(new new AntSubscriber<HttpResult<List<Subject>>>(this, true){
   @Override
    public void call(HttpResult<List<Subject>> listHttpResult) {

   }
});
</code>

5.退出activity 或者控制取消订阅。

 AntCloud.getInstance().getSubcription().addSubscription(subscription);//添加
 AntCloud.getInstance().getSubcription().unSubscribe();//取消，一般在onDestory方法中调用
</pre>
#### 类说明
