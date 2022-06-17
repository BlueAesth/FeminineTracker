package com.example.femininetracker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.text.StyleConstants.setBackground;


public class FullCalendarView {

    private ArrayList<AnchorPaneNode> allCalendarDays = new ArrayList<>(35);
    private VBox view;
    private Text calendarTitle;
    private YearMonth currentYearMonth;
    int flag = 0;

    public FullCalendarView(YearMonth yearMonth) throws IOException {
        currentYearMonth = yearMonth;
        // Create the calendar grid pane
        GridPane calendar = new GridPane();
        calendar.setPrefSize(600, 400);
        calendar.setGridLinesVisible(true);
        // Create rows and columns with anchor panes for the calendar
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 7; j++) {
                AnchorPaneNode ap = new AnchorPaneNode();
                ap.setPrefSize(200, 200);
                calendar.add(ap, j, i);
                allCalendarDays.add(ap);
            }
        }
        // Days of the week labels
        Text[] dayNames = new Text[]{new Text("Sunday"), new Text("Monday"), new Text("Tuesday"),
                new Text("Wednesday"), new Text("Thursday"), new Text("Friday"),
                new Text("Saturday")};
        GridPane dayLabels = new GridPane();
        dayLabels.setPrefWidth(600);
        Integer col = 0;
        for (Text txt : dayNames) {
            AnchorPane ap = new AnchorPane();
            ap.setPrefSize(200, 10);
            ap.setBottomAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            dayLabels.add(ap, col++, 0);
        }
        // Create calendarTitle and buttons to change current month
        calendarTitle = new Text();
        Button previousMonth = new Button("<<");

        previousMonth.setOnAction(e -> {
            try {
                previousMonth();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        Button nextMonth = new Button(">>");
        nextMonth.setOnAction(e -> {
            try {
                nextMonth();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        HBox titleBar = new HBox(previousMonth, calendarTitle, nextMonth);
        titleBar.setAlignment(Pos.BASELINE_CENTER);
        Text quotes = new Text();
        String quotesString;

        try {
            // initialize lines stream
            Stream<String> stream = Files.lines(Paths.get("src/main/resources/com/example/femininetracker/quotes.txt"));
            List<String> quoteList = new ArrayList<>();

            stream.filter(l -> l.endsWith(":"))
                    .collect(Collectors.toCollection(() -> quoteList));
                    //.forEach(System.out::println);

            Random rand = new Random();
            //System.out.println(quoteList.get(0));
            int randomElement = rand.nextInt(12);
            quotesString = quoteList.get(randomElement);
            quotes.setText(quotesString);
            quotes.setFont(Font.font(16));

            // close the stream
            stream.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        HBox modBar = new HBox();
        modBar.getChildren().add(quotes);
        // Populate calendar with the appropriate day numbers
        populateCalendar(yearMonth);
        // Create the calendar view
        view = new VBox(titleBar, dayLabels, calendar, modBar);//Add modBar
        view.setStyle("-fx-background-image:url('https://cdn.pixabay.com/photo/2017/02/16/19/47/bokeh-2072271_1280.jpg'); -fx-background-position: center center;");
    }

    public void populateCalendar(YearMonth yearMonth) throws IOException {
        // Get the date we want to start with on the calendar
        LocalDate calendarDate = LocalDate.of(yearMonth.getYear(), yearMonth.getMonthValue(), 1);
        // Dial back the day until it is SUNDAY (unless the month starts on a sunday)
        while (!calendarDate.getDayOfWeek().toString().equals("SUNDAY") ) {
            calendarDate = calendarDate.minusDays(1);
        }
        // Populate the calendar with day numbers
        for (AnchorPaneNode ap : allCalendarDays) {
            if (ap.getChildren().size() != 0) {
                ap.getChildren().remove(0);
            }
            Text txt = new Text(String.valueOf(calendarDate.getDayOfMonth()));
            ap.setDate(calendarDate);
            ap.setTopAnchor(txt, 5.0);
            ap.setLeftAnchor(txt, 5.0);
            ap.getChildren().add(txt);
            calendarDate = calendarDate.plusDays(1);
        }
        // Change the title of the calendar
        calendarTitle.setText(yearMonth.getMonth().toString() + " " + String.valueOf(yearMonth.getYear()));
    }

    private void previousMonth() throws IOException {

        currentYearMonth = currentYearMonth.minusMonths(1);
        populateCalendar(currentYearMonth);

        if (flag == 0) {
            for (AnchorPaneNode pm : allCalendarDays) {
                if (pm.backgroundProperty().get() != null) {
                    pm.setBackground(new Background(new BackgroundFill(Color.LIGHTGREY, CornerRadii.EMPTY, Insets.EMPTY)));
                    flag = 1;
                } else if (flag == 0){
                    pm.setBackground(null);
                }
            }
        }
    }

    private void nextMonth() throws IOException {
        currentYearMonth = currentYearMonth.plusMonths(1);
        populateCalendar(currentYearMonth);

        for (AnchorPaneNode pm : allCalendarDays) {
            if(pm.backgroundProperty().get()!=null) {
                pm.setBackground(new Background(new BackgroundFill(Color.LIGHTPINK, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        }
    }

    public VBox getView() {
        return view;
    }

    public ArrayList<AnchorPaneNode> getAllCalendarDays() {
        return allCalendarDays;
    }

    public void setAllCalendarDays(ArrayList<AnchorPaneNode> allCalendarDays) {
        this.allCalendarDays = allCalendarDays;
    }
}