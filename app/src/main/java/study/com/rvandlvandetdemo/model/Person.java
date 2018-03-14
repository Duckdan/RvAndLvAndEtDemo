package study.com.rvandlvandetdemo.model;

/**
 * Created by Administrator on 2018/3/14.
 */

public class Person {
    private String name;
    private String description;

    public Person() {
    }

    public Person(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
