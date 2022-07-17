package com.task.ibm.Payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.client.RestTemplate;

import com.task.ibm.payroll.dto.EmpRequest;
import com.task.ibm.payroll.entity.Employee;
import com.task.ibm.payroll.repository.EmployeeRepository;
import com.task.ibm.payroll.service.EmployeeService;

@SpringBootTest
class PayrollApplicationTests {
    private String baseUrl = "http://localhost:8080/employee";

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository mockedRepository;

    public static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }
    @Test
    void testGetAllEmployeesService(){
        List<Employee> employeeList = new ArrayList<>();
        Employee employee = Employee.of(0,"Furat", "Engineer");
        employeeList.add(employee);
        employeeList.add(employee);

        when(mockedRepository.findAll()).thenReturn(employeeList);
        assertEquals(2,employeeService.getAllEmployees().size());
    }
    @Test
    void testAddEmployee() throws URISyntaxException {
        EmpRequest empRequest = EmpRequest.of("Furat", "Engineer");
        Employee response = restTemplate.postForObject(new URI(baseUrl), empRequest, Employee.class);
        assertEquals("Furat", response.getName());
        assertEquals("Engineer", response.getRole());
    }

    @Test
    void testUpdateByName() throws URISyntaxException {
        EmpRequest empRequest = EmpRequest.of("Furat", "Engineer");
        restTemplate.postForObject(new URI(baseUrl), empRequest, Employee.class);
        empRequest = EmpRequest.of("Furat", "Engineer");
        restTemplate.postForObject(new URI(baseUrl), empRequest, Employee.class);
        empRequest = EmpRequest.of("Furat", "Engineer");
        restTemplate.put(new URI(baseUrl), empRequest);
        List<Employee> employeeList = employeeRepository.findByName(empRequest.getName());

        for (Employee employee : employeeList) {
            assertEquals("Engineer", employee.getName());
        }
    }

}
