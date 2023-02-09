package com.springBootwithMongo.demo.controller;

import com.mongodb.client.result.DeleteResult;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.model.response.ResponseForAggregate;
import com.springBootwithMongo.demo.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@Slf4j

public class EmployeeController {


    @Autowired
    private final EmployeeService service;
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee")
    public ResponseEntity<List<ResponseEmployee>> getEmployees() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAllEmployee());

    }

    @PostMapping("/employee")
    public ResponseEntity<ResponseEmployee> createEmployee(@Valid @RequestBody CreateEmployeeRequest createEmployeeRequest){
        log.debug(createEmployeeRequest.toString());
        return ResponseEntity.status(HttpStatus.OK).body(service.newEmployee(createEmployeeRequest));
    }

    @GetMapping("/find")
    public ResponseEntity<List<List>> getEmployeeBased(@RequestParam("name") @NotBlank(message = "name should be mentioned") String name,
                                                       @RequestParam("designation") @NotBlank(message = "designation should be mentioned") String designation) {
        List<List> answer = new ArrayList<>();
        answer.add(service.getEmployeeWithConstraints(name, designation));

        return ResponseEntity.status(HttpStatus.OK).body(answer);
    }

    @DeleteMapping(value = "delete/{id}")
    public ResponseEntity<DeleteResult> deleteEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deleteEmployee(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseEmployee> updateEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id, @Valid @RequestBody UpdateDTO update) {
        return ResponseEntity.status(HttpStatus.OK).body(service.updateEmployee(id, update));

    }

    @GetMapping("/salary")
    public ResponseEntity<List<ResponseForAggregate>> getSalary(@NotBlank(message = "Please enter designation") @RequestParam(value = "designation") String designation){
        return ResponseEntity.status(HttpStatus.OK).body(service.getSalary(designation));
    }

}





