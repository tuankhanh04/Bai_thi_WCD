package com.example.model;

import java.util.Date;

public class Employee {
    private String employee_id;
    private String employee_name;
    private Date birthday;
    private String phone_number;
    private String email;

    public Employee() {}

    public Employee(String employee_id, String employee_name, java.sql.Date birthday, String phone_number, String email) {
        this.employee_id = employee_id;
        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Employee(String employee_name, java.sql.Date birthday, String phone_number, String email) {

        this.employee_name = employee_name;
        this.birthday = birthday;
        this.phone_number = phone_number;
        this.email = email;
    }

    public Employee(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
