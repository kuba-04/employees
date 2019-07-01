package com.example.employment.employee.usecase.query;

import com.example.employment.employee.domain.Employee;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.*;

@AllArgsConstructor
public class EmployeeSpecification implements Specification<Employee> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(
            Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if (criteria.getValue() == null || criteria.getValue().equals("")) {
            return builder.like(root.get(criteria.getKey()).as(String.class), "%");
        } else {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
    }
}
