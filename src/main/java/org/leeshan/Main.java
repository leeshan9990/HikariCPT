package org.leeshan;

import com.zaxxer.hikari.HikariDataSource;
import org.leeshan.config.DatabaseConfig;
import org.leeshan.dao.EmployeeDAOImpl;
import org.leeshan.model.Employee;
import org.leeshan.service.EmployeeService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    private static final EmployeeService employeeService =
            new EmployeeService();
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("HikariCP Testing and welcome!");


            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.


//            try (Connection con =
//                         DatabaseConfig.getDataSource().getConnection()) {
//
//                if (con.isValid(5)) {
//                    System.out.println("Employee Management DB Connected!");
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//
//            } finally {
//                DatabaseConfig.closeDataSource();
//            }

        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
//
//        Employee employee = new Employee(
//                "Arjun Mehta",
//                "arjun@gmail.com",
//                "Engineering",
//                new BigDecimal("85000.00"),
//                LocalDate.of(2026, 10, 29)
//        );
//
//        try {
//            int generatedId = employeeDAO.addEmployee(employee);
//
//            if (generatedId != -1) {
//                System.out.println("Employee added successfully!");
//                System.out.println("Generated Employee ID: " + generatedId);
//            } else {
//                System.out.println("Employee insertion failed.");
//            }
//
//        } finally {
//            DatabaseConfig.closeDataSource();
//        }

//        try {
//            Optional<Employee> result = employeeDAO.getEmployeeById(2);
//
//            if (result.isPresent()) {
//                System.out.println("Employee Found!");
//                System.out.println(result.get());
//            } else {
//                System.out.println("Employee not found!");
//            }
//
//        } finally {
//            DatabaseConfig.closeDataSource();
//        }

        // get all EMployees

//        try{
//            List<Employee> employees =employeeDAO.getAllEmployees();
//            if(employees.isEmpty()){
//                System.out.print("No employees found");
//            }else {
//                System.out.println("All Employees");
//                for(Employee employee: employees){
//                    System.out.println(employee);
//                }
//            }
//        }
//        finally {
//            DatabaseConfig.closeDataSource();
//        }

//        Employee employee = new Employee(
//                2,
//                "Aman Verma",
//                "aman@gmail.com",
//                "HR",
//                new BigDecimal("70000.00"),
//                LocalDate.of(2025, 2, 15)
//        );
//
//        boolean updated = employeeDAO.updateEmployee(employee);
//
//        System.out.println(
//                updated ? "Employee updated successfully!" : "Employee not found!"
//        );
//        boolean deleted = employeeDAO.deleteEmployee(5);
//
//        if (deleted) {
//            System.out.println("Employee deleted successfully!");
//        } else {
//            System.out.println("Employee not found!");
//        }
//
//
//        try {
//
//            List<Employee> employees = employeeDAO.searchEmployees("IT");
//
//            if (employees.isEmpty()) {
//                System.out.println("No employees found!");
//            } else {
//                System.out.println("Search Results:");
//
//                for (Employee employee1 : employees) {
//                    System.out.println(employee1);
//                }
//            }
//
//        } finally {
//            DatabaseConfig.closeDataSource();
//        }

        try {
            boolean running = true;

            while (running) {

                displayMenu();

                System.out.print("Enter your choice: ");

                int choice = readInt();

                try {
                    switch (choice) {

                        case 1 -> addEmployee();

                        case 2 -> findEmployee();

                        case 3 -> viewAllEmployees();

                        case 4 -> updateEmployee();

                        case 5 -> deleteEmployee();

                        case 6 -> searchEmployees();

                        case 7 -> {
                            running = false;
                            System.out.println("Thank you for using Employee Management System!");
                        }

                        default -> System.out.println("Invalid choice! Try again.");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println("Validation Error: " + e.getMessage());

                } catch (RuntimeException e) {
                    System.out.println("Operation failed: " + e.getMessage());
                }
            }

        } finally {
            scanner.close();
            DatabaseConfig.closeDataSource();
        }
    }

    private static void displayMenu() {

        System.out.println("\n====================================");
        System.out.println("       EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("====================================");

        System.out.println("1. Add Employee");
        System.out.println("2. Find Employee By ID");
        System.out.println("3. View All Employees");
        System.out.println("4. Update Employee");
        System.out.println("5. Delete Employee");
        System.out.println("6. Search Employee");
        System.out.println("7. Exit");

        System.out.println("====================================");
    }

    private static int readInt() {

        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static BigDecimal readSalary() {

        while (true) {
            try {
                return new BigDecimal(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid salary: ");
            }
        }
    }

    private static LocalDate readDate() {

        while (true) {
            try {
                System.out.print("Enter joining date (YYYY-MM-DD): ");
                return LocalDate.parse(scanner.nextLine().trim());

            } catch (Exception e) {
                System.out.println("Invalid date format. Try again.");
            }
        }
    }

    private static void addEmployee() {

        System.out.println("\n--- Add Employee ---");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        BigDecimal salary = readSalary();

        LocalDate joiningDate = readDate();

        Employee employee = new Employee(
                name, email, department, salary, joiningDate
        );

        int id = employeeService.addEmployee(employee);

        System.out.println("Employee added successfully!");
        System.out.println("Generated Employee ID: " + id);
    }

    private static void findEmployee() {

        System.out.println("\n--- Find Employee ---");

        System.out.print("Enter employee ID: ");
        int id = readInt();

        Optional<Employee> employee =
                employeeService.getEmployeeById(id);

        if (employee.isPresent()) {
            System.out.println(employee.get());
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void viewAllEmployees() {

        System.out.println("\n--- All Employees ---");

        List<Employee> employees =
                employeeService.getAllEmployees();

        if (employees.isEmpty()) {
            System.out.println("No employees found!");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    private static void updateEmployee() {

        System.out.println("\n--- Update Employee ---");

        System.out.print("Enter employee ID: ");
        int id = readInt();

        Optional<Employee> existingEmployee =
                employeeService.getEmployeeById(id);

        if (existingEmployee.isEmpty()) {
            System.out.println("Employee not found!");
            return;
        }

        Employee old = existingEmployee.get();

        System.out.print("Enter new name: ");
        String name = scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();

        System.out.print("Enter new department: ");
        String department = scanner.nextLine();

        System.out.print("Enter new salary: ");
        BigDecimal salary = readSalary();

        LocalDate joiningDate = readDate();

        Employee updatedEmployee = new Employee(
                id, name, email, department, salary, joiningDate
        );

        boolean updated =
                employeeService.updateEmployee(updatedEmployee);

        if (updated) {
            System.out.println("Employee updated successfully!");
        } else {
            System.out.println("No employee data was changed.");
        }
    }

    private static void deleteEmployee() {

        System.out.println("\n--- Delete Employee ---");

        System.out.print("Enter employee ID: ");
        int id = readInt();

        boolean deleted = employeeService.deleteEmployee(id);

        if (deleted) {
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }

    private static void searchEmployees() {

        System.out.println("\n--- Search Employees ---");

        System.out.print("Enter name or department: ");
        String keyword = scanner.nextLine();

        List<Employee> employees =
                employeeService.searchEmployees(keyword);

        if (employees.isEmpty()) {
            System.out.println("No matching employees found!");
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }
}