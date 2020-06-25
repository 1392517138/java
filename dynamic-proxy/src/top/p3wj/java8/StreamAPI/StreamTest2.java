package top.p3wj.java8.StreamAPI;

import org.junit.Test;
import top.p3wj.java8.lambda2.Employee;
import top.p3wj.java8.lambda2.EmployeeData;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: Aaron
 * @Description: 测试Stream的中制操作
 * @Date: Created in 11:35 2020/6/25 0025
 */
public class StreamTest2 {
    //1-匹配与查找
    @Test
    public void test1(){
        List<Employee> employees = EmployeeData.getEmployees();
        //allMathch(Predicate p)————检查是否匹配所有元素。练习：是否所有的员工的年龄都大于18
        boolean b = employees.stream().allMatch(s -> s.getAge() > 18);
        //anyMatch(Predicate p)————检查是否至少匹配一个元素。练习：是否村子啊员工的工资大于10000
        boolean b1 = employees.stream().anyMatch(s -> s.getSalary() > 10000);
        //noneMatch(Predicate p)————检查是否没有匹配的元素。练习：是否存在员工姓“雷”
        employees.stream().noneMatch(employee -> employee.getName().startsWith("雷"));
        //findFirst————返回第一个元素
        Optional<Employee> first = employees.stream().findFirst();
        //findAny————返回当前流中的任意元素,注意这里是parallelStream,stream是有顺序的
        Optional<Employee> any = employees.parallelStream().findAny();
        //count————返回流中的元素个数
        long count = employees.stream().filter(e -> e.getSalary() > 5000).count();
        //max(Comparator c)————返回流中的最大值 练习：返回最高工资
        Optional<Double> max = employees.stream().map(e -> e.getSalary()).max(Double::compare);
        //min(Comparator c)————返回流种的最小值 练习：返回最低工资的员工
        Optional<Employee> min = employees.stream().min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        //forEach(Consumer c)————内部迭代.一个是Stream里面的，一个是集合里面的
        employees.stream().forEach(System.out::println);
        employees.forEach(System.out::println);
    }
    //2-规约
    @Test
    public void test3(){
        //reduce(T iden,BinaryOperator b)可以将流中元素反复结合起来，得到一个值，返回T。(((1+2)+3)+4)...这样反复.所以叫归约
        //练习1：计算1-10的自然数的和
        List<Integer> list = Arrays.asList(1,2,3,4,5,6);
        Integer sum = list.stream().reduce(10, Integer::sum);
        System.out.println(sum);
        //练习2：计算公司所有员工工资的总和
//        Optional<Integer> salary = list.stream().reduce(Integer::sum);
        Optional<Integer> salary = list.stream().reduce((d1,d2)->d1+d2);
        System.out.println(salary);
    }
    //3-收集
    @Test
    public void test04(){
        List<Employee> employees = EmployeeData.getEmployees();
        //练习1：查找工资大于6000的员工，结果返回一个List或set(无序)
        List<Employee> collect = employees.stream().filter(e -> e.getSalary() > 6000).collect(Collectors.toList());
        employees.stream().collect(Collectors.toSet());
    }

}
