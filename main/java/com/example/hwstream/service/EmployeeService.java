package com.example.hwstream.service;

import com.example.hwstream.Employee;

import java.util.Collection;

public interface EmployeeService {


    Employee add(String firstName, String lastName, int department, double salary);
    Employee remove(String firstName, String lastName, int department, double salary);
    Employee find(String firstName, String lastName, int department, double salary);

    Collection<Employee> findAll();


    boolean getAll();
}
