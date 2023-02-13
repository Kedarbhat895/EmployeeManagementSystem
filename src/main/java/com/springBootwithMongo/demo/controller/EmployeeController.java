package com.springBootwithMongo.demo.controller;

import com.mongodb.client.result.DeleteResult;
//import com.springBootwithMongo.demo.KafkaProducer;
import com.springBootwithMongo.demo.KafkaProducer;
import com.springBootwithMongo.demo.model.request.UpdateEmployeeRequest;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.EmployeeResponse;
import com.springBootwithMongo.demo.model.response.SalaryResponse;
import com.springBootwithMongo.demo.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@Validated
@Slf4j

public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.employeeService = service;
    }

    @GetMapping("/getallemployee") //todo - naming
    public ResponseEntity<List<EmployeeResponse>> getAllEmployee() {
        log.debug("Received a request to get all employees");
        List<EmployeeResponse> employeeResponse = employeeService.getAllEmployee();
        log.debug("Response sent {}",employeeResponse);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);

    }

    @PostMapping("/createemployee")
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        log.debug("Received a request to create a employee {}",createEmployeeRequest);
        EmployeeResponse employeeResponse=employeeService.newEmployee(createEmployeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @GetMapping("/find") //todo Change list(list) to responseEmployee
    public ResponseEntity<List<EmployeeResponse>> getEmployeeBased(@RequestParam("name") @NotBlank(message = "name should be mentioned") String name,
                                                       @RequestParam("designation") @NotBlank(message = "designation should be mentioned") String designation) {
        List<EmployeeResponse> response = employeeService.getEmployeeWithConstraints(name, designation);
        log.debug("Received a request to find employee with {} and {}",name,designation);
        log.debug("Response sent {}",response);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id) {
        log.debug("Received a request to delete employee with id {}",id);
        DeleteResult result = employeeService.deleteEmployee(id);
        return ResponseEntity.status(HttpStatus.OK).body("deleted");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id, @Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
        log.debug("Received a request to update employee for id {} and update request is {} ",id,updateEmployeeRequest);
        EmployeeResponse employeeResponse = employeeService.updateEmployee(id, updateEmployeeRequest);
        return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
    }

    @GetMapping("/salary")
    public ResponseEntity<List<SalaryResponse>> getSalary(@NotBlank(message = "Please enter designation") @RequestParam(value = "designation") String designation){
        log.debug("Received a request to get total salary of {} designation",designation);
        List<SalaryResponse> salaryResponse = employeeService.getSalary(designation);
        return ResponseEntity.status(HttpStatus.OK).body(salaryResponse);
    }

}





