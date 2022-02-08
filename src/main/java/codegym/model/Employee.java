package codegym.model;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "{error.blank}")
    private String employeeCode;

    @NotBlank(message = "{error.blank}")
    private String name;

    @NotNull(message = "{error.blank}")
    @Min(value = 18,message = "{error.age}")
    @Max(value = 60,message = "{error.age}")
    private int age;

    @NotNull(message = "{error.blank}")
    private double salary;

    @ManyToOne
    private Branch branch;

    public Employee() {
    }

    public Employee(long id, String employeeCode, String name, int age, double salary, Branch branch) {
        this.id = id;
        this.employeeCode = employeeCode;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.branch = branch;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmployeeCode() {
        return employeeCode;
    }

    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }
}

