package com.example.dao;

import com.example.model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/hr_manage?useSSL=false";
    private static String jdbcUsername = "root";
    private static String jdbcPassword = "";

    public EmployeeDao() {}

    protected static Connection getConnection() throws SQLException {

        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (Exception e) {
            throw new SQLException(e.getMessage());
        }

        return connection;
    }

    public void insertEmployee(Employee employee) throws SQLException {
        String sql = "INSERT INTO employee (employee_id , employee_name, birthday, phone_number, email) VALUES (?, ?, ?, ?, ?)";
        Connection connection = getConnection();
        java.sql.Date sqlDate = new java.sql.Date(employee.getBirthday().getTime());
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getEmployee_id());
        statement.setString(2, employee.getEmployee_name());
        statement.setDate(3, sqlDate);
        statement.setString(3, employee.getPhone_number());
        statement.setString(3, employee.getEmail());

        statement.executeUpdate();
    }

    public List<Employee> listAllEmployee() throws SQLException {
        List<Employee> listEmployee = new ArrayList<>();
        String sql = "SELECT * FROM employee";

        Connection connection = getConnection();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String employee_id = resultSet.getString("employee_id");
            String employee_name = resultSet.getString("employee_name");
            Date birthday = resultSet.getDate("birthday");
            String phone_number = resultSet.getString("phone_number");
            String email = resultSet.getString("email");

            Employee employee = new Employee(employee_id, employee_name, birthday, phone_number, email);
            listEmployee.add(employee);
        }

        resultSet.close();
        statement.close();

        return listEmployee;
    }

    public boolean deleteUser(Employee employee) throws SQLException {
        String sql = "DELETE FROM employee where employee_id = ?";

        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, employee.getEmployee_id());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        return rowDeleted;
    }

    public boolean updateUser(Employee employee) throws SQLException {
        String sql = "UPDATE employee SET employee_name = ?, birthday = ?, phone_number = ?, email = ? WHERE id = ?";

        Connection connection = getConnection();

        java.sql.Date sqlDate = new java.sql.Date(employee.getBirthday().getTime());

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(3, employee.getEmployee_name());
        statement.setDate(3, sqlDate);
        statement.setString(3, employee.getPhone_number());
        statement.setString(4, employee.getEmail());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        return rowUpdated;
    }



    public Employee getEmployee(String id) throws SQLException {
        Employee employee = null;
        String sql = "SELECT * FROM employee WHERE employee_id = ?";

        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String employee_name = resultSet.getString("employee_name");
            Date birthday = resultSet.getDate("birthday");
            String phone_number = resultSet.getString("phone_number");
            String email = resultSet.getString("email");

            employee = new Employee( employee_name, birthday, phone_number, email);
        }

        resultSet.close();
        statement.close();

        return employee;
    }

    public void updateEmployee(Employee employee) {

    }
}
