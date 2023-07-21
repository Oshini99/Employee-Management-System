package com.example.springbootjpacrud.service;

import com.example.springbootjpacrud.exception.ResourceNotFoundException;
import com.example.springbootjpacrud.model.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAllEmployees();



    Employee getEmployeeById(Long id);

    Employee setEmployee(Employee employee);

    Employee updateEmployee(Long id, Employee employeeDetails);

    Map<String, Boolean> deleteEmployee(Long employeeId) throws ResourceNotFoundException;
}
