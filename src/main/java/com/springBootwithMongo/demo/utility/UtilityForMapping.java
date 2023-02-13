package com.springBootwithMongo.demo.utility;

import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.UpdateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;

import java.time.LocalDate;
import java.time.Period;

public class UtilityForMapping {

    public static int getAge(String dob) {
        String date[] = dob.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate d = LocalDate.of(year, month, day);
        LocalDate Cur = LocalDate.now();
        int age = Period.between(d, Cur).getYears();

        return age;
    }

    public static EmployeeResponse employeeResponse(Employee employee) {
        return EmployeeResponse.builder().employeeName(employee.getEmployeeName()).employeeId(employee.getEmployeeID())
                .employeeDob(employee.getEmployeeDob()).employeeSalary(employee.getEmployeeSalary()).employeeDesignation(employee.getEmployeeDesignation()).employeeAge(employee.getEmployeeAge()).build();

    }

    public static Employee employeeData(CreateEmployeeRequest createEmployeeRequest) {
        return Employee.builder().employeeID(createEmployeeRequest.getEmployeeId()).employeeName(createEmployeeRequest.getEmployeeName())
                        .employeeAge(getAge(createEmployeeRequest.getEmployeeDob())).employeeDesignation(createEmployeeRequest.getEmployeeDesignation())
                        .employeeSalary(createEmployeeRequest.getEmployeeSalary()).employeeDob(createEmployeeRequest.getEmployeeDob()).build();

    }

    public static void updateEmployee(UpdateEmployeeRequest updateEmployeeRequest,Employee employee) {
        employee.setEmployeeAge(updateEmployeeRequest.getEmployeeAge());
        employee.setEmployeeSalary(updateEmployeeRequest.getEmployeeSalary());
        employee.setEmployeeDesignation(updateEmployeeRequest.getEmployeeDesignation());
        employee.setEmployeeName(updateEmployeeRequest.getEmployeeName());
    }



}
