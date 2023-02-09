package com.springBootwithMongo.demo.model;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.aot.generate.GenerationContext;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class Employee {
    @Id
    private String employeeID;
    private String employeeName;
    private String employeeDesignation;
    private String employeeDob;
    private int employeeAge;
    private int employeeSalary;





}
