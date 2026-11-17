package org.leeshan.dao;

import org.leeshan.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeDAO {

    // Add a new employee
    int addEmployee(Employee employee);

    // Find employee using ID
    Optional<Employee> getEmployeeById(int id);
//
//    // Retrieve all employees
    List<Employee> getAllEmployees();
//
    // Update employee details
    boolean updateEmployee(Employee employee);
//
//    // Delete employee using ID
    boolean deleteEmployee(int id);
//
//    // Search employees by name or department
    List<Employee> searchEmployees(String keyword);
}