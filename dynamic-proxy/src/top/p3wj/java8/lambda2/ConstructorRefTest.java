package top.p3wj.java8.lambda2;

import org.junit.Test;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 一、构造器引用
 *      和方法引用类似，函数式接口的抽象方法的形参列表和构造器的形参列表一致。
 *      抽象方法的返回值类型即为构造器所属的类的类型
 * 二、数组引用
 *
 */
public class ConstructorRefTest {
	//构造器引用
    //Supplier中的T get()
    //我们的空参构造器也是没有参数返回一个类型
    @Test
    public void test1(){
        Supplier<Employee> sup1 = ()->new Employee();
        System.out.println("----------");
        Supplier<Employee> sup2 = Employee::new;
	}

	//Function中的R apply(T t)
    @Test
    public void test2(){
        Function<Integer,Employee> func1 = id -> new Employee(id);
        Employee apply = func1.apply(1001);
        System.out.println(apply);
        System.out.println("----------");
        Function<Integer,Employee> func2 = Employee::new;
        Employee apply1 = func2.apply(2002);
        System.out.println(apply1);
    }

	//BiFunction中的R apply(T t,U u)
    @Test
    public void test3(){
        BiFunction<Integer,String,Employee> func1 = (id,name) ->new Employee(id,name);
        Employee emp1 = func1.apply(1, "小红");
        System.out.println(emp1);
        System.out.println("---------");
        BiFunction<Integer,String,Employee> func2 = Employee::new;
        Employee emp2 = func2.apply(2, "小黑");
        System.out.println(emp2);
    }

	//数组引用
    //Function中的R apply(T t)
    @Test
    public void test4(){
        Function<Integer,String[]> func1 = length -> new String[length];
        String[] apply = func1.apply(5);
        System.out.println(Arrays.toString(apply));
        System.out.println("-----------");
        Function<Integer,String[]> func2 = String[]::new;
        String[] apply1 = func2.apply(10);
        System.out.println(Arrays.toString(apply1));

    }
}
