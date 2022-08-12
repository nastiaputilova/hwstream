package com.example.hwstream.controller;

import com.example.hwstream.Employee;
import com.example.hwstream.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee findEmployeeWithMaxSalaryFromDepartment(@RequestParam("department") int department){
        return departmentService.findEmployeeWithMaxSalaryFromDepartment(department);

    }

    @GetMapping("/min-salary")
    public Employee findEmployeeWithMinSalaryFromDepartment(@RequestParam("department") int department){
        return departmentService.findEmployeeWithMinSalaryFromDepartment(department);
    }

    @GetMapping(value= "/all", params = "department")
    public java.util.List<Employee> findEmployeesFromDepartment(@RequestParam("department") int department){
        return departmentService.findEmployeesFromDepartment(department);
    }

    @GetMapping("/all")
    public Map< Integer, List<Employee>> findEmployees(@RequestParam("department") int department){
        return departmentService.findEmployees();
    }
}