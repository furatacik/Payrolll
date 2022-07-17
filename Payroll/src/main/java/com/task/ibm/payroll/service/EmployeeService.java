package com.task.ibm.payroll.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.task.ibm.payroll.dto.EmpRequest;
import com.task.ibm.payroll.entity.Employee;
import com.task.ibm.payroll.exception.EmployeeNotFoundException;
import com.task.ibm.payroll.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	   private EmployeeRepository employeeRepository;

	    @Autowired
	    public EmployeeService(EmployeeRepository employeeRepository){
	        this.employeeRepository = employeeRepository;
	    }

	    public Employee saveEmployee(EmpRequest empRequest) {
	        Employee employee = Employee.of(0, empRequest.getName(), empRequest.getRole());
	        return employeeRepository.save(employee);
	    }

	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    public Optional<Employee> getEmployee(int id) throws EmployeeNotFoundException {
	        Optional<Employee> employee = employeeRepository.findById(id);
	        if (employee.isPresent()) {
	            return employee;
	        } else {
	            throw new EmployeeNotFoundException("Employee not found with id= " + id);
	        }
	    }

	    public List<Employee> updateEmployeeByName(EmpRequest empRequest) throws EmployeeNotFoundException {
	        List<Employee> employees = employeeRepository.findByName(empRequest.getName());
	        List<Employee> updatedEmployees = new ArrayList<>();
	        if (employees.size() != 0) {
	            for (Employee employee : employees) {
	                Employee updatedemployee = Employee.of(employee.getId(), employee.getName(), empRequest.getRole());
	                employeeRepository.save(updatedemployee);
	                updatedEmployees.add(updatedemployee);
	            }
	        } else {
	            throw new EmployeeNotFoundException("No Employee not found with name= " + empRequest.getName());
	        }
	        return updatedEmployees;
	    }
}
