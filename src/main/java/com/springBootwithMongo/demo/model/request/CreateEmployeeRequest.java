package com.springBootwithMongo.demo.model.request;

import com.springBootwithMongo.demo.model.Employee;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;

import java.time.LocalDate;
import java.time.Period;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {


    private String employeeId;

    @NotBlank(message = "Cannot be blank")
    private String employeeName;
    @NotBlank(message = "Cannot be blank")
    private String employeeDesignation;


    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$", message = "enter in dd/mm/yyyy pattern")
    private String employeeDob;
//    @NotBlank(message="Please enter Salary")


    @Max(100000)
    @Min(100)
    @Positive

    private Integer employeeSalary;

    //    private int age;
    private Employee employee;
}
