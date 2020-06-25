package top.p3wj.java8.lambda;

import org.junit.Test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @Author: Aaron
 * @Description:
 * 消费型接口 Consumer<T>    void accept(T t) 消费这个t
 * 供给型接口 Supplier<T>    T get() 不给东西，返回一个东西。供给
 * 函数型接口 Function<T,R>  R apply(T t)给一个T类型的，返回R类型
 * 断定型接口 Predicate<T>   boolean test(T t)根据这个T来判断true或false
 * @Date: Created in 18:14 2020/6/24 0024
 */
public class LambdaTest2 {
    @Test
    public void test1(){
        //原来的写法
        happyTime(500, new Consumer<Double>() {
            @Override
            public void accept(Double aDouble) {
                System.out.println("学习太累了，喝口水,价格为"+aDouble);
            }
        });
        //两者效果一样
        happyTime(600, money-> System.out.println(money));
    }
    public void happyTime(double money, Consumer<Double> conn){
        conn.accept(money);
    }
    @Test
    public void test2(){
        List<String> list = Arrays.asList("一","二","三");
        List<String> filterStrs = filter(list,s->s.contains("一"));
        System.out.println(filterStrs);
    }
    //根据给定的规则，过滤几何中的字符串。此规则由Predicate的方法决定
    public List<String> filter(List<String> list, Predicate<String> pre){
        ArrayList<String> filterList = new ArrayList<>();
        for (String s : list){
            if (pre.test(s)){
                filterList.add(s);
            }
        }
        return filterList;
    }

}
