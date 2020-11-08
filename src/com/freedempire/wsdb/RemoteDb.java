package com.freedempire.wsdb;

import javax.swing.*;
import java.sql.*;

public class RemoteDb {
    private Connection connection;
    private PreparedStatement preparedStatement;

    public RemoteDb() {
        createConnection();
    }

    private void createConnection() {
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            not needed anymore

            connection =DriverManager.getConnection("jdbc:mysql://apache.wic.edu.au/17519", "17519", "17519");
            JOptionPane.showMessageDialog(null, "College database connected successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEntry(String name, String mobile, String course) {
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO srdb (name, mobile, course) VALUES (?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mobile);
            preparedStatement.setString(3, course);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateEntry(String id, String name, String mobile, String course) {
        try {
            preparedStatement = connection.prepareStatement("UPDATE srdb SET name = ?, mobile = ?, course = ? WHERE id = ?");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, mobile);
            preparedStatement.setString(3, course);
            preparedStatement.setString(4, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteEntry(String id) {
        try {
            preparedStatement = connection.prepareStatement("DELETE FROM srdb WHERE id = ?");
            preparedStatement.setString(1, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getData() {
        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM srdb");
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        new RemoteDb();
    }
}
