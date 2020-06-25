## JAVA8新特性——一文学懂

[TOC]



### 一、新特性概览

![image-20200621141741106](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200621141741106.png)

●速度更快
●代码更少(增加了新的语法: **Lambda 表达式**
●强大的**Stream API**
●便于并行
●最大化减少空指针异常: Optional
●Nashorn引擎，允许在JVM上运行JS应用

**并行流与串行流**
**并行流**就是把一个内容分成多个数据块，并用不同的线程分别处理每个数据块的流。相比较串行的流，**并行的流可以很大程度上提高程序的执行效率。**
Java 8中将并行进行了优化，我们可以很容易的对数据进行并行操作。Stream API可以声明性地通过parallel()与sequential() 在并行流与顺序流之间进行切换。

### 二、Lambda表达式应用举例

**Lambda**是一 个匿名函数，我们可以把Lambda表达式理解为是一 段可以
传递的代码(将代码像数据“ 样进行传递)。使用它可以写出更简洁、更
灵活的代码。作为一种更紧凑的代码风格，使Java的语言表达能力得到了

1.

![image-20200624164745895](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624164745895.png)

**lambda表达式写法**

![image-20200624164935369](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624164935369.png)

2.

![image-20200624165223301](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624165223301.png)

**lambda表达式写法**

![image-20200624165523236](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624165523236.png)

可以看见，Lambda表达式更简洁了

#### 具体说明：

```java
* 1.举例 (o1,o2) -> Integer.compare(o1,o2);
* 2.格式
*      -> :lambda操作符 或 箭头操作符
*      ->左边：lambda形参列表 (其实就是接口中的抽象方法的形参列表)
*      ->右边：lambda体（其实就是重写的抽象方法的方法体）
* 3.lambda表达式的使用： （分为6种情况）
```

##### 1.无参，无返回值

![image-20200624171136657](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624171136657.png)

**lambda表达式的本质：作为接口的实例**

##### 2.lambda需要一个参数，但是没有返回值

![image-20200624171820261](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624171820261.png)

##### 3.数据类型可以省略，因为可由编译器推断出，称为“类型推断”

![image-20200624172250834](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624172250834.png)

##### 4.lambda若只需要一个参数时，参数的小括号可以省略

![image-20200624173712745](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624173712745.png)

##### 5.lambda需要两个或以上的参数，多条执行语句，并且可以有返回值

![image-20200624174420737](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624174420737.png)

##### 6.当lambda体只有一条语句时，return与大括号若有，都可以省略

![image-20200624174836729](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624174836729.png)

###  三、函数式（Functional）接口

**lambda表达式的本质：作为函数式接口的实现**

函数式接口：

@FunctionalInterface加不加都可以，只是一个校验。跟@Override一样

![image-20200624180309264](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624180309264.png)

![image-20200624175948246](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624175948246.png)

**●只包含一个抽象方法的接口，称为函数式接口。**
●你可以通过Lambda表达式来创建该接口的对象(若Lambda表达式抛出一个受检异常(即:非运行时异常)，那么该异常需要在目标接口的抽象方法上进行声明)
●我们可以在一 个接口上使用**@FunctionalInterface**注解，这样做可以检查它是否是一个函数式接口。同时javadoc也会包含--条声明，说明这个接口是一个函数式接口。
**●在java.util.function包下定义了Java 8的丰富的函数式接口**

#### 如何理解函数式接口

●Java从诞生日起就是一-直倡导“一切皆对象”，在Java里面面向对象(OOP)编程是一切。**即java不但可 以支持OOP还可以支持OOF (面向函数编程)**
●在函数式编程语言当中，函数被当做一等公民对待。 在将函数作为一 等公民的编程语言中，Lambda表达式的类型是函数。但是在Java8中，有所不同。在Java8中，Lambda表达式是对象，而不是函数，它们必须依附于一类特别的对象类型——**函数式接口。**
●简单的说，在Java8中，**Lambda表达式就是一个函数式接口的实例**。这就是Lambda表达式和函数式接口的关系。也就是说，只要一个对象是函数式接口的实例，那么该对象就可以用Lambda表达式来表示。
**●所以以前用匿名实现类表示的现在都可以用Lambda表达式来写。**

![image-20200624181226234](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624181226234.png)

```java
* 消费型接口 Consumer<T>    void accept(T t) 消费这个t
* 供给型接口 Supplier<T>    T get() 不给东西，返回一个东西。供给
* 函数型接口 Function<T,R>  R apply(T t)给一个T类型的，返回R类型
* 断定型接口 Predicate<T>   boolean test(T t)根据这个T来判断true或false
```

四个基本的。来写两个例子看一下

![image-20200624191817938](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624191817938.png)

![image-20200624192807711](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624192807711.png)

**其他接口**

![image-20200624192935815](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624192935815.png)

### 四、方法引用与构造器引用

#### 方法引用

●当要传递给ambda体的操作，已经有实现的方法了，可以使用方法引用!
●方法引用可以看做是Lambda表达式深层次的表达。换句话说，方法引用就是Lambda表达式，也就是函数式接口的一个实例，通过方法的名字来指向一个方法，可以认为是Lambda表达式的一个语法糖。
●要求:**实现接口的抽象方法的参数列表和返回值类型，必须与方法引用的方法的参数列表和返回值类型保持一致!**
●格式:使用操作符**“::”**将类(或对象)与方法名分隔开来。
●如下三种主要使用情况:
**➢对象::实例方法名**
**➢类::静态方法名**
**➢类::实例方法名**

我们来用代码看一下：
**准备**

1.

![image-20200624193705458](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624193705458.png)

2.

**Employee，写了一些成员及方法**

![image-20200624193749753](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624193749753.png)

3.

**EmployData,用于生成一些测试数据**

![image-20200624193901102](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624193901102.png)

**测试**

##### 1.对象::实例方法

![image-20200624200306887](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624200306887.png)

##### ![image-20200624200825552](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624200825552.png)

##### 2.类::静态方法

![image-20200624201655879](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624201655879.png)

![image-20200624201840108](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624201840108.png)

##### **3.类::非静态方法**

![image-20200624203215022](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200624203215022.png)

![image-20200625085359717](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625085359717.png)

![image-20200625085415451](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625085415451.png)

##### **总结**

```java
* 方法引用的使用
* 1.使用情景：当要传递给lambda体的操作，已经有实现的方法了，可以使用方法引用
* 2.方法引用，本质上就是lambda表达式，二lambda表达式作为函数式接口的实例，所以
*   方法引用，也是函数式接口的实现
* 3.使用格式： 类（或对象） ::  方法名
* 4.具体分为如下三种情况
*  情况1    对象 :: 非静态方法
*  情况2    类 :: 静态方法
*  情况3    类 :: 非静态方法 (！！)
* 5.方法引用使用的要求：要i去接口中的抽象方法的形参列表喝返回值里欸性能关于方法引用的方法
*   的形参列表喝返回值相同。(针对于情况1，情况2)比如下面的这个void accept(T t)跟
*   void println(T t)
```

#### 构造器引用

##### 1.空参构造器

![image-20200625085858346](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625085858346.png)

##### 2.有参构造器

![image-20200625090228413](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625090228413.png)

![image-20200625090627281](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625090627281.png)

#### 数组引用

![image-20200625091210979](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625091210979.png)

### 五、Stream API

●Java8中有两大最为重要的改变。第一一个是**Lambda**表达式;另外一 -个则是**Stream API。**
●Stream是Java8中处理集合的关键抽象概念，它可以指定你希望对集合进行的操作，可以执行非常复杂的查找、过滤和映射数据等操作。**使用Stream API对集合数据进行操作，就类似于使用SQL执行的数据库查询。**也可以使用Stream API来并行执行操作。简言之StreamAPl 提供了一种高效且易于使用的处理数据的方式。

**为什么要使用Stream API**
●实际开发中，项目中多数数据源都来自于Mysql, Oracle等。 但现在数据源可以更多了，有MongDB，Radis等， 而这些NoSQL的数据就需要Java层面去处理。
●Stream 和Collection集合的区别: **Collection 是一种静态的内存数据结构，而Stream是有关计算的**。前者是主要面向内存，存储在内存中，后者，主要是面向CPU，通过CPU实现算。

**集合讲的是数据，stream讲的是运算**

注意:
①Stream自己不会存储元素。
②Stream不会改变源对象。相反，他们会返回一一个持有结果的新Stream.
③Stream操作是延迟执行的。这意味着他们会等到需要结果的时候才执行。

![image-20200625092325628](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625092325628.png)

#### 创建Stream

我们接着用上面的EmployData.getEmployees()来得到一个list

![image-20200625092952341](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625092952341.png)

##### 一、通过集合

Java8中的Collection 接口被扩展，提供了两个获取流
的方法:
●default Stream\<E\> stream() :返回一个顺序流
●default Stream\<E\> parallelStream():返回一个并行流

我们来看一下这个stream

![image-20200625093252411](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625093252411.png)

代码：

![image-20200625093416073](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625093416073.png)

集合是一种容器，数组也是

##### 二、通过数组

Java8中的Arrays的静态方法stream()可以获取数组流:
**●static \<T> Stream\<T> stream(T[] array):返回一个流**
**重载形式，能够处理对应基本类型的数组:**
●public static IntStream stream(int[] array)
●public static LongStream stream(long[] array)
●public static DoubleStream stream(double[] array)

![image-20200625094730656](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625094730656.png)

##### 三、通过Stream的of()

可以调用Stream类静态方法of(), 通过显示值创建一个流。它可以接收任意数量的参数。
●public static\<T> Stream\<T> of(T.. values):返回一个流

![image-20200625095933299](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625095933299.png)

##### 四、创建无限流（用的比较少）

可以使用静态方法Stream.iterate()和Stream.generate(),
创建无限流。
迭代
public static\<T> Stream\<T> iterate(final T seed, final UnaryOperator\<T> f)
生成
public static\<T> Stream\<T> generate(Supplier\<T> s)

![image-20200625101251827](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101251827.png)

![image-20200625101307875](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101307875.png)

可以看见 **UnaryOperator继承了Fucntion，**Function中的apply是

![image-20200625101410685](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101410685.png)

接受一个T，返回一个R，而UnaryOperator比较特殊，接受一个T，返回一个T

![image-20200625101449804](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101449804.png)

![image-20200625101917035](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101917035.png)

**终止操作就是System.out.println,用于消费**

#### Stream的中间操作

![image-20200625101950648](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625101950648.png)

##### 一、筛选与切片

首先看一下之前Employee中重写的hashCode跟equals。用于distinct演示

![image-20200625103740053](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625103740053.png)

![image-20200625104342217](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625104342217.png)

![image-20200625104403904](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625104403904.png)

##### 二、映射

![image-20200625104529920](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625104529920.png)

![image-20200625110202738](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625110202738.png)

接下来看flatMap,这个有点麻烦，我们先看一下这个

有add跟addAll,我们通过这两个来类比flatMap

**add**

![image-20200625110505163](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625110505163.png)

**addAll**

![image-20200625110530062](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625110530062.png)

**即flatMap相当于addAll**

**map**

![image-20200625112005114](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625112005114.png)

**flatMap**

![image-20200625112252531](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625112252531.png)

##### 三、排序

![image-20200625112433062](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625112433062.png)

**sorted()**

![image-20200625112731800](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625112731800.png)

**sorted(Comparator conn)**

![image-20200625113330328](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625113330328.png)

#### Stream终止操作

##### 一、查找与匹配

![image-20200625113434789](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625113434789.png)

![image-20200625143634348](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625143634348.png)

##### 二、归约

![image-20200625143734182](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625143734182.png)

**reduce(T iden,BinaryOperator b)**

reduce

![image-20200625144135570](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625144135570.png)

BinaryOperator

![image-20200625144236916](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625144236916.png)

Integer.sum

![image-20200625144323921](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625144323921.png)

**identity是初始值**

![image-20200625150522087](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150522087.png)

**这里(d1,d2)->d1+d2**，为什么呢？
![image-20200625150602922](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150602922.png)

![image-20200625150615548](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150615548.png)

![image-20200625150633836](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150633836.png)

##### 三、收集

![image-20200625150713239](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150713239.png)

![image-20200625150916768](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625150916768.png)

![image-20200625151914701](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625151914701.png)

下面仅对list跟set做一个演示

![image-20200625151936763](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625151936763.png)

### 六、Optional类

●到目前为止，臭名昭著的空指针异常是导致Java应用程序失败的最常见原因。以前，为了解决空指针异常，Google公 司著名的Guava项目引入了Optional类，Guava通过使用检查空值的方式来防止代码污染，它鼓励程序员写更干净的代码。受到Google Guava的启发Optional类 已经成为Java 8类库的一部分。)

**●Optional\<T>类(java.util.Optional) 是-一个容器类，它可以保存类型T的值，代表这个值存在。或者仅仅保存null，表示这个值不存在。原来用null表示一个值不存在，现Optional可以更好的表达这个概念。并且可以避免空指针异常。**
●Optional类的Javadoc描述如下:这是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法 会返回该对象。

![image-20200625152253989](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625152253989.png)

**准备：**

![image-20200625152604205](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625152604205.png)

![image-20200625152611828](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625152611828.png)

下面举几个例子

1.**Optional.of**

![image-20200625153016669](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153016669.png)

![image-20200625153030589](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153030589.png)

2.**Optional.ofNullable**

![image-20200625153208180](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153208180.png)

![image-20200625153220631](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153220631.png)

3.我们来看一下使用举例

![image-20200625153443806](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153443806.png)

在平常的开发中，我们经常遇到上面这样的空指针异常

那么我们优化后呢？

![image-20200625153703777](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625153703777.png)

那么我们用 **Optional**来怎么实现

![image-20200625154650333](http://smart-house-img.oss-cn-beijing.aliyuncs.com/picgo/image-20200625154650333.png)