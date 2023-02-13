package com.springBootwithMongo.demo.model.request;

import jakarta.validation.constraints.*;
import lombok.*;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String employeeId;//todo add notblank
    @NotBlank(message = "Cannot be blank for name")
    private String employeeName;
    @NotBlank(message = "Cannot be blank")
    private String employeeDesignation;
    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$", message = "enter in dd/mm/yyyy pattern")
    private String employeeDob;
    @DecimalMax("100000.00")
    @DecimalMin("100")
    @Positive
    private Double employeeSalary;


}
