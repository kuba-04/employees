package com.example.employment.employee.domain;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false)
    private Integer grade;

    @Column(nullable = false)
    private Integer salary;

}
