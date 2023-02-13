package com.springBootwithMongo.demo;
import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;
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
        EmployeeResponse expected = new EmployeeResponse("1","kedar","intern",9000.00,"19/05/2001",21);
        Employee employee = new Employee("1","kedar","intern","19/05/2001",21,9000.00);
        EmployeeResponse actual = UtilityForMapping.employeeResponse(employee);
        assertEquals(expected,actual);
    }

    @Test
    void testMapRequestToData(){
        CreateEmployeeRequest createEmployeeRequest = new CreateEmployeeRequest("1","Kedar","intern","19/05/2001",9000.00);
        Employee expected = new Employee("1","kedar","intern","19/05/2001",21,9000.00);
        Employee actual = UtilityForMapping.employeeData(createEmployeeRequest);
        assertEquals(expected.getEmployeeAge(),actual.getEmployeeAge());

    }


}
