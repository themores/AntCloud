# AntCloud

“蚁云”,该项目由retrofit2.0+rxjava1.0结合使用封装的网络库。
#### 功能优势

>1.支持添加网络拦截器

>2.已经默认设置cookie持久化管理

>3.支持设置请求缓存，减少用户流量

>4.支持多interface-service

#### 导入
#### 初始化
一般都放在Application onCreate 方法中进行初始化
``` java
AntCloud.getInstance().init(context,baseUrl);
```
#### 使用

1.如何定义Service 参考[retrofit 官方](http://square.github.io/retrofit/)  
``` java
public interface ApiService {
    @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
```

2. 获取Service
``` java
ApiService service = AntCloud.getInstance().createService(Service.class);
```
3.发起网络请求,返回一个Observable类型
``` java
service.getTopMovie(0,10)
```

4.链接rxjava1.0，call 为抽象方法必须实现，如果你需要对一些error 做特殊处理可以重写error方法
``` java
AntCloud.getInstance().getSubcription().addSubscription(service.getTopMovie()
.compose(AntSchedulers.<HttpResult<List<Subject>>>defaultSchedulers())
.subscribe（new AntSubscriber<HttpResult<List<Subject>>>(this, true){
   @Override
    public void call(HttpResult<List<Subject>> listHttpResult) {

   }
}));
```
5.退出activity 或者控制取消订阅。
``` java
 AntCloud.getInstance().getSubcription().addSubscription(subscription);//添加
 AntCloud.getInstance().getSubcription().unSubscribe();//取消，一般在onDestory方法中调用
 ```
#### 关键类说明
1.AntSchedulers 对rxjava 线程转换做了一次封装。
``` java
public class AntSchedulers {

    /**
     * subscribeOn Schedulers.io()
     * <br/>
     * observeOn AndroidSchedulers.mainThread()
     *
     * @param <T>
     * @return
     */
    public static <T> Observable.Transformer<T, T> defaultSchedulers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}

```
2.AntSubscriber 继承 Subscriber
``` java
  public AntSubscriber() 
  public AntSubscriber(Context context) 
  public AntSubscriber(Context context, boolean mIsOpenToast) //第二个字断用于控制是否弹出toast
```
#### 参考资料
1.Rxjava 学习
[很全的Rxjava教程](https://mcxiaoke.gitbooks.io/rxdocs/content/)

#### 交流加群284430347

