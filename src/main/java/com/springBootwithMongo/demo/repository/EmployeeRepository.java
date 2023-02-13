package com.springBootwithMongo.demo.repository;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.response.SalaryResponse;

import java.util.List;


public interface EmployeeRepository {

    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployee();


    Employee updateEmployee(Employee employee);

    DeleteResult deleteEmployee(Employee employee);

    Employee getEmployeeByID(String id);

    List<Employee> getEmployeeByName(String name);
    List<Employee> getEmployeeByDesignation(String designantion);


    List<Employee> getEmployeeWithNameAndDesignation(String name,String designation);

    List<SalaryResponse> getTotalSalary(String designation);


}