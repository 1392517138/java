## 动态代理简单实现

[TOC]

### 一、反射

●Reflection (反射)是被视为动态语言的关键，反射机制允许程序在执行期
借助于Reflection API取得任何类的内部信息，并能直接操作任意对象的内
部属性及方法。

●加载完类之后，在堆内存的方法区中就产生了一个Class类型的对象(一个中
类只有一个Class对象)，这个对象就包含了完整的类的结构信息。我们可
以通过这个对象看到类的结构。**这个对象就像一面镜子，透过这个镜子看**
**到类的结构，所以，我们形象的称之为:反射。**
**正常方式：	引入需要的“包类”名称 -》通过new实例化-》取得实例化对象**

**反射方式：实例化对象-》getClass()方法-》得到完整的“包类”名称**

框架 = 反射 + 注解 + 设计模式

### 二、**反射机制提供的功能**

➢在运行时判断任意一-个对象所属的类
➢在运行时构造任意--个类的对象
➢在运行时判断任意一一个类所具有的成员变量和方法
➢在运行时获取泛型信息
➢在运行时调用任意一-个对象的成员变量和方法
➢在运行时处理注解
➢生成动态代理

#### 1.相关API

java.lang.Class:反射的源头
java.lang.reflect.Method
java.lang.reflect.Field
java.lang.reflect.Constructor

#### 2.Class类的理解

1.类的加载过程:
程序经过javac.exe命令以后，会生成一个或多个字节码文件(.class结尾)。接者我们使用java. exe命令对某个字节码文件进行解释运行。相当于将某个字节码文件加载到内存中。此过程就称为类的加载。加载到内存中的类，我们就称为运行时类，此运行时类，就作为Class的一 个实例。
2.换句话说，Class 的实例就对应者-个运行时类。
3.加载到内存中的运行时类，会缓存- 定的时间。在此时间之内，我们可以通过不同的方式
来获取此运行时类。

#### 4.创建类的对象的方式

方式一：new +构造器
方式二：要创建Xxx类的对象，可以考虑: Xxx、Xxxs、XxxFactory、 xxxBuilder类中 查看是否有静态方法的存在。可以调用其静态方法，创建xx对象。

方式三：通过反射

#### 5.Class实例可以是那些结构的说明

1.  class:外部类，成员(成员内部类，静态内部类)，局部内部类，匿名内部类
2.  interface: 接口
3.  []: 数组
4.  enum:枚举
5.  annotation: 注解@ interface
6.  primitive type:基本数据类型
7.  void

### 三、类的加载过程

类的加载（Load）将类的class文件读入内存，并为之创建一个java.lang.Class对象。此过程由类加载器完成

类的链接（Link）将类的二进制数据合并到**jre**中

类的初始化（Initialize）jvm负责对类进行初始化

●加载:将class文件 字节码内容加载到内存中，并将这些静态数据转换成方法区的运行时数据结构，然后生成一-一个代表这个类的java.lang.Class对象，作为方法区中类数据的访问入口(即引用地址)。所有需要访问和使用类数据只能通过这个Class对象。这个加载的过程需要类加载器参与。
●链接:将Java类的二进制代码合并到JVM的运行状态之中的过程。
➢验证:确保加载的类信息符合JVM规范，例如:以cafe开头， 没有安全方面的问题
➢准备:正式为类变量(static) 分配内存并**设置类变量默认初始值的阶段**，这些内存都将在方法区中进行分配。
➢解析:虚拟机常量池内的符号引用(常量名)替换为直接引用(地址)的过程。
●初始化:
➢**执行类构造器<clinit>()**方法的过程。**类构造器<clinit>()方法是由编译期自动收集类中所有类变量的赋值动作和静态代码块中的语句合并产生的。( 类构造器是构造类信息的，不是构造该类对象的构造器)。**
➢当初始化- -个类的时候，如果发现其父类还没有进行初始化，则需要先触发其父类的初始化。
➢虚拟机会保证一个类的<clinit>()方法在 多线程环境中被正确加锁和同步。

