package com.example.hwstream.service;

import com.example.hwstream.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
    public class EmployeeServiceImpl implements EmployeeService {

    public static final int LIMIT = 10;

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl(){
        this.employees= new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int department, double salary) {

        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName,  department, salary);
        if (employees.containsKey(employee.getFullName())){
            throw new EmployeeAlreadyAddedException();
        }
        if (employees.size() < LIMIT){
            employees.put(employee.getFullName(), employee);
            return employee;
        }
        throw new EmployeeStorageIsFullException();
    }


    @Override
    public Employee remove(String firstName, String lastName, int department, double salary) {

        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName,  department, salary);
        if(employees.containsKey(employee.getFullName())){
            return employees.remove(employee.getFullName());
            }
            throw new EmployeeNotFoundException();
        }

    @Override
    public Employee find(String firstName, String lastName, int department, double salary) {

        if(!validateInput(firstName, lastName)){
            throw new InvalidInputException();
        }
        Employee employee = new Employee(firstName, lastName,  department, salary);
        if(employees.containsKey(employee.getFullName())){
            employees.remove(employee);
            return employees.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return Collections.unmodifiableCollection(employees.values());
    }

    @Override
    public boolean getAll() {
        return false;
    }


    private boolean validateInput(String firstName, String lastName){
        return StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName);
    }
}