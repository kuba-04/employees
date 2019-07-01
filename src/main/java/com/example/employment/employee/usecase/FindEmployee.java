package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.repository.EmployeeRepository;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.exception.EmployeeNotExistsException;
import com.example.employment.employee.usecase.query.EmployeeSpecification;
import com.example.employment.employee.usecase.query.SearchCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
class FindEmployee {

    private final EmployeeRepository repository;

    @Transactional(readOnly = true)
    public Employee findOne(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotExistsException(id));
    }

    @Transactional(readOnly = true)
    public List<Employee> list(String name, String surname, Integer grade, Integer salary) {
        EmployeeSpecification specForName = new EmployeeSpecification(new SearchCriteria("name", name));
        EmployeeSpecification specForSurname = new EmployeeSpecification(new SearchCriteria("surname", surname));
        EmployeeSpecification specForGrade = new EmployeeSpecification(new SearchCriteria("grade", grade));
        EmployeeSpecification specForSalary = new EmployeeSpecification(new SearchCriteria("salary", salary));
        return repository.findAll(Specification
                .where(specForName)
                .and(specForSurname)
                .and(specForGrade)
                .and(specForSalary));
    }
}
