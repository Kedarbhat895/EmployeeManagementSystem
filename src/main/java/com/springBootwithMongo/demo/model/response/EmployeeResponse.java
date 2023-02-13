package com.springBootwithMongo.demo.model.response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String employeeId;

    private String employeeName;
    private String employeeDesignation;
    private Double employeeSalary;

    private String employeeDob;
    private Integer employeeAge;


}
