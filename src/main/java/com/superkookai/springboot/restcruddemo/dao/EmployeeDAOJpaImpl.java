package com.superkookai.springboot.restcruddemo.dao;

import com.superkookai.springboot.restcruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO{
    private EntityManager entityManager;
    @Autowired
    public EmployeeDAOJpaImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create query
        TypedQuery<Employee> query = entityManager.createQuery("FROM Employee", Employee.class);

        //execute query and get result list
        List<Employee> employees = query.getResultList();

        //return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        Employee foundEmployee = entityManager.find(Employee.class,theId);
        return foundEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        Employee dbEmployee = entityManager.merge(theEmployee);
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        Employee foundEmployee = entityManager.find(Employee.class,theId);
        entityManager.remove(foundEmployee);
    }
}
