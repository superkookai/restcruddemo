package com.superkookai.springboot.restcruddemo.rest;

import com.superkookai.springboot.restcruddemo.dao.EmployeeDAO;
import com.superkookai.springboot.restcruddemo.entity.Employee;
import com.superkookai.springboot.restcruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;

    //inject employee service
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    //expose '/employees' to return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //expose '/employees/{employeeId}' to return employee by id
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee foundEmployee = employeeService.findById(employeeId);
        if (foundEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return foundEmployee;
    }

    //expose '/employees' to add (POST) new Employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //in case client add id to json request body >> set id = 0 >> to make save not update
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //expose '/employees' to update (PUT) a Employee
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //expose '/employees/{employeeId}' to delete employee by id
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee foundEmployee = employeeService.findById(employeeId);
        if (foundEmployee == null){
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}
