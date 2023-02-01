package com.springBootwithMongo.demo.model.response;

import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEmployee {
    private String employeeId;

    private String employeeName;
    private String employeeDesignation;
    private Integer employeeSalary;

    private String employeeDob;
    private int age;


}
