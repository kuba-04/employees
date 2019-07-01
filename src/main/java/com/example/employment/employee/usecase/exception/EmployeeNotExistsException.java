package com.example.employment.employee.usecase.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotExistsException extends RuntimeException {
    public EmployeeNotExistsException(Integer id) {
        super("Employee with id = " + id + " doesn't exist!");
    }
}
