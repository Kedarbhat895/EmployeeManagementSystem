package com.springBootwithMongo.demo.service;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;

import java.util.List;

public interface EmployeeService {

    List<ResponseEmployee> getAllEmployee();


    ResponseEmployee newEmployee(CreateEmployeeRequest employee);

    ResponseEmployee deleteEmployee(String id);

    ResponseEmployee updateEmployee(String id, UpdateDTO update);

    List<ResponseEmployee> getEmployeeWithConstraints(String name,String designation);

    List<Employee> getEmployeeWithID(String id);

    List<ResponseForAggregate> getSalary(String designation);
}
