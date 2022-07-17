package com.task.ibm.payroll.repository;
import com.task.ibm.payroll.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
	 List<Employee> findByName(String name);
}
