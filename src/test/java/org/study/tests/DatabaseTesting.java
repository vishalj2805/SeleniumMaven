package org.study.tests;


import java.sql.*;

public class DatabaseTesting {

    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Qadbt", "root", "vishalj2805");
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("Select * from EmployeeInfo");
        resultSet.next();
        System.out.printf(resultSet.getString("name"));
        conn.close();

    }
}
