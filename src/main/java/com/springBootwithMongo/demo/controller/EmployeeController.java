package com.springBootwithMongo.demo.controller;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.UpdateDTO;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller

public class EmployeeController {


    private EmployeeService service;


    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping("employee")
    @ResponseBody
    public List<Employee> getEmployees() {

        return service.getAllEmployee();

    }

    @RequestMapping(value = "employee", method = RequestMethod.POST)
    public String createEmployee(@Valid @RequestBody CreateEmployeeRequest employee){
        service.newEmployee(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "find",method=RequestMethod.GET)
    @ResponseBody
    public List<List> getEmployeeBased(@RequestParam("name") String name,@RequestParam("designation") String designation){
        List<List> answer = new ArrayList<>() ;
        answer.add(service.getEmployeeWithConstraints(name,designation));

        return answer;


    }


//    @RequestMapping(value = "employee/{id}",method = RequestMethod.GET)
//    public String getByEmployeeID(@PathVariable String id){
//        service.EmployeeID(id);
//        return "redirect:/employee";
//    }

    @RequestMapping(value = "id", method = RequestMethod.GET)
    @ResponseBody
    public List<List> getIDEmployee(@RequestParam(value = "id") String id) {
        List<List> n = new ArrayList<>();
        n.add(service.getEmployeeWithID(id));
        return n;

    }



    @RequestMapping(value = "employee/{id}", method = RequestMethod.DELETE)
    public String deleteEmployee(@PathVariable String id) {
        service.deleteEmployee(id);

        return "redirect:/employee";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String updateEmployee(@PathVariable String id, @RequestBody UpdateDTO update) {

//        service.updateEmployee(id, employee);
        service.updateEmployee(id,update);
//        ?
        return "redirect:/employee";
    }

}


/*
        service.updateEmployee(id,salary);
        System.out.println("employee from .get() is---------"+nEmployee);
*/





