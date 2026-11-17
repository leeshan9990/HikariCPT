package org.leeshan.service;

import org.leeshan.dao.EmployeeDAO;
import org.leeshan.dao.EmployeeDAOImpl;
import org.leeshan.model.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeService() {
        this.employeeDAO = new EmployeeDAOImpl();
    }

    // Add employee
    public int addEmployee(Employee employee) {

        if (employee.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Salary must be greater than zero!"
            );
        }

        if (employee.getName() == null ||
                employee.getName().isBlank()) {
            throw new IllegalArgumentException(
                    "Employee name cannot be empty!"
            );
        }

        return employeeDAO.addEmployee(employee);
    }

    // Find employee by ID
    public Optional<Employee> getEmployeeById(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be positive!"
            );
        }

        return employeeDAO.getEmployeeById(id);
    }

    // Get all employees
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    // Update employee
    public boolean updateEmployee(Employee employee) {

        if (employee.getId() <= 0) {
            throw new IllegalArgumentException(
                    "Invalid employee ID!"
            );
        }

        if (employee.getSalary().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "Salary must be greater than zero!"
            );
        }

        return employeeDAO.updateEmployee(employee);
    }

    // Delete employee
    public boolean deleteEmployee(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException(
                    "Employee ID must be positive!"
            );
        }

        return employeeDAO.deleteEmployee(id);
    }

    // Search employees
    public List<Employee> searchEmployees(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException(
                    "Search keyword cannot be empty!"
            );
        }

        return employeeDAO.searchEmployees(keyword.trim());
    }
}