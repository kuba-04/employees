package com.example.employment.employee.usecase;

import com.example.employment.employee.adapter.controller.model.EmployeeDto;
import com.example.employment.employee.domain.Employee;
import com.example.employment.employee.usecase.exception.EmployeeNotExistsException;
import com.example.employment.employee.usecase.exception.EmployeeValidationException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeFacadeTestSuite {

    @Autowired
    private EmployeeFacade facade;

    @Test
    public void should_insert_employee() {
        // given: employee named John
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);

        // when: John is added
        Integer johnId = facade.insertEmployee(john).getId();

        // then: John exists in db
        assertEquals("John", facade.findEmployee(johnId).getName());
    }

    @Test(expected = EmployeeValidationException.class)
    public void should_not_insert_employee_with_missing_data() {
        // given: employee named John with null values
        EmployeeDto employeeDto = new EmployeeDto(null, null, null, null);

        // when: user is attempting to add John
        facade.insertEmployee(employeeDto);
    }

    @Test
    public void should_update_employee_data() {
        // given: employee named John exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        Integer johnId = facade.insertEmployee(john).getId();

        // when: new values are provided for that employee
        EmployeeDto johny = new EmployeeDto("Johny", "Doer", 11, 2_000_000);
        facade.updateEmployee(johnId, johny);

        // then: returned employee values are updated
        Employee employee = facade.findEmployee(johnId);
        assertEquals("Johny", employee.getName());
        assertEquals("Doer", employee.getSurname());
        assertEquals(11, employee.getGrade(), 0);
        assertEquals(2_000_000, employee.getSalary(), 0);
    }

    @Test
    public void should_update_only_specified_employee_data() {
        // given: employee named John exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        Integer johnId = facade.insertEmployee(john).getId();

        // when: employee name is updated
        EmployeeDto johny = new EmployeeDto("Johny", null, null, null);
        facade.updateEmployee(johnId, johny);

        // then: only name is changed
        Employee employee = facade.findEmployee(johnId);
        assertEquals("Johny", employee.getName());
        assertEquals("Doe", employee.getSurname());
        assertEquals(10, employee.getGrade(), 0);
        assertEquals(1_000_000, employee.getSalary(), 0);
    }

    @Test
    public void should_find_one_employee() {
        // given: employee named John exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        Integer johnId = facade.insertEmployee(john).getId();

        // when: user requests an employee with John's id
        Employee employee = facade.findEmployee(johnId);

        // then: returned employee name is John
        assertEquals("John", employee.getName());
    }

    @Test(expected = EmployeeNotExistsException.class)
    public void should_throw_exception_on_employee_not_found() {
        // when: user requests an employee with id 1 while there are no records in db
        facade.findEmployee(1);
    }

    @Test
    public void should_return_all_employees_with_empty_query() {
        // given: 3 employees exist in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        facade.insertEmployee(john);

        EmployeeDto jack = new EmployeeDto("Jack", "Doe", 2, 1_000_000);
        facade.insertEmployee(jack);

        EmployeeDto sofie = new EmployeeDto("Sofie", "Rose", 9, 1_500_000);
        facade.insertEmployee(sofie);

        // when: user requests for employees without any filters
        List<Employee> employees = facade.findEmployees("", "", null, null);

        // then: all 3 employees are returned
        assertEquals(3, employees.size());
    }

    @Test
    public void should_return_empty_list_for_not_matching_query() {
        // given: employee named John exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        facade.insertEmployee(john);

        // and: employee named Jack exists in db
        EmployeeDto jack = new EmployeeDto("Jack", "Doe", 2, 1_000_000);
        facade.insertEmployee(jack);

        // and: employee named Sofie exists in db
        EmployeeDto sofie = new EmployeeDto("Sofie", "Rose", 9, 1_500_000);
        facade.insertEmployee(sofie);

        // when: user requests all employees with name Andy
        List<Employee> employees = facade.findEmployees("Andy", "", null, null);

        // then: no records are returned
        assertTrue(employees.isEmpty());
    }

    @Test
    public void should_return_2_employees_with_the_same_surname() {
        // given: employee John Doe exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        facade.insertEmployee(john);

        // and: employee named Jack Doe exists in db
        EmployeeDto jack = new EmployeeDto("Jack", "Doe", 2, 1_000_000);
        facade.insertEmployee(jack);

        // and: employee named Sofie exists in db
        EmployeeDto sofie = new EmployeeDto("Sofie", "Rose", 9, 1_500_000);
        facade.insertEmployee(sofie);

        // when: user requests all employees with surname Doe
        List<Employee> employees = facade.findEmployees("", "Doe", null, null);

        // then: 2 records are returned
        assertEquals(2, employees.size());
    }

    @Test
    public void should_delete_employee() {
        // given: employee named John exists in db
        EmployeeDto john = new EmployeeDto("John", "Doe", 10, 1_000_000);
        Integer johnId = facade.insertEmployee(john).getId();

        // when: John is deleted from db
        facade.deleteEmployee(johnId);

        // then: db is empty
        List<Employee> employees = facade.findEmployees("", "", null, null);
        assertTrue(employees.isEmpty());
    }

    @Test(expected = EmployeeNotExistsException.class)
    public void should_throw_exception_on_deleting_missing_employee() {
        // when: user requests an employee with id 1
        facade.deleteEmployee(1);
    }

    @After
    public void cleanup() {
        facade.findEmployees(null, null, null, null)
                .stream()
                .map(Employee::getId)
                .forEach(id -> facade.deleteEmployee(id));
    }
}
