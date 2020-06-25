package top.p3wj.java8.lambda;

import org.junit.Test;

import java.util.Comparator;

/**
 * @Author: piwenjing
 * @Description:
 * @Date: Created in 16:43 2020/6/24 0024
 */
public class LambdaTest {
    @Test
    public void test01(){
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("我爱你");
            }
        };
        r1.run();
    }
    @Test
    public void test02(){
        Runnable r2 = ()-> System.out.println("我爱lambda表达式");
        r2.run();
    }
    @Test
    public void test03(){
        Comparator<Integer> com1 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        int compare = com1.compare(12, 13);
        System.out.println(compare);
    }
    @Test
    public void test04(){
        Comparator<Integer> com2 = (o1, o2) -> o1-o2;
        int compare = com2.compare(123, 222);
        System.out.println(compare);
    }
}
