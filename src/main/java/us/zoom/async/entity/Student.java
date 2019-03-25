package us.zoom.async.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author Eden Ye
 * @Date 2019/3/23 13:45
 * @Description
 */
@Getter
@Setter
public class Student {
    private String id;
    private String name;
    private int age;

    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
