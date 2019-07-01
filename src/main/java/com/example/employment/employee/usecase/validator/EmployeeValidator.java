package com.example.employment.employee.usecase.validator;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.usecase.exception.EmployeeValidationException;
import org.springframework.util.StringUtils;

public class EmployeeValidator {

    public void validateNewEmployee(EmployeeDto employeeDto) {
        if (employeeDto == null) throw new EmployeeValidationException("Employee should not be null");
        if (StringUtils.isEmpty(employeeDto.getName())) throw new EmployeeValidationException("Employee name should be specified");
        if (StringUtils.isEmpty(employeeDto.getSurname())) throw new EmployeeValidationException("Employee surname should be specified");
        if (StringUtils.isEmpty(employeeDto.getGrade())) throw new EmployeeValidationException("Employee grade should be specified");
        if (StringUtils.isEmpty(employeeDto.getSalary())) throw new EmployeeValidationException("Employee salary should be specified");
    }
}
