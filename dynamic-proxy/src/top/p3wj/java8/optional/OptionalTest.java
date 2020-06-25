package top.p3wj.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * @Author: Aaron
 * @Description:
 * @Date: Created in 15:26 2020/6/25 0025
 */
public class OptionalTest {
    @Test
    public void test1(){
        Girl girl = new Girl();
//        girl = null;
        Optional<Girl> girl1 = Optional.ofNullable(girl);
        System.out.println(girl1);
    }
    public String getGirlName(Boy boy){
        return boy.getGirl().getName();
    }
    @Test
    public void test2(){
        Boy boy = new Boy();
        String girlName = getGirlName1(boy);
        System.out.println(girlName);

    }
    //使用Optional类的getGirlName方法
    public String getGirlName2(Boy boy){
        //boy可能本身就是一个Null
        Optional<Boy> boyOptional = Optional.ofNullable(boy);
        //如果是boy空的
        Boy boy1 = boyOptional.orElse(new Boy(new Girl("哈哈")));
        Girl girl = boy1.getGirl();
        //有可能boy不是空的，girl是空的
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
        Girl girl1 = girlOptional.orElse(new Girl("呵呵呵"));
        return girl.getName();
    }
    //优化以后的getGirlName方法
    public String getGirlName1(Boy boy){
        if (boy != null){
            Girl girl = boy.getGirl();
            if (girl != null){
                return girl.getName();
            }
        }
        return null;
    }
}
