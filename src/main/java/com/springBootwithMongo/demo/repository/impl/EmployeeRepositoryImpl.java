package com.springBootwithMongo.demo.repository.impl;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.exceptions.EmployeeNotFoundException;
import com.springBootwithMongo.demo.exceptions.MongoException;
import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.response.SalaryResponse;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;


@Repository
@Slf4j

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public EmployeeRepositoryImpl(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public Employee saveEmployee(Employee employee) {
        try {
            log.debug("saveEmployee in repository is accessed and request received is {}",employee);
            Employee employee1 = mongoTemplate.save(employee);
            log.debug("saveEmployee in repository is accessed and response sent is {}",employee1);
            return employee1;
        } catch (MongoException mongoException){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public List<Employee> getAllEmployee() {
        try{
            List<Employee> employees = mongoTemplate.findAll(Employee.class);
            log.debug("getAllEmployee in repository is accessed and response sent is {}",employees);
            return employees;
        } catch (MongoException mongoException){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public Employee updateEmployee(Employee employee){
        try {
            log.debug("updateEmployee in repository is accessed and request received is {}",employee);
            Employee employee1= mongoTemplate.save(employee);
            log.debug("updateEmployee in repository is accessed and response sent is {}",employee1);
            return employee1;
        } catch (MongoException mongoException){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public DeleteResult deleteEmployee(Employee employee) {
        try {
            log.debug("deleteEmployee in repository is accessed and request received is {}",employee);
            DeleteResult deleteResult = mongoTemplate.remove(employee);
            log.debug("deleteEmployee in repository is accessed and response sent is {}",deleteResult);
            return deleteResult;
        } catch (MongoException mongoException){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }

    @Override
    public Employee getEmployeeByID(String id) {
        try {
            log.debug("getEmployeeById in repository is accessed and request received is {}",id);
            Employee employee = mongoTemplate.findById(id, Employee.class);
            if(employee==null){
                throw new EmployeeNotFoundException(id);
            }
            log.debug("getEmployeeById in repository is accessed and response sent is {}",employee);
            return employee;
        } catch (MongoException mongoException){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }

    }

    @Override
    public List<Employee> getEmployeeByName(String name){
        try {
            log.debug("getEmployeeByName in repository is accessed and request received is {}",name);
            Query query = new Query();
            query.addCriteria(Criteria.where("employeeName").is(name));
            List<Employee> employees = mongoTemplate.find(query, Employee.class);
            log.debug("getEmployeeByName in repository is accessed and response sent is {}",employees);
            if (employees.isEmpty()) {
                log.error("Employee with name {} not found",name);
                throw new EmployeeNotFoundException(name);
            }
            return employees;
        } catch(MongoException mongoException){
            log.error("MonoDB Error",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }

    @Override
    public List<Employee> getEmployeeByDesignation(String designation) {
        try {
            log.debug("getEmployeeByDesignation in repository is accessed and request received is {}",designation);
            Query query = new Query();
            query.addCriteria(Criteria.where("employeeDesignation").is(designation));
            List<Employee> employees = mongoTemplate.find(query, Employee.class);
            log.debug("getEmployeeByDesignation in repository is accessed and response sent is {}",employees);
            if(employees.isEmpty()) {
                log.error("Employee with designation {} not found",designation);
                throw new EmployeeNotFoundException(designation);

            }
            return employees;
        } catch (MongoException mongoException){
            log.error("MongoDB Error",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();

        }


    }
    @Override
    public List<Employee> getEmployeeWithNameAndDesignation(String name, String designation) {
        try {
            log.debug("getEmployeeByDesignationAndDesignation in repository is accessed and request received is {},{}",name,designation);
            Query query = new Query();
            query.addCriteria(Criteria.where("employeeName").is(name).andOperator(Criteria.where("employeeDesignation").is(designation)));
            List<Employee> employees = mongoTemplate.find(query, Employee.class);
            log.debug("getEmployeeByDesignation in repository is accessed and response sent is {}",employees);
            if(employees.isEmpty()) {
                log.error("Employee with designation {} and {} not found",name,designation);
                throw new EmployeeNotFoundException(designation);
            }
            return employees;
        } catch (MongoException mongoException){
            log.error("Mongo Error",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }


    }
    @Override
    public  List<SalaryResponse> getTotalSalary(String designation) {
        try {
            log.debug("getTotalSalary in repository is accessed and request received is {}",designation);
            MatchOperation mathDesignation = match(Criteria.where("employeeDesignation").is(designation));
            GroupOperation groupByDesignation = group("employeeDesignation").sum("employeeSalary").as("totalSalary");
            Aggregation aggregation = newAggregation(mathDesignation,groupByDesignation);
            AggregationResults<SalaryResponse> result = mongoTemplate.aggregate(aggregation, Employee.class, SalaryResponse.class);
            log.debug("getTotalSalary in repository is accessed and responseSent is {}",result.getMappedResults());
            return result.getMappedResults();
        } catch (MongoException mongoException){
            log.error("MongoException occurred");
            throw new MongoException();
        }
    }
}
