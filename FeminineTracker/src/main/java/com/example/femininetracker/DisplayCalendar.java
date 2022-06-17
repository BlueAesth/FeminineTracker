package com.example.femininetracker;

import javafx.scene.*;
import javafx.stage.*;

import java.io.IOException;
import java.time.YearMonth;


public class DisplayCalendar {

    public static void display() throws IOException//Can make this the actual calendar. vvvvv
    {
        Stage popupwindow=new Stage();

        popupwindow.initModality(Modality.APPLICATION_MODAL);
        popupwindow.setTitle("Your Calendar");

        /*Label label1= new Label("Pop up window now displayed");
        //Button button1= new Button("Close this pop up window");
        //button1.setOnAction(e -> popupwindow.close());
        //VBox layout= new VBox(10);
        //layout.getChildren().addAll(label1, button1);
        layout.setAlignment(Pos.CENTER);

        //primaryStage.setTitle("Full Calendar Example");
        //primaryStage.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));
        //primaryStage.show();

        //Scene scene1= new Scene(layout, 300, 250);*/

        popupwindow.setScene(new Scene(new FullCalendarView(YearMonth.now()).getView()));

        popupwindow.showAndWait();

    }
}

