package com.example.controller;

import com.example.dao.EmployeeDao;
import com.example.model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/")
public class EmployeeServlet extends HttpServlet {
    private EmployeeDao employeeDao;

    public void init() { employeeDao = new EmployeeDao();}


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertEmployee(request, response);
                    break;
                case "/delete":
                    deleteEmployee(request, response);
                    break;
                case "/edit":
                    EditFormEmployee(request, response);
                    break;
                case "/update":
                    updateEmployee(request, response);
                    break;
                default:
                    listEmployee(request, response);
                    break;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException, ClassNotFoundException {
        List<Employee> listEmployee = employeeDao.listAllEmployee();
        request.setAttribute("listUser", listEmployee);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeList.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        dispatcher.forward(request, response);
    }

    private void EditFormEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException, ClassNotFoundException {
        String id = request.getParameter("id");
        Employee employee = employeeDao.getEmployee(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("UserForm.jsp");
        request.setAttribute("user", employee);
        dispatcher.forward(request, response);

    }

    private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException, ParseException {
        String employee_name = request.getParameter("employee_name");
        String dt=request.getParameter("bday").toString();
        java.util.Date birthday = new SimpleDateFormat("dd-mm-yyyy").parse(dt.toString());
        String phone_number = request.getParameter("phone_number");
        String email = request.getParameter("email");

        Employee newEmployee = new Employee(employee_name, (java.sql.Date) birthday, phone_number, email);
        employeeDao.insertEmployee(newEmployee);
        response.sendRedirect("list");
    }

    private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException, ParseException {
        String employee_name = request.getParameter("employee_name");
        String phone_number = request.getParameter("phone_number");
        String dt=request.getParameter("bday").toString();
        java.util.Date birthday = new SimpleDateFormat("dd-mm-yyyy").parse(dt.toString());
        String email = request.getParameter("email");

        Employee employee = new Employee(employee_name, (java.sql.Date) birthday, phone_number,email);
        employeeDao.updateEmployee(employee);
        response.sendRedirect("list");
    }

    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ClassNotFoundException {
        String employee_id = request.getParameter("employee_id");

        Employee employee = new Employee(employee_id);
        employeeDao.deleteUser(employee);
        response.sendRedirect("list");

    }
}
