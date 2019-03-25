package us.zoom.async.lambda;

import us.zoom.async.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Eden Ye
 * @Date 2019/3/23 13:45
 * @Description
 */
public class LambdaTest {


    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("no_1","name_1",20));
        students.add(new Student("no_2","name_2",20));
        students.add(new Student("no_3","name_3",21));
        students.add(new Student("no_4","name_4",22));
        students.add(new Student("no_5","name_5",23));

        List<String> names = students.stream()
                .filter(student -> student.getAge()==20)
                .map(Student::getName)
                .collect(Collectors.toList());

        System.out.println(String.join(",",names));
    }
}
