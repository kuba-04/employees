package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.adapter.repository.EmployeeRepository;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.validator.EmployeeValidator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class InsertEmployee {

    private final EmployeeRepository repository;
    private final EmployeeValidator validator;

    Employee insert(EmployeeDto employeeDto) {
        validator.validateNewEmployee(employeeDto);
        return repository.save(employeeDto.toEmployee());
    }
}
