package com.springBootwithMongo.demo.repository;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.core.query.Query;
import java.util.List;



@Repository

public class EmployeeImplementation implements EmployeeRepository {

    private final MongoTemplate mongoTemplate;

    @Autowired

    public EmployeeImplementation(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }


    @Override
    public void saveEmployee(Employee employee) {
        mongoTemplate.save(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
//        Employee employee1 = new Employee("2334","Kedar","Engineer","19/05/2001",20000,20);
//        Employee employee2 = new Employee("2335","Manja","HR","23/09/1997",15000,19);
//        mongoTemplate.save(employee1);
//        mongoTemplate.save(employee2);
        return mongoTemplate.findAll(Employee.class);
    }



    @Override
    public void updateEmployee(Employee employee){
        mongoTemplate.save(employee);
    }



    @Override
    public void deleteEmployee(Employee employee) {
        mongoTemplate.remove(employee);


    }

    @Override
    public Employee getEmployeeByID(String id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("id").is(id));

        System.out.println("Values are---------"+mongoTemplate.findById(id,Employee.class));

        return mongoTemplate.findById(id, Employee.class);
    }

    @Override
    public List<Employee> getEmployeeByName(String name){
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeName").is(name));
        return mongoTemplate.find(query,Employee.class);
    }

    @Override
    public List<Employee> getEmployeeByDesignation(String designation) {
        Query query = new Query();
        query.addCriteria(Criteria.where("employeeDesignation").is(designation));
        return mongoTemplate.find(query,Employee.class);

    }
}
