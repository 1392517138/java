package top.p3wj.java8.StreamAPI;

import org.junit.Test;
import top.p3wj.java8.lambda2.Employee;
import top.p3wj.java8.lambda2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: Aaron
 * @Description:
 * @Date: Created in 9:25 2020/6/25 0025
 */
public class StreamAPITest {
    //创建方式一：通过集合
    @Test
    public void test01(){
        List<Employee> employees = EmployeeData.getEmployees();
//        default Stream<E> stream() :返回一个顺序流
        Stream<Employee> stream = employees.stream();
//        default Stream<E> parallelStream():返回一个并行流
        Stream<Employee> parallelStream = employees.parallelStream();
    }
    //创建方式二：通过数组
    @Test
    public void test02() {
        int[] arr = new int[]{1,2,3,4};
        //调用Arrays类的static<T> Stream<T> stream(T[] array):返回一个流
        IntStream stream = Arrays.stream(arr);
    }
    //创建方式三：通过Stream的of()
    @Test
    public void test3(){
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5, 6);
//       迭代 public static<T> Stream<T> iterate(final T seed, final UnaryOperator\<T> f)
        //遍历前10个偶数。不停地去迭代，+2。想要听着就需要用到我们的终止操作 limit
        Stream.iterate(0,t->t+2).limit(10).forEach(System.out::println);
        System.out.println("-----------");
//        生成  public static<T> Stream<T> generate(Supplier<T> s)
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }
}