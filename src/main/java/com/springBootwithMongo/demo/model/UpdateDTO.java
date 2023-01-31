package com.springBootwithMongo.demo.model;

public class UpdateDTO {
    private int salary;
    private String designation;

    public UpdateDTO(int salary, String designation) {
        this.salary = salary;
        this.designation = designation;
    }

    public int getSalary() {
        return salary;
    }

    public String getDesignation() {
        return designation;
    }
}
