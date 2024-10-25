package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import ui.Calendar;

import java.net.URL;
import java.nio.file.Paths;

public class Main extends Application {
    private final int SCREEN_WIDTH = 1440;
    private final int SCREEN_HEIGHT = 960;

    @Override
    public void start(Stage stage) {
        Calendar calendar = new Calendar();

        VBox root = new VBox(calendar.getCalendar());
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT); // Adjust size as needed
        URL url = getClass().getResource("/styles.css");
        scene.getStylesheets().add(url.toExternalForm());

        stage.setTitle("Calendar App");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}