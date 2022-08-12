package com.example.hwstream.service;

import com.example.hwstream.Employee;
import com.example.hwstream.EmployeeNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    public final EmployeeServiceImpl employeeService;

    public DepartmentService(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    public Employee findEmployeeWithMaxSalaryFromDepartment(int department){
    return employeeService.findAll().stream()
            .filter(employee -> employee.getDepartment() == department)
            .max(Comparator.comparingDouble(Employee::getSalary))
            .orElseThrow(EmployeeNotFoundException::new);
    }

    public Employee findEmployeeWithMinSalaryFromDepartment(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public java.util.List<Employee> findEmployeesFromDepartment(int department){
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toList());
    }
    public Map< Integer, List<Employee>> findEmployees(){
        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
