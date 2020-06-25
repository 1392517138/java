package top.p3wj.java8.optional;

/**
 * @Author: Aaron
 * @Description:
 * @Date: Created in 15:23 2020/6/25 0025
 */
public class Girl {
    private String name;

    public Girl(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Girl{" +
                "name='" + name + '\'' +
                '}';
    }

    public Girl() {
    }
}
