<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %><%@ page language="java" contentType="text/html; charset=UTF-8"
                                                                                pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
<center>
    <h1>List Management</h1>
    <h2>
        <a href="<%request.getContextPath();%>/new">Add New Employee</a>
        &nbsp;&nbsp;&nbsp;
        <a href="<%request.getContextPath();%>/list">List All Users</a>

    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Birth</th>
            <th>Phone</th>
            <th>Email</th>
        </tr>
        <c:forEach var="employee" items="${listEmployee}">
            <tr>
                <td><c:out value="${employee.employee_id}" /></td>
                <td><c:out value="${employee.employee_name}" /></td>
                <td><c:out value="${employee.birthday}" /></td>
                <td><c:out value="${employee.phone_number}" /></td>
                <td><c:out value="${employee.email}" /></td>
                <td>
                    <a href="edit?id=<c:out value='${employee.employee_id}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="delete?id=<c:out value='${employee.employee_id}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>