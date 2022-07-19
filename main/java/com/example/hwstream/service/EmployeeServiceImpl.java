package com.example.hwstream.service;

import com.example.hwstream.Employee;
import com.example.hwstream.EmployeeAlreadyAddedException;
import com.example.hwstream.EmployeeNotFoundException;
import com.example.hwstream.EmployeeStorageIsFullException;
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
        Employee employee = new Employee(firstName, lastName,  department, salary);
        if(employees.containsKey(employee.getFullName())){
            return employees.remove(employee.getFullName());
            }
            throw new EmployeeNotFoundException();
        }

    @Override
    public Employee find(String firstName, String lastName, int department, double salary) {
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
}