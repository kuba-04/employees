package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.adapter.repository.EmployeeRepository;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.exception.EmployeeNotExistsException;
import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
class UpdateEmployee {

    private final EmployeeRepository repository;

    @Transactional
    public Employee update(Integer id, EmployeeDto employeeDto) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotExistsException(id));
        if (!Strings.isNullOrEmpty(employeeDto.getName())) {
            employee.setName(employeeDto.getName());
        }
        if (!Strings.isNullOrEmpty(employeeDto.getSurname())) {
            employee.setSurname(employeeDto.getSurname());
        }
        if (employeeDto.getGrade() != null) {
            employee.setGrade(employeeDto.getGrade());
        }
        if (employeeDto.getSalary() != null) {
            employee.setSalary(employeeDto.getSalary());
        }
        return employee;
    }
}
