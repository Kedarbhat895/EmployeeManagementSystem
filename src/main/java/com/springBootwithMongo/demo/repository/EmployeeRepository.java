package com.springBootwithMongo.demo.repository;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;

import java.util.List;


public interface EmployeeRepository {

    void saveEmployee(Employee employee);
    List<Employee> getAllEmployee();


    void updateEmployee(Employee employee);

    void deleteEmployee(Employee employee);

    Employee getEmployeeByID(String id);

    List<Employee> getEmployeeByName(String name);
    List<Employee> getEmployeeByDesignation(String designantion);


//    Person savePerson(Person person);
//    List<Person> getAllPerson();
//    List<Person> getAllPersonPaginated(
//            int pageNumber, int pageSize);
//    Person findOneByName(String name);
//    List<Person> findByName(String name);
//    List<Person> findByBirthDateAfter(Date date);
//    List<Person> findByAgeRange(int lowerBound, int upperBound);
//    List<Person> findByFavoriteBooks(String favoriteBook);
//    void updateMultiplePersonAge();
//    Person updateOnePerson(Person person);
//    void deletePerson(Person person);
}