package com.springBootwithMongo.demo.model.request;

import com.springBootwithMongo.demo.model.Employee;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateEmployeeRequest {


    private String employeeId;

    @NotNull(message = "Cannot be blank")
    private String employeeName;
//    @NotBlank(message = "Cannot be blank")
    private String employeeDesignation;


    @Pattern(regexp = "(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/\\d{4}$",message = "enter in dd/mm/yyyy pattern")
    private String employeeDob;
//    @NotBlank(message="Please enter Salary")

    private int employeeSalary;

//    private int age;
    private Employee employee;


    public int getAge(String dob){


        String date[] = dob.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate d = LocalDate.of(year, month, day);
        LocalDate Cur = LocalDate.now();
        int age = Period.between(d, Cur).getYears();

        return age;
    }}
