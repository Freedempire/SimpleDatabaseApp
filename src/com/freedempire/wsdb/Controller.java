package com.freedempire.wsdb;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldMobile;

    @FXML
    private TextField textFieldCourse;

    @FXML
    private TableView<StudentModel> tableView;

    @FXML
    private TableColumn<StudentModel, String> columnId;

    @FXML
    private TableColumn<StudentModel, String> columnName;

    @FXML
    private TableColumn<StudentModel, String> columnMobile;

    @FXML
    private TableColumn<StudentModel, String> columnCourse;

    ObservableList<StudentModel> observableList = FXCollections.observableArrayList();

    private RemoteDb remoteDb;

    public void initialize() {
        remoteDb = new RemoteDb();
        tableViewInit();
        try {
            tableViewUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    private void handlerButtonAdd(ActionEvent actionEvent) {
        if (textFieldId.getText().length() != 0) {
            JOptionPane.showMessageDialog(null, "Please use the \"Upadate\" button to update existing entries.");
        } else {
            String name = textFieldName.getText();
            String mobile = textFieldMobile.getText();
            String course = textFieldCourse.getText();
            if (name.length() != 0 && mobile.length() != 0 && course.length() != 0) {
                try {
                    remoteDb.addEntry(name, mobile, course);
                    JOptionPane.showMessageDialog(null, "Data added successfully!");
                    tableViewUpdate();
                    clearFields();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data addding failed!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fill up all the three fields first!");
            }
        }
    }

    @FXML
    private void handlerButtonUpdate(ActionEvent actionEvent) {
        String id = textFieldId.getText();
        if (id.length() == 0) {
            JOptionPane.showMessageDialog(null, "Please use the \"Add\" button to add new entries.");
        } else {
            String name = textFieldName.getText();
            String mobile = textFieldMobile.getText();
            String course = textFieldCourse.getText();
            if (name.length() != 0 && mobile.length() != 0 && course.length() != 0) {
                try {
                    remoteDb.updateEntry(id, name, mobile, course);
                    JOptionPane.showMessageDialog(null, "Data updated successfully!");
                    tableViewUpdate();
                    clearFields();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Data updating failed!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "None of the fields can be empty!");
            }
        }
    }

    @FXML
    private void handlerButtonDelete(ActionEvent actionEvent) {
        String id = textFieldId.getText();
        if (id.length() != 0) {
            int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure to delete this record (id = " + id + ")?");
            if (dialogResult == JOptionPane.YES_OPTION) {
                try {
                    remoteDb.deleteEntry(id);
                    JOptionPane.showMessageDialog(null, "Data deleted successfully!");
                    tableViewUpdate();
                    clearFields();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void handlerButtonClear(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    private void handlerTableViewMouseClicked(MouseEvent mouseEvent) {
        StudentModel studentSelected = tableView.getSelectionModel().getSelectedItem();
        textFieldId.setText(studentSelected.getId());
        textFieldName.setText(studentSelected.getName());
        textFieldMobile.setText(studentSelected.getMobile());
        textFieldCourse.setText(studentSelected.getCourse());
    }

    private void tableViewInit() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnMobile.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        columnCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
    }

    private void tableViewUpdate() throws SQLException {
        ResultSet resultSet = remoteDb.getData();
        String id;
        String name;
        String mobile;
        String course;

        if (observableList.size() > 0) {
            observableList.clear();
        }

        if (resultSet != null) {
            while (resultSet.next()) {
                id = resultSet.getString("id");
                name = resultSet.getString("name");
                mobile = resultSet.getString("mobile");
                course = resultSet.getString("course");

                observableList.add(new StudentModel(id, name, mobile, course));
            }

            tableView.setItems(observableList);
        }
    }

    private void clearFields() {
        textFieldId.setText("");
        textFieldName.setText("");
        textFieldMobile.setText("");
        textFieldCourse.setText("");
    }
}
