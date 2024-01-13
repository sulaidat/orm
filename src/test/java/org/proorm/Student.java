package org.proorm;
import org.proorm.attributeconverter.JsonAttributeConverter;

import javax.persistence.Convert;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private boolean gender;
    private int age;
    private String address;
    @Convert(converter = Student.ListStringJsonConverter.class)
    private List<String> courses;
    private Date birthday;

    // Constructor
    public Student(Integer id, String name, boolean gender, int age, String address, List<String> courses, Date birthday) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.address = address;
        this.courses = courses;
        this.birthday = birthday;
    }

    // Getter methods
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public List<String> getCourses() {
        return courses;
    }

    public Date getBirthday() {
        return birthday;
    }

    // Setter methods
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public static class ListStringJsonConverter extends JsonAttributeConverter<List<String>> {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", courses=" + courses +
                ", birthday=" + birthday +
                '}';
    }
}
