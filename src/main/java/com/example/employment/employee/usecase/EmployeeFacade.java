package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.adapter.repository.EmployeeRepository;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.validator.EmployeeValidator;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class EmployeeFacade {

    private final InsertEmployee insertEmployee;
    private final UpdateEmployee updateEmployee;
    private final FindEmployee findEmployee;
    private final DeleteEmployee deleteEmployee;

    public EmployeeFacade(EmployeeRepository repository) {
        this.insertEmployee = new InsertEmployee(repository, new EmployeeValidator());
        this.updateEmployee = new UpdateEmployee(repository);
        this.findEmployee = new FindEmployee(repository);
        this.deleteEmployee = new DeleteEmployee(repository);
    }

    public Employee insertEmployee(EmployeeDto employeeDto) {
        return insertEmployee.insert(employeeDto);
    }

    @Transactional
    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        return updateEmployee.update(id, employeeDto);
    }

    public Employee findEmployee(Integer id) {
        return findEmployee.findOne(id);
    }

    public List<Employee> findEmployees(String name, String surname, Integer grade, Integer salary) {
        return findEmployee.list(name, surname, grade, salary);
    }

    public void deleteEmployee(Integer id) {
        deleteEmployee.delete(id);
    }

}
