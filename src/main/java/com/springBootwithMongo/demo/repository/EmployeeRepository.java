package com.springBootwithMongo.demo.repository;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;

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

    List<ResponseForAggregate> getTotalSalary(String designation);


}