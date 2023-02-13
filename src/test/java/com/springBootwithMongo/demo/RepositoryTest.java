package com.springBootwithMongo.demo;


import com.springBootwithMongo.demo.model.entity.Employee;
import com.springBootwithMongo.demo.repository.impl.EmployeeRepositoryImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RepositoryTest {


    @Mock
    public MongoTemplate mongoTemplate;
    @Captor
    private ArgumentCaptor<Employee> employeeArgumentCaptor;
    @InjectMocks
    private EmployeeRepositoryImpl employeeRepository;
    @BeforeEach
    private void setUp() {
         MockitoAnnotations.initMocks(this);

    }



//    @Test
//    public void testUpdateEmployee() {
//        Employee employee = new Employee("1000","kedar","manager","19/05/2001",21,10000);
//        Employee expected = new Employee("1000","kedar","manager","19/05/2001",21,10000);
//        when(mongoTemplate.save(employee)).thenReturn(expected);
//        Employee actual = employeeRepository.updateEmployee(employee);
//
//        verify(mongoTemplate).save(employee);
//        assertEquals(employee, updatedEmployee);
//    }

//    @Test
//    public void testGetAllEmployee(){
//        List<Employee> list = new ArrayList<Employee>();
//        Employee empOne = new Employee("1", "John", "John", "19/07/2010",19,6000);
//        Employee empTwo = new Employee("2", "Alex", "kolenchiski", "alexk@yahoo.com",20,500);
//        Employee empThree = new Employee("3", "Steve", "Waugh", "swaugh@gmail.com",21,9090);
//        list.add(empOne);
//        list.add(empTwo);
//        list.add(empThree);
//        Employee employee = new Employee();
//
//        when(mongoTemplate.findAll(any())).thenReturn(Collections.singletonList(list));
//        List<Employee> actual = employeeRepository.getAllEmployee();
//        assertEquals(actual.size(),list.size());
//        verify(mongoTemplate).findAll(any());
//
//    }
//    @Test
//    void testForDelete(){
//        Employee employee1 = new Employee();
//        DeleteResult expectedDeleteResult = new DeleteResult() {
//            @Override
//            public boolean wasAcknowledged() {
//                return false;
//            }
//            @Override
//            public long getDeletedCount() {
//                return 0;
//            }
//        };
//
//        when(mongoTemplate.remove(any())).thenReturn((ExecutableRemoveOperation.ExecutableRemove<Object>) expectedDeleteResult);
//        DeleteResult actual = employeeRepository.deleteEmployee(employee1);
//
//        assertEquals(expectedDeleteResult,actual);
//        verify(mongoTemplate).remove(employee1);
//
//
//    }

//    @Test
//    public void testInsert() {
//        // Define test data
//        Employee user = new Employee("1000","John","manager","19/09/2009",14,800);
//
//        mongoTemplate.insert(user);
//
//        // Validate that the test data was inserted correctly
//        Employee savedUser = mongoTemplate.findById(user.getEmployeeID(), Employee.class);
//        assertEquals(user, savedUser);
//    }

}
