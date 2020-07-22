# Retrofit源码解析

- ## retrofit.create()：

 	创建接口api的动态代理对象（create()返回api service动态代理对象，调用代理对象上的方法时，会触发代理对象上的invoke方法，这里面会封装好OKHttpCall对象，OKHttpCall的数据返回根据calladapter转换为Observable） 

```java
/**
 * 返回api service动态代理对象,调用代理对象上的方法时，会触发代理对象上的invoke方法，这里面会封装好OKHttpCall对	* 象，OKHttpCall的数据返回根据calladapter转换为Observable）
 */
public <T> T create(final Class<T> service) {
    //验证传进来的参数是一个没有继承其他接口的接口
    Utils.validateServiceInterface(service);
    if (validateEagerly) {
      //对声明的接口文件里面的方法进行验证
      eagerlyValidateMethods(service);
    }
    return (T) Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
          private final Platform platform = Platform.get();
             @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
              throws Throwable {
            	 // 判断
                 if (method.getDeclaringClass() == Object.class) {
              		return method.invoke(this, args);
                  }
                 if (platform.isDefaultMethod(method)) {
              		return platform.invokeDefaultMethod(method, service, proxy, args);
            	}
                 ServiceMethod<Object, Object> serviceMethod =
                    (ServiceMethod<Object, Object>) loadServiceMethod(method);
            	OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);  
                 //将OkHttpCall对象通过适配器转换为Retrofit2.Call对象
     			return serviceMethod.adapt(okHttpCall);
          	}
     });
 }
```

- ## retrofit的核心是动态代理：

```java
/**
 * 将接口传进来，实现接口的方法最后将方法里面的参数和方法调用InvocationHandler方法回调回来。
 * service.getClassLoader():获取类加载器
 * new Class<?>[] { service }:提供一系列的接口，retrofit只提供一个。
 * new InvocationHandler()：回调方法
 */
Proxy.newProxyInstance(service.getClassLoader(), new Class<?>[] { service },
        new InvocationHandler() {
          private final Platform platform = Platform.get();
             @Override public Object invoke(Object proxy, Method method, @Nullable Object[] args)
              throws Throwable {
            	 // If the method is a method from Object then defer to normal invocation.
                 if (method.getDeclaringClass() == Object.class) {
              		return method.invoke(this, args);
                 }
                 if (platform.isDefaultMethod(method)) {
              		return platform.invokeDefaultMethod(method, service, proxy, args);
            	 }
                 //将接口方法的接口信息分解（包括注解，参数注解等）
                 ServiceMethod<Object, Object> serviceMethod =
                    (ServiceMethod<Object, Object>) loadServiceMethod(method);
                 //生成OkHttpCall对象
            	 OkHttpCall<Object> okHttpCall = new OkHttpCall<>(serviceMethod, args);     
     			return serviceMethod.adapt(okHttpCall);
          	}
        });
```

- ##  ServiceMethod ：

    核心处理类 ，解析方法和注解，生成HttpRequest（toRequest方法；创建responseConverter（将response流转换为String或者实体类）； 创建callAdapter（转换为rxjava observable） 

- ##  OKHttpCall ：

   封装okhttp3的调用 

- ##  Call 对象

   调用execute或者enqueue方法，前者是同步请求，后者是异步请求，再方法里面会调用Okhttp的网络请求方法。 

- ## 总结

   Retrofit就是一个封装了Http请求的框架，底层的网络请求都是使用的Okhttp，本身只是简化了用户网络请求的参数配置等，还能与Rxjava相结合，使用起来更加简便。 