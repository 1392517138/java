package top.p3wj.java8.StreamAPI;

import org.junit.Test;
import top.p3wj.java8.lambda2.Employee;
import top.p3wj.java8.lambda2.EmployeeData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Author: Aaron
 * @Description: 测试stream的中间操作
 * @Date: Created in 10:20 2020/6/25 0025
 */
public class StreamAPITest1 {
    //1-筛选与切片
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //filter(Predicate p)————接受lambda,从流中排除某些元素
        employees.stream().filter(e->e.getSalary()>7000).forEach(System.out::println);
        //limit(n)————截断流，使其元素不超过给定数量
        System.out.println("--------");
        employees.stream().limit(1).forEach(System.out::println);
        //skip(n)————跳过元素，返回一个扔掉了前n个元素的流，若流中元素不足n个，则返回一个空流。与limit互补
        System.out.println("--------");
        employees.stream().skip(6).forEach(System.out::println);
        //distinct()————筛选，通过流所生成元素的hashCode()和equals()去除重复元素
        System.out.println("--------");
        employees.add(new Employee(1,"哈哈",40,9000));
        employees.add(new Employee(1,"哈哈",40,9000));
        employees.add(new Employee(1,"哈哈",40,9000));
        employees.stream().distinct().forEach(System.out::println);
    }
    //2.映射
    @Test
    public void test2(){
        //map(Fuction f)接受一个函数作为参数，将元素抓换成其他形式或提取信息，该函数会被应用到每个元素上，并将其映射成一个新的元素
        List<String> list = Arrays.asList("aa", "bb", "cc", "dd");
        list.stream().map(str->str.toUpperCase()).forEach(System.out::println);
        System.out.println("----------");
        //练习1：获取员工姓名长度大于3的姓名
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().map(Employee::getName).filter(name->name.length()>3).forEach(System.out::println);
        //练习2：!!这是一个Stream构成的Stream
//        Stream<Stream<Character>> streamStream = list.stream().map(StreamAPITest1::fromStringToStream);
//        streamStream.forEach(s->{
//            s.forEach(System.out::println);
//        });
        //flatMap(Function f)接受一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流练成一个流。！！注意这个跟第一个的区别
        Stream<Character> rStream = list.stream().flatMap(StreamAPITest1::fromStringToStream);
        rStream.forEach(System.out::println);
    }
    //将字符串中的多个字符构成的集合转换为对应的Stream的实例
    public static Stream<Character> fromStringToStream(String str){
        ArrayList<Character> list = new ArrayList<>();
        for (Character c : str.toCharArray()){
            list.add(c);
        }
        return list.stream();
    }
    @Test
    public void test3(){
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        ArrayList list2 = new ArrayList();
        list2.add(4);
        list2.add(5);
        list2.add(6);
//        list1.add(list2);
        list1.addAll(list2);
        System.out.println(list1);
    }
    //3-排序
    @Test
    public void test4(){
        //sorted————自然排序
        List<Integer> integers = Arrays.asList(1, 2, 58, 1, 65, 453453);
        integers.stream().sorted().forEach(System.out::println);
        //sorted(Comparator com)————定制排序。若年龄一样则按照工资排序
        List<Employee> employees = EmployeeData.getEmployees();
        employees.stream().sorted((o1,o2)->{
            int compare = Integer.compare(o1.getAge(), o2.getAge());
            if (compare != 0){
                return compare;
            }else {
                return Double.compare(o1.getSalary(),o2.getSalary());
            }
        }).forEach(System.out::println);
    }
}
