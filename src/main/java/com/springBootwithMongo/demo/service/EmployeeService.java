package com.springBootwithMongo.demo.service;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;

import java.util.List;

public interface EmployeeService {

    List<ResponseEmployee> getAllEmployee();
    ResponseEmployee newEmployee(CreateEmployeeRequest employee);

    DeleteResult deleteEmployee(String id);

    ResponseEmployee updateEmployee(String id, UpdateDTO update);

    List<ResponseEmployee> getEmployeeWithConstraints(String name,String designation);

    ResponseEmployee getEmployeeWithID(String id);

    List<ResponseForAggregate> getSalary(String designation);
}
