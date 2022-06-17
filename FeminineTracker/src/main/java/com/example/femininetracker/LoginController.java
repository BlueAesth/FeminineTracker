package com.example.femininetracker;

import javafx.event.ActionEvent;
import java.io.IOException;

public class LoginController extends ActionEvent {

    public static boolean handleSubmitButtonAction(String userName, String password) throws IOException {
        boolean flag;

        if(password.equals("Password") && userName.equals("Admin")){
            flag = true;
        } else {
            flag = false;
        }
        return flag;
    }
}