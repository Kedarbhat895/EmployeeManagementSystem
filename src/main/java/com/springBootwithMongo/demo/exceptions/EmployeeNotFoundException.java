package com.springBootwithMongo.demo.exceptions;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String error){
        super(String.format("Employee with attribute %s not found",error));
    }


}
