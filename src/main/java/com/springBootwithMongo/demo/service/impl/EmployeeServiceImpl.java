package com.springBootwithMongo.demo.service.impl;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.request.UpdateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;
import com.springBootwithMongo.demo.model.response.SalaryResponse;
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

    public List<EmployeeResponse> getAllEmployee() {
        List<EmployeeResponse> responseEmployee = new ArrayList<>();
        List<Employee> dataEmployee = employeeRepository.getAllEmployee();
        for (Employee e : dataEmployee) {
            EmployeeResponse re = UtilityForMapping.employeeResponse(e);
            responseEmployee.add(re);
        }
        log.info("getAllEmployee in Service is accessed and response sent is {}",responseEmployee);
        return responseEmployee;
    }
    @Override
    public EmployeeResponse newEmployee(CreateEmployeeRequest createEmployeeRequest){
        Employee employee = UtilityForMapping.employeeData(createEmployeeRequest);
        Employee savedEmployee = employeeRepository.saveEmployee(employee);
        EmployeeResponse responseEmployee = UtilityForMapping.employeeResponse(savedEmployee);
        log.info("newEmployee in Service is accessed and response sent is {}",responseEmployee);
        return responseEmployee;
    }

    @Override
    public DeleteResult deleteEmployee(String id) {
        Employee employee = employeeRepository.getEmployeeByID(id);
        DeleteResult deleteResult = employeeRepository.deleteEmployee(employee);
        log.info("deleteEmployee in Service is accessed and response sent is {}",deleteResult);
        return  deleteResult;
    }

    @Override
    public EmployeeResponse updateEmployee(String id, UpdateEmployeeRequest update){
        Employee savedEmployee = employeeRepository.getEmployeeByID(id);
        UtilityForMapping.updateEmployee(update,savedEmployee);
        EmployeeResponse responseEmployee = UtilityForMapping.employeeResponse(savedEmployee);
        log.info("updateEmployee in Service is accessed and response sent is {}",responseEmployee);
        return responseEmployee;
    }
    public List<EmployeeResponse> getEmployeeWithConstraints(String name, String designation){
        List<Employee> dataEmployee = employeeRepository.getEmployeeWithNameAndDesignation(name,designation);
        List<EmployeeResponse> responseEmployee = new ArrayList<>();
        for (Employee employee : dataEmployee) {
            EmployeeResponse re = UtilityForMapping.employeeResponse(employee);
            responseEmployee.add(re);
        }
        log.info("getEmployeeWithConstraints in Service is accessed and response sent is {}",responseEmployee);
        return responseEmployee;}
    @Override
    public EmployeeResponse getEmployeeWithID(String id) {
        Employee dataEmployee = employeeRepository.getEmployeeByID(id);
        EmployeeResponse responseEmployee = UtilityForMapping.employeeResponse(dataEmployee);
        log.info("getEmployeeWithId in Service is accessed and response sent is {}",responseEmployee);
        return responseEmployee;
    }
    @Override
    public List<SalaryResponse> getSalary(String designation) {
        List<SalaryResponse> response = employeeRepository.getTotalSalary(designation);
        log.info("getSalary in Service is accessed and response sent is {}",response);
        return response;}


}
