package com.springBootwithMongo.demo.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import com.springBootwithMongo.demo.service.EmployeeService;
import com.springBootwithMongo.demo.utility.UtilityForMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j

public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<ResponseEmployee> getAllEmployee() {
        List<ResponseEmployee> responseEmployee = new ArrayList<>();
        List<Employee> dataEmployee = employeeRepository.getAllEmployee();
        for (Employee e : dataEmployee) {
            ResponseEmployee re = UtilityForMapping.employeeResponse(e);
            responseEmployee.add(re);
        }
        log.info("getAllEmployee in Service is accessed {}",responseEmployee);
        return responseEmployee;
    }
    @Override
    public ResponseEmployee newEmployee(CreateEmployeeRequest createEmployeeRequest) {
        Employee employee = UtilityForMapping.employeeData(createEmployeeRequest); //Todo
        Employee savedEmployee = employeeRepository.saveEmployee(employee);
        ResponseEmployee responseEmployee = UtilityForMapping.employeeResponse(savedEmployee);
        return responseEmployee;
    }

    @Override
    public DeleteResult deleteEmployee(String id) {
        Employee employee = employeeRepository.getEmployeeByID(id);
        DeleteResult e = employeeRepository.deleteEmployee(employee);
        return e;
    }

    @Override
    public ResponseEmployee updateEmployee(String id, UpdateDTO update) {
        Employee employee = employeeRepository.getEmployeeByID(id);
        employee.setEmployeeDesignation(update.getDesignation());
        employee.setEmployeeSalary(update.getSalary());
        Employee savedEmployee = employeeRepository.updateEmployee(employee);
        ResponseEmployee responseEmployee = UtilityForMapping.employeeResponse(savedEmployee);
        return responseEmployee;
    }


    public List<ResponseEmployee> getEmployeeWithConstraints(String name, String designation) {
        List<Employee> dataEmployee = employeeRepository.getEmployeeWithNameAndDesignation(name,designation);
        List<ResponseEmployee> responseEmployee = new ArrayList<>();
        for (Employee employee : dataEmployee) {
            ResponseEmployee re = UtilityForMapping.employeeResponse(employee);
            responseEmployee.add(re);
        }
        return responseEmployee;

    }
    @Override
    public ResponseEmployee getEmployeeWithID(String id) {
        Employee dataEmployee = employeeRepository.getEmployeeByID(id);
        ResponseEmployee responseEmployee = UtilityForMapping.employeeResponse(dataEmployee);

        return responseEmployee;


    }
    @Override
    public List<ResponseForAggregate> getSalary(String designation) {
        List<ResponseForAggregate> ans = employeeRepository.getTotalSalary(designation);
        return ans;
    }


}
