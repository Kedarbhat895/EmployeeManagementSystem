package com.springBootwithMongo.demo.utility;

import com.springBootwithMongo.demo.model.Employee;
import com.springBootwithMongo.demo.model.request.CreateEmployeeRequest;
import com.springBootwithMongo.demo.model.response.ResponseEmployee;

import java.time.LocalDate;
import java.time.Period;

public class generalUtility {

    public static int getAge(String dob){


        String date[] = dob.split("/");
        int year = Integer.parseInt(date[2]);
        int month = Integer.parseInt(date[1]);
        int day = Integer.parseInt(date[0]);
        LocalDate d = LocalDate.of(year, month, day);
        LocalDate Cur = LocalDate.now();
        int age = Period.between(d, Cur).getYears();

        return age;
    }

    public static void mapDataToResponse(ResponseEmployee re, Employee e){
        re.setEmployeeId(e.getEmployeeID());
        re.setEmployeeDob(e.getEmployeeDob());
        re.setEmployeeDesignation(e.getEmployeeDesignation());
        re.setEmployeeName(e.getEmployeeName());
        re.setEmployeeSalary(e.getEmployeeSalary());
        re.setAge(e.getAge());
    }

    public static void mapRequestToData(CreateEmployeeRequest e, Employee re){

        re.setEmployeeID(e.getEmployeeId());
        System.out.println("re id----->"+re.getEmployeeID());
        System.out.println("e id----->"+e.getEmployeeId());
        re.setEmployeeDob(e.getEmployeeDob());
        re.setEmployeeDesignation(e.getEmployeeDesignation());
        re.setEmployeeName(e.getEmployeeName());
        re.setEmployeeSalary(e.getEmployeeSalary());
        int age = generalUtility.getAge(e.getEmployeeDob());
        re.setAge(age);
    }

    public static void mapRequestToResponse(CreateEmployeeRequest e,ResponseEmployee re){
        re.setEmployeeId(e.getEmployeeId());
        re.setEmployeeDob(e.getEmployeeDob());
        re.setEmployeeDesignation(e.getEmployeeDesignation());
        re.setEmployeeName(e.getEmployeeName());
        re.setEmployeeSalary(e.getEmployeeSalary());
//        re.setAge(e.);

    }
}
