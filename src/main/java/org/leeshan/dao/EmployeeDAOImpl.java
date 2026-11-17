package org.leeshan.dao;

import org.leeshan.config.DatabaseConfig;
import org.leeshan.model.Employee;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeDAOImpl implements EmployeeDAO{

    @Override
    public int addEmployee(Employee employee) {

        String sql = """
                INSERT INTO employee
                (name, email, department, salary, joining_date)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = con.prepareStatement(
                     sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setBigDecimal(4, employee.getSalary());
            pstmt.setDate(5,
                    java.sql.Date.valueOf(employee.getJoiningDate()));
            

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                return -1;
            }

            try (var generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add employee", e);
        }

        return -1;
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {

        String sql = "SELECT * FROM employee WHERE id = ?";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {

                    Employee employee = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("department"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("joining_date").toLocalDate()
                    );

                    return Optional.of(employee);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to find employee", e);
        }

        return Optional.empty();

    }
    @Override
    public List<Employee> getAllEmployees(){
        List<Employee> employees=new ArrayList<>();
        String sql="Select * from employee";

        try(Connection con =DatabaseConfig.getDataSource().getConnection();
        PreparedStatement ps =con.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();
        ) {
            while(rs.next()){
                Employee employee=new Employee
                        (rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                                rs.getBigDecimal("salary"),
                                rs.getDate("joining_date").toLocalDate()
                        );
                employees.add(employee);


            }

        }
        catch(SQLException e){
            throw new RuntimeException("unable to fetch employees",e);
        }
return employees;
    }

@Override
    public boolean updateEmployee(Employee employee) {

        String sql = """
            UPDATE employee
            SET name = ?,
                email = ?,
                department = ?,
                salary = ?,
                joining_date = ?
            WHERE id = ?
            """;

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, employee.getName());
            pstmt.setString(2, employee.getEmail());
            pstmt.setString(3, employee.getDepartment());
            pstmt.setBigDecimal(4, employee.getSalary());
            pstmt.setDate(5,
                    java.sql.Date.valueOf(employee.getJoiningDate()));
            pstmt.setInt(6, employee.getId());

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update employee", e);
        }

    }

    @Override
    public boolean deleteEmployee(int id) {

        String sql = "DELETE FROM employee WHERE id = ?";

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            int rowsAffected = pstmt.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete employee", e);
        }
    }
    @Override
    public List<Employee> searchEmployees(String keyword) {

        List<Employee> employees = new ArrayList<>();

        String sql = """
            SELECT * FROM employee
            WHERE name LIKE ? OR department LIKE ?
            """;

        try (Connection con = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {

                    Employee employee = new Employee(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("department"),
                            rs.getBigDecimal("salary"),
                            rs.getDate("joining_date").toLocalDate()
                    );

                    employees.add(employee);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to search employees", e);
        }

        return employees;
    }
}