package com.example.employesapicrud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Employee")
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long empId;

    @Column(name = "emp_name" )
    private String emp_name;

    @Column(name = "emp_salary")
    private Float emp_salary;

    @Column(name = "emp_age")
    private int emp_age;

    @Column(name = "emp_city")
    private String emp_city;

}
