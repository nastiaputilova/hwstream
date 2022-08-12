package com.example.hwstream;

import com.example.hwstream.service.DepartmentService;
import com.example.hwstream.service.EmployeeService;
import org.apache.el.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.params.provider.Arguments.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {

    @Mock

    private EmployeeService employeeService;

    @InjectMocks

    private DepartmentService departmentService;

    @BeforeEach

    public void BeforeEach(){
        java.util.List<Employee> employees = List.of(
                new Employee ("Ivan", "Ivanov", 1, 1000),
                new Employee ("Stepan", "Stepanov", 1, 2000),
                new Employee ("Sergey", "Sergeev", 2, 3000),
                new Employee ("Diana", "Dianova", 2, 2000),
                new Employee ("Alena", "Sidorova", 2, 4000)
        );
        when(employeeService.getAll()).thenReturn(employees);

    }

    @ParameterizedTest
    @MethodSource("EmployeeWithMaxSalaryParams")

    public maxSalaryTest(int department, Employee expected){
        assertThat(departmentService.findEmployeeWithMaxSalaryFromDepartment(department)).isEqualTo(expected);

}

@Test

    public maxSalaryTest2(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMaxSalaryFromDepartment(3));
    }

    @ParameterizedTest
    @MethodSource("EmployeeWithMinSalaryParams")

    public minSalaryTest(int department, Employee expected){
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).isEqualTo(expected);

    }

    @Test

    public minSalaryTest2(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalaryFromDepartment(3));
    }


    @ParameterizedTest
    @MethodSource("EmployeesFromDepartmentParams")

    public findEmployeesFromDepartmentTest(int department, java.util.List<Employee> expected){
        assertThat(departmentService.findEmployeeWithMinSalaryFromDepartment(department)).containsExactlyElementsOf(expected);

    }

    @Test

    public findEmployeesFromDepartmentTest2(){
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalaryFromDepartment(3));
    }

    @Test

    public findEmployeesTest(){
        assertThat(departmentService.findEmployees()).containsAllEntriesOf(
                Map.of(
                        1, List.of(new Employee("Stepan", "Stepanov", 1, 1000),(new Employee("Ivan", "Ivanov", 1, 1000)),
                        2, List.of(new Employee ("Sergey", "Sergeev", 2, 3000),
                                        new Employee ("Diana", "Dianova", 2, 2000),
                                        new Employee ("Alena", "Sidorova", 2, 4000))
                )
        );
    }

    public static Stream<Arguments> EmployeeWithMaxSalaryParams(){

        return Stream.of(
                of(1, new Employee("Sergei", "Sergeev", 2, 3000)),
                of(2, new Employee ("Alena", "Sidorova", 2, 4000))
        );


    }
    public static Stream<Arguments> EmployeeWithMinSalaryParams(){

        return Stream.of(
                of(1,new Employee ("Ivan", "Ivanov", 1, 1000)),
                of(2, new Employee ("Diana", "Dianova", 2, 2000))
        );

    }
    public static Stream<Arguments> EmployeesFromDepartmentParams(){

        return Stream.of(
                of(1, List.of(new Employee("Stepan", "Stepanov", 1, 1000),new Employee ("Ivan", "Ivanov", 1, 1000)),
                of(2, List.of(new Employee ("Sergey", "Sergeev", 2, 3000),
                        new Employee ("Diana", "Dianova", 2, 2000),
                        new Employee ("Alena", "Sidorova", 2, 4000))
        );

    }


}
