package com.springBootwithMongo.demo;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.utility.UtilityForMapping;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UtilityTests {

    @InjectMocks
    UtilityForMapping generalUtility;

    @Test
    void testGetAge(){
        String dob = "19/05/2001";
        int expectedAge = 21;
        int actualAge = UtilityForMapping.getAge(dob);
        assertEquals(expectedAge,actualAge);
    }

    @Test
    void testMapDataToResponse(){
        ResponseEmployee expected = new ResponseEmployee("1","kedar","intern",9000,"19/05/2001",21);
        Employee employee = new Employee("1","kedar","intern","19/05/2001",21,9000);
        ResponseEmployee actual = UtilityForMapping.employeeResponse(employee);
        assertEquals(expected,actual);
    }

    @Test
    void testMapRequestToData(){
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest("1","Kedar","intern","19/05/2001",9000);
        Employee expected = new Employee("1","kedar","intern","19/05/2001",21,9000);
        Employee actual = UtilityForMapping.employeeData(createEmployeeRequest);
        assertEquals(expected.getEmployeeAge(),actual.getEmployeeAge());

    }


}
