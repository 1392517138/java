package top.p3wj.java8.optional;

/**
 * @Author: Aaron
 * @Description:
 * @Date: Created in 15:23 2020/6/25 0025
 */
public class Boy {
    private Girl girl;

    @Override
    public String toString() {
        return "Boy{" +
                "girl=" + girl +
                '}';
    }

    public Girl getGirl() {
        return girl;
    }

    public void setGirl(Girl girl) {
        this.girl = girl;
    }

    public Boy() {
    }

    public Boy(Girl girl) {
        this.girl = girl;
    }
}
