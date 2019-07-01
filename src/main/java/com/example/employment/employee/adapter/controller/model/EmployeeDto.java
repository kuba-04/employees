package com.example.employment.employee.adapter.controller.model;

import com.example.employment.employee.domain.Employee;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class EmployeeDto {

    private String name;
    private String surname;
    private Integer grade;
    private Integer salary;

    @JsonCreator
    public EmployeeDto(
            @JsonProperty(value = "name") String name,
            @JsonProperty(value = "surname") String surname,
            @JsonProperty(value = "grade") Integer grade,
            @JsonProperty(value = "salary") Integer salary) {
        this.name = name;
        this.surname = surname;
        this.grade = grade;
        this.salary = salary;
    }

    public Employee toEmployee() {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setSurname(surname);
        employee.setGrade(grade);
        employee.setSalary(salary);
        return employee;
    }
}
