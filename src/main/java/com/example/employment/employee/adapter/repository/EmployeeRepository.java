package com.example.employment.employee.adapter.repository;

import com.example.employment.employee.domain.Employee;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee, Integer> {

    Employee save(Employee employee);

    Optional<Employee> findById(Integer id);

    void deleteById(Integer id);

    List<Employee> findAll(Specification<Employee> specification);

}
