package csc1035.project3;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "STUDENT")
public class Student {

    @Id
    @Column
    private String studentId;

    @Column
    private String name;

    @Column
    private Integer age;

    @ManyToMany
    @JoinTable(
            name = "STUDENT_MODULE",
            joinColumns = {@JoinColumn(name = "student_id")},
            inverseJoinColumns = {@JoinColumn(name = "module_code")})
    private Set<Module> modules = new HashSet<>();



    public Student(String studentId) {
        this.studentId = studentId;
    }

    public Student(String studentId, String name, Integer age) {
        this.studentId = studentId;
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }


    public void setModules(Set<Module> modules) {
        this.modules = modules;
    }

    public Set<Module> getModules() {
        return modules;
    }
}
