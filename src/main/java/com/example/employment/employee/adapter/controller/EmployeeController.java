package com.example.employment.employee.adapter.controller;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.EmployeeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/employee")
class EmployeeController {

    private final EmployeeFacade facade;

    @PostMapping
    Employee insert(@RequestBody EmployeeDto employeeDto) {
        return facade.insertEmployee(employeeDto);
    }

    @PutMapping("/{id}")
    Employee update(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        return facade.updateEmployee(id, employeeDto);
    }

    @GetMapping("/{id}")
    Employee getOne(@PathVariable Integer id) {
        return facade.findEmployee(id);
    }

    @GetMapping("/list")
    List<Employee> list(
            @RequestParam(required = false, defaultValue = "") String name,
            @RequestParam(required = false, defaultValue = "") String surname,
            @RequestParam(required = false, defaultValue = "") Integer grade,
            @RequestParam(required = false, defaultValue = "") Integer salary) {
        return facade.findEmployees(name, surname, grade, salary);
    }

    @DeleteMapping
    void delete(@RequestParam Integer id) {
        facade.deleteEmployee(id);
    }

}
