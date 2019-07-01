package com.example.employment.employee.usecase.query;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SearchCriteria {

    private String key;
    private Object value;
}
