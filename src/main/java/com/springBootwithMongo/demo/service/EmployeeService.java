package com.springBootwithMongo.demo.service;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    List<Employee> getAllEmployee();


    void newEmployee(CreateEmployeeRequest employee);

    void deleteEmployee(String id);

    void updateEmployee(String id, UpdateDTO update);

    List<List> getEmployeeWithConstraints(String name,String designation);

    List<Employee> getEmployeeWithID(String id);
}