![image-20200613193722323](https://github.com/1392517138/imgRepository/blob/master/image-20200613193722323.png)

**类加载器作用是用来把类(class)装载进内存的。**JVM规范定义了如下类型的类的加载器。

![image-20200613193828389](https://github.com/1392517138/imgRepository/blob/master/image-20200613193828389.png)

### 四、动态代理（反射的动态性）

#### 一、反射的应用：动态代理

**●代理设计模式的原理:**
使用一个代理将对象包装起来,然后用该代理对象取代原始对象。任何对原始对象的调用都要通过代理。代理对象决定是否以及何时将方法调用转到原始对象上。
●之前为大家讲解过代理机制的操作，属于静态代理，特征是代理类和目标对象的类都是在编译期间确定下来，不利于程序的扩展。同时，每-一个代理类只能为一一个接口服务，这样-来程序开发中必然产生过多的代理。**最好可以通过一个代理类完成全部的代理功能。**

因为是在运行期间进行动态代理，所以我们这里用到反射

●动态代理是指客户通过代理类来调用其它对象的方法，并且是在程序运行时根据需要动态创建目标类的代理对象。
●动态代理使用场合:
➢调试
➢远程方法调用
**●动态代理相比于静态代理的优点:**
抽象角色中(接口)声明的所有方法都被转移到调用处理器一个集中的方法中处理，这样，我们可以更加灵活和统一的处理众多的方法。

### 五、静态代理举例

### 

```java
interface ClothFactory{
    void produceCloth();
}
//代理类
class ProxyClothFactory implements ClothFactory{
    private ClothFactory factory;//用被代理对象进行实例化
    public ProxyClothFactory(ClothFactory factory) {
        this.factory = factory;
    }
    @Override
    public void produceCloth() {
        System.out.println("代理工厂做一些准备工作");
        factory.produceCloth();
        System.out.println("代理工厂做一些后续的收尾工作");
    }
}
//被代理类
class NikeClothFactory implements ClothFactory{
    @Override
    public void produceCloth() {
        System.out.println("Nike工厂生产一批运动服");
    }
}
public class StaticProxyTest {
    public static void main(String[] args) {
        //创建被代理类对象
        NikeClothFactory nike = new NikeClothFactory();
        //创建代理类的对象
        ProxyClothFactory proxyClothFactory = new ProxyClothFactory(nike);
        proxyClothFactory.produceCloth();
    }
}
```

![image-20200620214828814](https://github.com/1392517138/imgRepository/blob/master/image-20200620214828814.png)

### 六、动态代理举例

**通过反射的Proxy.newProxyInstance**

我们来看一下

![image-20200620220444767](https://github.com/1392517138/imgRepository/blob/master/image-20200620220444767.png)

再来看一下InvocationHandler

![image-20200620220521362](https://github.com/1392517138/imgRepository/blob/master/image-20200620220521362.png)

invocationHandler的invoke方法

![image-20200620220550718](https://github.com/1392517138/imgRepository/blob/master/image-20200620220550718.png)

上代码：

```java
/**
 * @Author: piwenjing
 * @Description:
 * @Date: Created in 21:49 2020/6/20 0020
 */
interface Human{
    String getBelief();
    void eat(String food);
}
//被代理类
class SuperMan implements Human{

    @Override
    public String getBelief() {
        return "I belive I can fly";
    }

    @Override
    public void eat(String food) {
        System.out.println("我喜欢吃" + food);
    }
}

/**
 * 要想实现动态代理，需要解决的问题
 * 问题一：如何根据加载到内存中的被带离类，动态的创建一个代理类及其对象
 * 问题二：当通过代理类的对象调用方法a时，如何动态的去调用被带代理类中的同名方法a
 */
//针对问题一
class ProxyFactory{
    //调用此方法，返回一个代理类的对象，解决问题一
    public static Object getProxyInstance(Object obj){//obj:被代理类的对象
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
        myInvocationHandler.bind(obj);
        //根据反射中的这个Proxy
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(),obj.getClass().getInterfaces(),myInvocationHandler);
    }
}
class MyInvocationHandler implements InvocationHandler{
    private Object obj;//需要使用被代理类的对象进行赋值

    public void bind(Object obj) {
        this.obj = obj;
    }

    //当我们通过代理类的对象，调用方法a时，就会自动的调用如下的方法: invoke()
    //将被带离类要执行的方法a的功能就声明在invoke()中
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method:即为代理类对象调用的方法，次方法也就作为了被代理类对象要调用的方法
        //obj:被代理类的对象
        Object returnValue = method.invoke(obj, args);
        //上述方法的返回值就作为当前类中的invoke()的返回值
        return returnValue;
    }
}
public class ProxyTest {
    public static void main(String[] args) {
        SuperMan superMan = new SuperMan();
        //proxyInstance:代理类的对象
        Human proxyInstance = (Human)ProxyFactory.getProxyInstance(superMan);
        //当通过代理类对象调用方法时，会自动地调用被代理类中同名的方法
        System.out.println(proxyInstance.getBelief());
        proxyInstance.eat("12312312");
        System.out.println("******************************************");
        NikeClothFactory nikeClothFactory = new NikeClothFactory();
        ClothFactory proxyClothFactory = (ClothFactory)ProxyFactory.getProxyInstance(nikeClothFactory);
        proxyClothFactory.produceCloth();
    }
}
```

**运行结果：**

![image-20200620223641708](https://github.com/1392517138/imgRepository/blob/master/image-20200620223641708.png)



### 七、AOP与动态代理的举例

![image-20200620224128287](https://github.com/1392517138/imgRepository/blob/master/image-20200620224128287.png)

![image-20200620224201342](https://github.com/1392517138/imgRepository/blob/master/image-20200620224201342.png)



我们在前面代码的基础上来体验一下

![image-20200620224305739](https://github.com/1392517138/imgRepository/blob/master/image-20200620224305739.png)

修改如下：
![image-20200620224722612](https://github.com/1392517138/imgRepository/blob/master/image-20200620224722612.png)

![image-20200620224759066](https://github.com/1392517138/imgRepository/blob/master/image-20200620224759066.png)

**结果：**

![image-20200620224819450](https://github.com/1392517138/imgRepository/blob/master/image-20200620224819450.png)

好处就在于我们不用跟以前一样将共同代码抽出来后，还需要在原来的几个方法中显示地去调用这个方法

