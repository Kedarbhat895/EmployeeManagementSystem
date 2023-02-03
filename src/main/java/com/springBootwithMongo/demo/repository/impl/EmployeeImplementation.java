package com.springBootwithMongo.demo.repository.impl;

import com.springBootwithMongo.demo.exceptions.EmployeeNotFoundException;
import com.springBootwithMongo.demo.exceptions.MongoException;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
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

public class EmployeeImplementation implements EmployeeRepository {
    private final MongoTemplate mongoTemplate;
    public EmployeeImplementation(MongoTemplate mongoTemplate) {

        this.mongoTemplate = mongoTemplate;
    }
    @Override
    public void saveEmployee(Employee employee) {
        try {
            mongoTemplate.save(employee);
        } catch (MongoException e){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public List<Employee> getAllEmployee() {
        try{
            return mongoTemplate.findAll(Employee.class);
        } catch (Exception e){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public void updateEmployee(Employee employee){
        try {
            mongoTemplate.save(employee);
        } catch (MongoException e){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }
    @Override
    public void deleteEmployee(Employee employee) {
        try {
            mongoTemplate.remove(employee);
        } catch (MongoException e){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }

    @Override
    public Employee getEmployeeByID(String id) {
        try {
            return mongoTemplate.findById(id, Employee.class);
        } catch (MongoException e){
            log.error("MongoDB Error", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }

    }

    @Override
    public List<Employee> getEmployeeByName(String name){
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("employeeName").is(name));
            List<Employee> ans = mongoTemplate.find(query, Employee.class);
            return ans;
        } catch(MongoException e){
            log.error("MonoDB Error",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }
    }

    @Override
    public List<Employee> getEmployeeByDesignation(String designation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeDesignation").is(designation));
        List<Employee> ans = mongoTemplate.find(query,Employee.class);
        if(ans.isEmpty()){
            throw new EmployeeNotFoundException(designation);
        }
        return ans;

    }
    @Override
    public List<Employee> getEmployeeWithNameAndDesignation(String name, String designation) {
        try {
            Query query = new Query();
            query.addCriteria(Criteria.where("employeeName").is(name).andOperator(Criteria.where("employeeDesignation").is(designation)));
            List<Employee> ans = mongoTemplate.find(query, Employee.class);
            return ans;
        } catch (MongoException e){
            log.error("Mongo Error",HttpStatus.INTERNAL_SERVER_ERROR);
            throw new MongoException();
        }


    }
    @Override
    public  List<ResponseForAggregate> getTotalSalary(String designation) {
        try {
            MatchOperation mathDesignation = match(Criteria.where("employeeDesignation").is(designation));
            GroupOperation groupByDesignation = group("employeeDesignation").sum("employeeSalary").as("totalSalary");
            Aggregation aggregation = newAggregation(mathDesignation,groupByDesignation);
            AggregationResults<ResponseForAggregate> result = mongoTemplate.aggregate(aggregation, Employee.class, ResponseForAggregate.class);
            return result.getMappedResults();
        } catch (MongoException e){
            log.error("MongoException occurred");
            throw new MongoException();
        }
    }
}
