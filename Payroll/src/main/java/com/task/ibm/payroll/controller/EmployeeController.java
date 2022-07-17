package com.task.ibm.payroll.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.task.ibm.payroll.dto.EmpRequest;
import com.task.ibm.payroll.entity.Employee;
import com.task.ibm.payroll.exception.EmployeeNotFoundException;
import com.task.ibm.payroll.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	   @Autowired
	    private EmployeeService employeeService;
	   
	    @GetMapping("/{id}")
	    public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
	        return ResponseEntity.ok(employeeService.getEmployee(id));
	    }
	    @GetMapping
	    public ResponseEntity<List<Employee>> getAllEmployee() {
	        return ResponseEntity.ok(employeeService.getAllEmployees());
	    }
	    @PostMapping
	    public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmpRequest employeeRequest) {
	        return new ResponseEntity<>(employeeService.saveEmployee(employeeRequest), HttpStatus.CREATED);
	    }
	    @PutMapping
	    public ResponseEntity<List<Employee>> updateEmployeeByName(@RequestBody @Valid EmpRequest employeeRequest) throws EmployeeNotFoundException {
	        return new ResponseEntity<>(employeeService.updateEmployeeByName(employeeRequest), HttpStatus.OK);
	    }
}
