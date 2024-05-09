<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee Management Application</title>
</head>
<body>
<center>
    <h1>Employee Management</h1>
    <h2>
        <a href="<%request.getContextPath();%>/new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="<%request.getContextPath();%>/list">List All Users</a>

    </h2>
</center>
<div align="center">
    <c:if test="${employee != null}">
    <form action="update?id=<c:out value='${employee.employee_id}' />" method="post">
        </c:if>
        <c:if test="${employee == null}">
        <form action="<%request.getContextPath();%>/insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${employee != null}">
                            Edit Employee
                        </c:if>
                        <c:if test="${employee == null}">
                            Add New employee
                        </c:if>
                    </h2>
                </caption>
                <c:if test="${employee != null}">
                    <input type="hidden" name="id" value="<c:out value='${employee.employee_id}' />" />
                </c:if>
                <tr>
                    <th>Name: </th>
                    <td>
                        <input type="text" name="name" size="45"
                               value="<c:out value='${employee.employee_name}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>birth: </th>
                    <td>
                        <input type="text" name="birth" size="45"
                               value="<c:out value='${employee.birthday}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>phone: </th>
                    <td>
                        <input type="text" name="phone" size="45"
                               value="<c:out value='${employee.phone_number}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Email: </th>
                    <td>
                        <input type="text" name="country" size="15"
                               value="<c:out value='${employee.email}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
