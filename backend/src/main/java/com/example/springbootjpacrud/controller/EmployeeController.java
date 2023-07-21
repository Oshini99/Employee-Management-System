package com.example.springbootjpacrud.controller;

import com.example.springbootjpacrud.exception.ResourceNotFoundException;
import com.example.springbootjpacrud.model.Employee;
import com.example.springbootjpacrud.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
            throws ResourceNotFoundException {
        try{
            Employee employee = employeeService.getEmployeeById((employeeId));
            return ResponseEntity.ok().body(employee);

        }catch (Exception ex){
            log.info(String.valueOf(new ResourceNotFoundException("Employee not found for this id :: " + employeeId)));
            return (ResponseEntity<Employee>) ResponseEntity.notFound();
        }
    }

    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.setEmployee(employee);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
                                                   @Valid @RequestBody Employee employeeDetails)  {
        try{
            Employee employee = employeeService.updateEmployee(employeeId,employeeDetails);
            return ResponseEntity.ok().body(employee);

        }catch (Exception ex){
            log.info(String.valueOf(new ResourceNotFoundException("Employee not found for this id :: " + employeeId)));
            return (ResponseEntity<Employee>) ResponseEntity.notFound();
        }

    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
        employeeService.deleteEmployee(employeeId);

    }
}
