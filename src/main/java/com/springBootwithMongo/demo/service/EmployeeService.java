package com.springBootwithMongo.demo.service;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.request.UpdateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;
import com.springBootwithMongo.demo.model.response.SalaryResponse;

import java.util.List;

public interface EmployeeService {

    List<EmployeeResponse> getAllEmployee();
    EmployeeResponse newEmployee(CreateEmployeeRequest employee);

    DeleteResult deleteEmployee(String id);

    EmployeeResponse updateEmployee(String id, UpdateEmployeeRequest update);

    List<EmployeeResponse> getEmployeeWithConstraints(String name, String designation);

    EmployeeResponse getEmployeeWithID(String id);

    List<SalaryResponse> getSalary(String designation);
}
