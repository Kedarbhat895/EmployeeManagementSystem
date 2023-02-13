package com.springBootwithMongo.demo.model.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeRequest {

    //todo add all other fields


    @Positive
    @DecimalMin("100.00")
    @DecimalMax("10000.00")
    private Double employeeSalary;

    @NotBlank(message = "enter a valid designation")
    private String employeeDesignation;


    @NotBlank(message = "enter a valid name")
    private String employeeName;


    @NotBlank(message = "enter a valid dob")
    private String employeeDob;


    private Integer employeeAge;

}

