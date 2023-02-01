package com.springBootwithMongo.demo.service.implementation;

import com.springBootwithMongo.demo.model.Employee;
//import com.springBootwithMongo.demo.model.RequestDTO;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import com.springBootwithMongo.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.springBootwithMongo.demo.utility.generalUtility;

import java.util.ArrayList;
import java.util.List;

import static com.springBootwithMongo.demo.utility.generalUtility.mapDataToResponse;
import static com.springBootwithMongo.demo.utility.generalUtility.mapRequestToData;

@Service

public class EmployeeServiceImplementation implements EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<ResponseEmployee> getAllEmployee() {
        List<ResponseEmployee> responseEmployee = new ArrayList<>();
        List<Employee> dataEmployee = employeeRepository.getAllEmployee();
        for (Employee e : dataEmployee) {
            ResponseEmployee re = new ResponseEmployee();
            generalUtility.mapDataToResponse(re, e);
            responseEmployee.add(re);
        }
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

    public List<List> getEmployeeWithConstraints(String name, String designantion) {


        List<List> queryAnswer = new ArrayList<>();
        System.out.println(employeeRepository.getEmployeeByName(name));
        System.out.println(employeeRepository.getEmployeeByDesignation(designantion));

        queryAnswer.add(employeeRepository.getEmployeeByName(name));
        queryAnswer.add(employeeRepository.getEmployeeByDesignation(designantion));
        return queryAnswer;
    }


    @Override
    public List<Employee> getEmployeeWithID(String id) {
        List<Employee> arr = new ArrayList<>();
        arr.add(employeeRepository.getEmployeeByID(id));
        return arr;


    }

}
