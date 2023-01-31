package com.springBootwithMongo.demo.service.implementation;

import com.springBootwithMongo.demo.model.Employee;
//import com.springBootwithMongo.demo.model.RequestDTO;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import com.springBootwithMongo.demo.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service

public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee() {
//
//        Employee employee1 = new Employee("2334","Kedar","Engineer","19/05/2001",20000);
//        Employee employee2 = new Employee("2335","Manja","HR","23/09/1997",15000);
//        repository.save(employee1);
//        repository.save(employee2);
        return employeeRepository.getAllEmployee();


    }

    @Override
    public void newEmployee(CreateEmployeeRequest employee) {


        Employee employee1 = new Employee();
//        System.out.println("EMployee Name____>"+employee.getEmployeeName());
        employee1.setEmployeeName(employee.getEmployeeName());
//        System.out.println("EMployee NewName____>"+employee1.getEmployeeName());

//        employee1.setEmployeeId(employee.getEmployeeId());
//        System.out.println("EMployee Name____>"+employee.getEmployeeDob());
        employee1.setEmployeeDob(employee.getEmployeeDob());
//        System.out.println("EMployee Name____>"+employee1.getEmployeeDob());
        employee1.setEmployeeDesignation(employee.getEmployeeDesignation());
        employee1.setEmployeeSalary(employee.getEmployeeSalary());
        employee1.setAge(employee.getAge(employee.getEmployeeDob()));



        employeeRepository.saveEmployee(employee1);
    }

    @Override
    public void deleteEmployee(String id) {
//        Query query = new Query();







        Employee employee = employeeRepository.getEmployeeByID(id);
        employeeRepository.deleteEmployee(employee);


//        repository.deleteById(id);

//        return null;
    }

    @Override
    public void updateEmployee(String id, UpdateDTO update) {
        Employee employee = employeeRepository.getEmployeeByID(id);


        employee.setEmployeeDesignation(update.getDesignation());
        employee.setEmployeeSalary(update.getSalary());


        employeeRepository.updateEmployee(employee);
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
    public List<Employee> getEmployeeWithID(String id){
        List<Employee> arr = new ArrayList<>();
        arr.add(employeeRepository.getEmployeeByID(id));
        return arr;


    }

}
