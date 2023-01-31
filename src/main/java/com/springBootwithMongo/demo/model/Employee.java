package com.springBootwithMongo.demo.model;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

public class Employee {
    @Id
    private String employeeID;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDob;
    private int age;
    private int employeeSalary;





}
