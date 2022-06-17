package com.example.femininetracker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLogin extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Button loginSubmit= new Button("Login!~");
        TextField userName = new TextField ();
        PasswordField password = new PasswordField ();
        Label usernameLbl = new Label("Username: ");
        Label passwordLbl = new Label("Password: ");
        Label loginStatus = new Label("Please enter your username and password to login.");

        String userNameValue = userName.getText();
        String passwordValue = password.getText();

        VBox layout= new VBox();
        layout.setPadding(new Insets(10));
        layout.setSpacing(8);
        layout.getChildren().add(usernameLbl);
        layout.getChildren().add(userName);
        layout.getChildren().add(passwordLbl);
        layout.getChildren().add(password);
        layout.getChildren().add(loginSubmit);
        layout.getChildren().add(loginStatus);
        layout.setStyle("-fx-background-image:url('https://cdn.pixabay.com/photo/2017/02/16/19/47/bokeh-2072271_1280.jpg'); -fx-background-position: center center;");

        Scene scene1= new Scene(layout, 375, 250);
        primaryStage.setScene(scene1);
        primaryStage.setTitle("Login to Feminine Tracker");
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);
        primaryStage.show();

        loginSubmit.setOnAction(e -> {
            try {
                LoginController.handleSubmitButtonAction(userNameValue, passwordValue);
                DisplayCalendar.display();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}