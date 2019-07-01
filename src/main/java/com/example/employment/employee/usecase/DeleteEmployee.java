package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.repository.EmployeeRepository;
import com.example.employment.employee.usecase.exception.EmployeeNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;

@RequiredArgsConstructor
class DeleteEmployee {

    private final EmployeeRepository repository;

    void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EmployeeNotExistsException(id);
        }
    }
}
