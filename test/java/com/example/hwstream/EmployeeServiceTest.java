package com.example.hwstream;

import com.example.hwstream.service.EmployeeService;
import com.example.hwstream.service.ValidatorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.awt.*;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EmployeeServiceTest {


    private final EmployeeService employeeService = new employeeService(new ValidatorService()) {


    @ParameterizedTest
    @MethodSource("paramsForAdd")

    public void addTest(String firstName,
                                String lastName,
                                int department,
                                double salary){
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add(firstName, lastName, department, salary));

    }

    @ParameterizedTest
    @MethodSource("paramsForAdd")

    public void addTest2(String firstName,
                                String lastName,
                                int department,
                                double salary){

        java.util.List<Employee> employees = generateEmployees(10);
        employees.forEach(employee ->
                assertThat(employeeService.add(employee.getFirstName(),employee.getLastName(),employee.getDepartment(), employee.getSalary())).isEqualTo(employee)
        );
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add(firstName, lastName, department, salary));
        
    }

    @ParameterizedTest
    @MethodSource("paramsForRemove")

    public void removeTest(String firstName,
                           String lastName,
                           int department,
                           double salary){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test name", "test last name", 0, 0));
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("test name", "test last name", 0, 0));

    }
    @ParameterizedTest
    @MethodSource("paramsForRemove")

    public void removeTest2(String firstName,
                               String lastName,
                               int department,
                               double salary){
        Employee expected = new Employee(firstName, lastName, department, salary);
        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);

        assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
        assertThat(employeeService.getAll()).isEmpty();

    }

        @ParameterizedTest
        @MethodSource("paramsForFind")

        public void findTest(String firstName,
                               String lastName,
                               int department,
                               double salary){
            assertThatExceptionOfType(EmployeeNotFoundException.class)
                    .isThrownBy(() -> employeeService.find("test name", "test last name", 0, 0));
            Employee expected = new Employee(firstName, lastName, department, salary);
            assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);
            assertThatExceptionOfType(EmployeeNotFoundException.class)
                    .isThrownBy(() -> employeeService.find("test name", "test last name", 0, 0));

        }
        @ParameterizedTest
        @MethodSource("paramsForFind")

        public void findTest2(String firstName,
                                String lastName,
                                int department,
                                double salary){
            Employee expected = new Employee(firstName, lastName, department, salary);
            assertThat(employeeService.add(firstName, lastName, department, salary)).isEqualTo(expected);

            assertThat(employeeService.find(firstName, lastName, department, salary)).isEqualTo(expected);
            assertThat(employeeService.getAll()).hasSize(1);

        }


    private java.util.List<Employee> generateEmployees(int size){
        return Stream.iterate(1, i -> i + 1)
                .limit(size)
                .map(i -> new Employee("First Name" + (char) ((int) 'a' + i), "Last Name" + (char) ((int) 'a' + i), i, 1000 + i))
                .collect(Collectors.toList());
    }

    public static Stream<Arguments> paramsForAdd(){
        return Stream.of(
                Arguments.of("Olga", "Ivanova", 1, 2000),
                Arguments.of("Maria", "Petrova", 2, 3000)
        );

    }
        public static Stream<Arguments> paramsForRemove(){
            return Stream.of(
                    Arguments.of("Olga", "Ivanova", 1, 2000),
                    Arguments.of("Maria", "Petrova", 2, 3000)
            );
}

    }
