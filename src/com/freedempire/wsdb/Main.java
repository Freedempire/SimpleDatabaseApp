package com.freedempire.wsdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("viewDesign.fxml"));
        primaryStage.setTitle("Wells Students Registration Database");
        primaryStage.setScene(new Scene(root));
        primaryStage.resizableProperty().setValue(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
