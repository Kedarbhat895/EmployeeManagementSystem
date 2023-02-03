package com.springBootwithMongo.demo.service.impl;

import com.springBootwithMongo.demo.model.Employee;
//import com.springBootwithMongo.demo.model.RequestDTO;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import com.springBootwithMongo.demo.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.springBootwithMongo.demo.utility.generalUtility.mapDataToResponse;
import static com.springBootwithMongo.demo.utility.generalUtility.mapRequestToData;

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
            ResponseEmployee re = new ResponseEmployee();
            mapDataToResponse(re, e);
            responseEmployee.add(re);
        }
        log.info("getAllEmployee in Service is accessed {}",responseEmployee);
        return responseEmployee;
    }
    @Override
    public ResponseEmployee newEmployee(CreateEmployeeRequest employee) {
        Employee employee1 = new Employee();
        ResponseEmployee responseEmployee = new ResponseEmployee();
        mapRequestToData(employee, employee1);
        mapDataToResponse(responseEmployee, employee1);
        employeeRepository.saveEmployee(employee1);

        return responseEmployee;
    }

    @Override
    public ResponseEmployee deleteEmployee(String id) {
        ResponseEmployee responseEmployee = new ResponseEmployee();
        Employee employee = employeeRepository.getEmployeeByID(id);
        mapDataToResponse(responseEmployee, employee);
        employeeRepository.deleteEmployee(employee);
        return responseEmployee;
    }

    @Override
    public ResponseEmployee updateEmployee(String id, UpdateDTO update) {
        Employee employee = employeeRepository.getEmployeeByID(id);
        ResponseEmployee responseEmployee = new ResponseEmployee();
        employee.setEmployeeDesignation(update.getDesignation());
        employee.setEmployeeSalary(update.getSalary());
        employeeRepository.updateEmployee(employee);
        mapDataToResponse(responseEmployee, employee);
        return responseEmployee;
    }


    public List<ResponseEmployee> getEmployeeWithConstraints(String name, String designation) {
        List<Employee> dataEmployee = employeeRepository.getEmployeeWithNameAndDesignation(name,designation);
        List<ResponseEmployee> responseEmployee = new ArrayList<>();
        for (Employee e : dataEmployee) {
            ResponseEmployee re = new ResponseEmployee();
            mapDataToResponse(re, e);
            responseEmployee.add(re);
        }
        return responseEmployee;

    }
    @Override
    public List<Employee> getEmployeeWithID(String id) {
        List<Employee> arr = new ArrayList<>();
        arr.add(employeeRepository.getEmployeeByID(id));
        return arr;


    }
    @Override
    public List<ResponseForAggregate> getSalary(String designation) {
        List<ResponseForAggregate> ans = employeeRepository.getTotalSalary(designation);
        return ans;
    }


}
