# ColaCloud

## 踩坑及解决方案

### Springboot项目能够成功启动但是无法访问templates/.html。

解决方案：只需要在依赖中添加thymeleaf依赖即可解决。

### Thymeleaf中$和#的应用场景

```html
<p th:text="${str}">test</p>
```

从模型中取出字符串用的是$。

### Vscode中新建html文件

在vscode中新建html文件发现文件是空白的，只需要输入！，然后按Tab即可。



### 注入RedisTemplate<String,object>时为null

注入的注解由@Autowired改成@Resource



## 设计思路

1. index.html：系统的一个欢迎页，所有人都可以访问。在这个欢迎页中，有各种超链接(例如查询图书，增加图书，删除图书)跳转到各个页面。需要对用户的登录状态进行检测，如果用户没有登录的话，不能进行跳转。
2. 用户权限设计：用户分成普通用户和管理员用户(用户的信息存放在一个表里面)，但是普通用户和管理员用户的权限是不同的。普通用户只有user:query的权限，而管理员用户具有user:update的权限。
3. redis中间件：把redis作为一个缓存的中间件，用户查询得到的书籍信息就存放在redis中，可以测试一下用户从redis中查询和从数据库中查询的性能差别。

## 数据库设计

### 用户表

用户编号-自增类型

用户名-username

密码-passowrd

权限-perms

### 图书表

书籍编号-自增类型

书籍名称-bookname

书籍价格-bookprice



