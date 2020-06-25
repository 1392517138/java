package top.p3wj.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @Author: Aaron
 * @Description:
 * 1.举例 (o1,o2) -> Integer.compare(o1,o2);
 * 2.格式
 *      -> :lambda操作符 或 箭头操作符
 *      ->左边：lambda形参列表 (其实就是接口中的抽象方法的形参列表)
 *      ->右边：lambda体（其实就是重写的抽象方法的方法体）
 * 3.lambda表达式的使用： （分为6种情况）
 * 4.lambda表达式的本质：作为接口的实例
 * @Date: Created in 16:59 2020/6/24 0024
 */
public class LambdaTest1 {
    //1.语法格式一：无参，无返回值
    @Test
    public void test1(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱你");
            }
        };
        r1.run();
        System.out.println("--------------");
        Runnable r2 = ()-> System.out.println("我爱lambda表达式");
        r2.run();
    }
    //语法格式二：lambda需要一个参数，但是没有返回值
    @Test
    public void test02() {
        Consumer<String> conn = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        conn.accept("我是luca");
        System.out.println("------------");
        Consumer<String>conn2 = (String s )-> System.out.println(s);
        conn2.accept("我是什么");
    }
    //语法格式三：数据类型可以省略，因为可由编译器推断出，称为“类型推断”
    @Test
    public void test03() {
        Consumer<String>conn2 = (String s )-> System.out.println(s);
        conn2.accept("我是什么");
        System.out.println("------------");
        Consumer<String>conn3 = (s) -> System.out.println(s);
        conn3.accept("我是2");
    }
    @Test
    //语法格式四：lambda若只需要一个参数时，参数的小括号可以省略
    public void test04() {
        Consumer<String>conn3 = (s) -> System.out.println(s);
        conn3.accept("我是2");
        System.out.println("------------");
        Consumer<String>conn4 = s -> System.out.println(s);
        conn4.accept("我是3");
    }
    //语法格式五：lambda需要两个或以上的参数，多条执行语句，并且可以有返回值
    @Test
    public void test05(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                System.out.println(o1);
                System.out.println(o2);
                return o1.compareTo(o2);
            }
        };
        int compare = com1.compare(3, 4);
        System.out.println(compare);
        System.out.println("------------");
        Comparator<Integer> com2 = (o1,o2) -> {
            System.out.println(o1);
            System.out.println(o2);
            return o1.compareTo(o2);
        };
        int compare2 = com2.compare(1, 3);
        System.out.println(compare2);
    }
    //语法格式6：当lambda体只有一条语句时，return与大括号若有，都可以省略
    @Test
    public void test056(){
        Comparator<Integer> com2 = (o1,o2) -> {
            return o1.compareTo(o2);
        };
        int compare2 = com2.compare(1, 3);
        System.out.println(compare2);
        System.out.println("------------");
        Comparator<Integer> com3 = (o1,o2) -> o1.compareTo(o2);
        int compare = com3.compare(1, 4);
        System.out.println(compare);
    }
}
