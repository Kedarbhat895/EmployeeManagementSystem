package com.springBootwithMongo.demo;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.UpdateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;
import com.springBootwithMongo.demo.model.response.SalaryResponse;
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
    @InjectMocks
    private EmployeeServiceImpl employeeService;


    @Test
    public void testNewEmployee() {
        List<Employee> list = new ArrayList<Employee>();
        Employee empOne = new Employee("1", "John", "John", "19/07/2010",19,6000.00);
        Employee empTwo = new Employee("2", "Alex", "kolenchiski", "alexk@yahoo.com",20,500.00);
        Employee empThree = new Employee("3", "Steve", "Waugh", "swaugh@gmail.com",21,9090.00);

        list.add(empOne);
        list.add(empTwo);
        list.add(empThree);

        when(employeeRepository.getAllEmployee()).thenReturn(list);
        List<EmployeeResponse> empList = employeeService.getAllEmployee();
        assertEquals(3, empList.size());
        verify(employeeRepository, times(1)).getAllEmployee();}

    @Test
    public void createEmployeeTest()
    {
        CreateEmployeeRequest createEmployeeRequest1 = new CreateEmployeeRequest("1000","kedar","intern","19/05/2001",9000.00);
        Employee employee = new Employee("1000","kedar","intern","19/05/2001",21,9000.00);
        EmployeeResponse expectedResult = new EmployeeResponse("1000","kedar","intern",9000.00,"19/05/2001",21);
        when(employeeRepository.saveEmployee(any(Employee.class))).thenReturn(employee);
        EmployeeResponse actualResult = employeeService.newEmployee(createEmployeeRequest1);
        assertEquals(expectedResult.getEmployeeId(),actualResult.getEmployeeId());
        verify(employeeRepository,times(1)).saveEmployee(employeeArgumentCaptor.capture());
    }



    @Test
    void testForUpdate(){
        String id = "1000";
        UpdateEmployeeRequest update = new UpdateEmployeeRequest(8000.00,"manager","kedar","19/05/2001",21);
        Employee employee1 = new Employee("1000","kedar","intern","19/05/2001",21,9000.00);
        Employee employee2 = new Employee("1000","kedar","manager","19/05/2001",21,8000.00);
        EmployeeResponse expectedResult = new EmployeeResponse("1000","kedar","manager",8000.00,"19/05/2001",21);
        when(employeeRepository.getEmployeeByID(id)).thenReturn(employee1);
        when(employeeRepository.updateEmployee(any(Employee.class))).thenReturn(employee2);
        EmployeeResponse responseEmployee = employeeService.updateEmployee(id,update);
        assertEquals(expectedResult.getEmployeeSalary(),responseEmployee.getEmployeeSalary());
        verify(employeeRepository,times(1)).getEmployeeByID(id);
        verify(employeeRepository,times(1)).updateEmployee(employeeArgumentCaptor.capture());
    }

    @Test
    public void testDeleteEmployeeSuccess() {
        String id = "1000";
        Employee employee1 = new Employee("1000","kedar","intern","19/05/2001",21,9000.00);
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
        verify(employeeRepository,times(1)).getEmployeeByID(id);
        verify(employeeRepository,times(1)).deleteEmployee(employee1);
        assertEquals(expectedDeleteResult.getDeletedCount(), actualDeleteResult.getDeletedCount());
    }

    @Test
    void testForGetSalary(){
        String designation = "abc";
        List<SalaryResponse> expectedResult = new ArrayList<>();
        when(employeeRepository.getTotalSalary(designation)).thenReturn(expectedResult);
        List<SalaryResponse> actualResult = employeeService.getSalary(designation);
        verify(employeeRepository).getTotalSalary(designation);
        assertEquals(expectedResult,actualResult);
    }

    @Test
    void testForGetSalaryWithNullInput() {
        List<SalaryResponse> expectedResult = Collections.emptyList();
        when(employeeRepository.getTotalSalary(null)).thenReturn(expectedResult);
        List<SalaryResponse> actualResult = employeeService.getSalary(null);
        verify(employeeRepository).getTotalSalary(null);
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testForGetWithConstraints(){
        String name = "name";
        String designation = "designation";
        List<Employee> expectedResult = new ArrayList<>();
        when(employeeRepository.getEmployeeWithNameAndDesignation(name,designation)).thenReturn(expectedResult);
        List<EmployeeResponse> actualResult = employeeService.getEmployeeWithConstraints(name,designation);
        verify(employeeRepository).getEmployeeWithNameAndDesignation(name,designation);
        assertEquals(expectedResult.size(),actualResult.size());
    }

    @Test
    void testForGetEmployeeWithID(){
        String id = "123";
        Employee employee = new Employee("123","kedar","intern","19/05/2001",21,9000.00);
        EmployeeResponse expected = new EmployeeResponse("123","kedar","intern",9000.00,"19/05/2001",21);
        when(employeeRepository.getEmployeeByID(id)).thenReturn(employee);
        EmployeeResponse actual = employeeService.getEmployeeWithID("123");
        verify(employeeRepository).getEmployeeByID(id);
        assertEquals(expected.getEmployeeSalary(),actual.getEmployeeSalary());
    }
}



