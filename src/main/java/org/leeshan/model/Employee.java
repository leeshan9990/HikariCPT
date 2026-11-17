package org.leeshan.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

    private int id;
    private String name;
    private String email;
    private String department;
    private BigDecimal salary;
    private LocalDate joiningDate;

    // Constructor for creating a new employee
    public Employee(String name, String email,
                    String department, BigDecimal salary,
                    LocalDate joiningDate) {

        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    // Constructor for employees retrieved from database
    public Employee(int id, String name, String email,
                    String department, BigDecimal salary,
                    LocalDate joiningDate) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.department = department;
        this.salary = salary;
        this.joiningDate = joiningDate;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public LocalDate getJoiningDate() {
        return joiningDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", department='" + department + '\'' +
                ", salary=" + salary +
                ", joiningDate=" + joiningDate +
                '}';
    }
}