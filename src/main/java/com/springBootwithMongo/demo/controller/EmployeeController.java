package com.springBootwithMongo.demo.controller;

import com.springBootwithMongo.demo.DemoApplication;
import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;
import com.springBootwithMongo.demo.service.EmployeeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
//import java.util.logging.Logger;

@RestController
@Validated

public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);

    @Autowired
    private final EmployeeService service;


    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employee")
    public List<ResponseEmployee> getEmployees() {

        logger.debug("Response sent",service.getAllEmployee());
        logger.info("Response sent info ",service.getAllEmployee());

        return service.getAllEmployee();

    }

    @PostMapping("/employee")
    public ResponseEmployee createEmployee(@Valid @RequestBody CreateEmployeeRequest employee) {
        ResponseEmployee responseEmployee = new ResponseEmployee();
        responseEmployee = service.newEmployee(employee);
        return responseEmployee;
    }

    @GetMapping("/find")
    public List<List> getEmployeeBased(@RequestParam("name") @NotBlank(message = "name should be mentioned") String name, @RequestParam("designation") @NotBlank(message = "designation should be mentioned") String designation) {

        List<List> answer = new ArrayList<>();
        answer.add(service.getEmployeeWithConstraints(name, designation));

        return answer;


    }


    @GetMapping("/id")
    public List<List> getIDEmployee(@RequestParam(value = "id") @NotBlank(message = "enter a valid id") String id) {
        List<List> n = new ArrayList<>();
        n.add(service.getEmployeeWithID(id));
        return n;

    }


    @DeleteMapping(value = "delete/{id}")
    public ResponseEmployee deleteEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id) {
        ResponseEmployee responseEmployee = new ResponseEmployee();
        responseEmployee = service.deleteEmployee(id);

        return responseEmployee;
    }

    @PostMapping("/update")
    public ResponseEmployee updateEmployee(@PathVariable @NotBlank(message = "enter a valid id") String id, @Valid @RequestBody UpdateDTO update) {

        ResponseEmployee responseEmployee = new ResponseEmployee();
        responseEmployee = service.updateEmployee(id, update);
        return responseEmployee;
    }

}





