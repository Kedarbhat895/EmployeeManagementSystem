package com.springBootwithMongo.demo;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;
import com.springBootwithMongo.demo.repository.EmployeeRepository;
import com.springBootwithMongo.demo.service.impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NewServiceTests {

    @Mock
    private EmployeeRepository employeeRepository;

    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;
    private static CreateEmployeeRequest createEmployeeRequest;
    private static Employee employeeData;
    private static ResponseEmployee responseEmployee;
    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void testNewEmployee() {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("1", "John", "John", "19/07/2010",19,6000);
        Employee empTwo = new Employee("2", "Alex", "kolenchiski", "alexk@yahoo.com",20,500);
        Employee empThree = new Employee("3", "Steve", "Waugh", "swaugh@gmail.com",21,9090);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.getAllEmployee()).thenReturn(list);
        List<ResponseEmployee> empList = employeeService.getAllEmployee();
        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).getAllEmployee();}

    @Test
    public void createEmployeeTest()
    {
        CreateEmployeeRequest createEmployeeRequest1 = new CreateEmployeeRequest("1000","kedar","intern","19/05/2001",9000);
        Employee employee = new Employee("1000","kedar","intern","19/05/2001",9000,21);
        ResponseEmployee expectedResult = new ResponseEmployee("1000","kedar","intern",9000,"19/05/2001",21);
        when(employeeRepository.saveEmployee(any(Employee.class))).thenReturn(employee);
        ResponseEmployee actualResult = employeeService.newEmployee(createEmployeeRequest1);
        assertEquals(expectedResult.getEmployeeId(),actualResult.getEmployeeId());
        verify(employeeRepository,times(1)).saveEmployee(employeeArgumentCaptor.capture());
//        System.out.println("--->"+employeeArgumentCaptor.getValue());
    }



    @Test
    void testForUpdate(){
        String id = "123";
        UpdateDTO update = new UpdateDTO(8000,"manager");
        Employee employee1 = new Employee("1000","kedar","intern","19/05/2001",21,9000);
        Employee employee2 = new Employee("1000","kedar","manager","19/05/2001",21,8000);
        ResponseEmployee expectedResult = new ResponseEmployee("1000","kedar","manager",8000,"19/05/2001",21);
        when(employeeRepository.getEmployeeByID(id)).thenReturn(employee1);
        when(employeeRepository.updateEmployee(any(Employee.class))).thenReturn(employee2);
        ResponseEmployee responseEmployee = employeeService.updateEmployee(id,update);
        assertEquals(expectedResult.getEmployeeSalary(),responseEmployee.getEmployeeSalary());
        verify(employeeRepository).getEmployeeByID(id);
        verify(employeeRepository,times(1)).updateEmployee(employee2);
    }

    @Test
    public void testDeleteEmployeeSuccess() {
        String id = "123";
        Employee employee1 = new Employee();
        DeleteResult expectedDeleteResult = new DeleteResult() {
            @Override
            public boolean wasAcknowledged() {
                return false;
            }

            @Override
            public long getDeletedCount() {
                return 0;
            }
        };
        when(employeeRepository.getEmployeeByID(id)).thenReturn(employee1);
        when(employeeRepository.deleteEmployee(any(Employee.class))).thenReturn(expectedDeleteResult);
        DeleteResult actualDeleteResult = employeeService.deleteEmployee(id);
        verify(employeeRepository).getEmployeeByID(id);
        verify(employeeRepository).deleteEmployee(employee1);
        assertEquals(expectedDeleteResult, actualDeleteResult);
    }

    @Test
    void testForGetSalary(){
        String designation = "abc";
        List<ResponseForAggregate> expectedResult = new ArrayList<>();
        when(employeeRepository.getTotalSalary(designation)).thenReturn(expectedResult);
        List<ResponseForAggregate> actualResult = employeeService.getSalary(designation);
        verify(employeeRepository).getTotalSalary(designation);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void testForGetSalaryWithNullInput() {
        List<ResponseForAggregate> expectedResult = Collections.emptyList();
        when(employeeRepository.getTotalSalary(null)).thenReturn(expectedResult);
        List<ResponseForAggregate> actualResult = employeeService.getSalary(null);
        verify(employeeRepository).getTotalSalary(null);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testForGetWithConstraints(){
        String name = "name";
        String designation = "designation";
        List<Employee> expectedResult = new ArrayList<>();
        when(employeeRepository.getEmployeeWithNameAndDesignation(name,designation)).thenReturn(expectedResult);
        List<ResponseEmployee> actualResult = employeeService.getEmployeeWithConstraints(name,designation);
        verify(employeeRepository).getEmployeeWithNameAndDesignation(name,designation);
        assertEquals(expectedResult.size(),actualResult.size());
    }

    @Test
    void testForGetEmployeeWithID(){
        String id = "123";
        Employee employee = new Employee("123","kedar","intern","19/05/2001",21,9000);
        ResponseEmployee expected = new ResponseEmployee("123","kedar","intern",9000,"19/05/2001",21);
        when(employeeRepository.getEmployeeByID(id)).thenReturn(employee);
        ResponseEmployee actual = employeeService.getEmployeeWithID("123");
        verify(employeeRepository).getEmployeeByID(id);
        assertEquals(expected.getEmployeeSalary(),actual.getEmployeeSalary());
    }
}



