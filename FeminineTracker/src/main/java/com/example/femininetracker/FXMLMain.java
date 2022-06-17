package com.example.femininetracker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.YearMonth;

public class FXMLMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fullCalendar.fxml"));
        primaryStage.setTitle("Full Calendar FXML Example");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setMaxHeight(600);
        primaryStage.setMaxWidth(600);
        // Get the calendarController and add the calendar view to it
        CalendarController calendarController = loader.getController();
        calendarController.calendarPane.getChildren().add(new FullCalendarView(YearMonth.now()).getView());
        primaryStage.show();
    }
}