package com.springBootwithMongo.demo.service;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<ResponseEmployee> getAllEmployee();


    ResponseEmployee newEmployee(CreateEmployeeRequest employee);

    ResponseEmployee deleteEmployee(String id);

    ResponseEmployee updateEmployee(String id, UpdateDTO update);

    List<List> getEmployeeWithConstraints(String name,String designation);

    List<Employee> getEmployeeWithID(String id);
}
