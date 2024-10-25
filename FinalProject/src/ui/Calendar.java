package ui;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.time.LocalDate;

public class Calendar {
    private final int ROWS = 5;
    private final int COLUMNS = 7;
    private final int CELL_WIDTH = 200;
    private final int CELL_HEIGHT = 160;
    private final int CELL_GAP = 5;

    private GridPane grid;

    private LocalDate today;
    private LocalDate currentFirstDay;
    private int currentRow;

    private boolean inAnimation = false;

    /**
     * Main function that generates the calendar
     * @return A scrollable pane which is the monthly calendar interface
     */
    public ScrollPane getCalendar() {
        grid = new GridPane();
        grid.setHgap(CELL_GAP);
        grid.setVgap(CELL_GAP);

        // Calculate the current day and infer the first day in the calendar
        today = LocalDate.now();
        currentFirstDay = today.minusDays(today.getDayOfWeek().getValue() % 7);

        loadInitialRows();

        // Wrap the grid in a scroll pane
        ScrollPane scrollPane = new ScrollPane(grid);
        scrollPane.setPrefHeight(CELL_HEIGHT * ROWS + CELL_GAP * ROWS);
        scrollPane.setPrefWidth(CELL_WIDTH * COLUMNS + CELL_GAP * (COLUMNS - 1));
        scrollPane.setMaxWidth(CELL_WIDTH * COLUMNS + CELL_GAP * (COLUMNS - 1));
        scrollPane.setFitToWidth(true);
        scrollPane.getStyleClass().add("monthView-scrollPane");
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        double rowHeight = CELL_HEIGHT + CELL_GAP;

        scrollPane.addEventFilter(ScrollEvent.SCROLL, event -> {
            // Ignore new scrolls during animation
            if (inAnimation) {
                event.consume();
                return;
            }

            double currentVValue = scrollPane.getVvalue();
            double totalScrollableHeight = grid.getHeight() - scrollPane.getViewportBounds().getHeight();
            double scrollAmount = rowHeight / totalScrollableHeight;

            double targetVValue = (event.getDeltaY() < 0)
                    ? Math.min(currentVValue + scrollAmount, 1)
                    : Math.max(currentVValue - scrollAmount, 0);

            inAnimation = true;
            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.millis(500), new KeyValue(scrollPane.vvalueProperty(), targetVValue, Interpolator.SPLINE(0.1, 0.7, 0.3, 0.9)))
            );
            // only updates the currentRow after the animation is complete
            timeline.setOnFinished(e -> {
                if (event.getDeltaY() < 0) { // Scrolling down
                    addNextRow();
                    //removeTopRow();
                } else { // Scrolling up
                    addPrevRow();
                    //removeBottomRow();
                }
                inAnimation = false;
            });
            timeline.play();

            event.consume();
        });

        return scrollPane;
    }

    /**
     * This function is used to generate a single cell in the monthly view.
     * @param date The date corresponding to the cell
     * @param isToday If the date is the current day
     * @return Vbox for the day cell
     */
    private VBox createDayCell(LocalDate date, boolean isToday) {
        VBox cell = new VBox();
        cell.setPrefSize(CELL_WIDTH, CELL_HEIGHT);
        cell.setAlignment(Pos.CENTER);
        cell.getStyleClass().add("monthView-dayCell");
        if (isToday) {
            cell.getStyleClass().add("monthView-dayCell-today");
        }

        Label dateLabel = new Label(date.toString());
        dateLabel.getStyleClass().add("dateLabel");

        cell.getChildren().add(dateLabel);
        return cell;
    }

    private void loadInitialRows() {
        for (int row = 0; row < ROWS+4; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                LocalDate cellDate = currentFirstDay.plusDays((row * COLUMNS) + col);
                VBox dayLabel = createDayCell(cellDate, cellDate.equals(today));
                grid.add(dayLabel, col, row);
            }
        }
    }

    public void addNextRow() {
        int lastRow = GridPane.getRowIndex(grid.getChildren().get(grid.getChildren().size() - 1)) + 1;
        for (int col = 0; col < COLUMNS; col++) {
            LocalDate cellDate = currentFirstDay.plusDays((lastRow * COLUMNS) + col);
            VBox dayLabel = createDayCell(cellDate, cellDate.equals(today));
            grid.add(dayLabel, col, lastRow);
        }
    }

    public void addPrevRow() {

    }

    public void removeTopRow() {

    }

    public void removeBottomRow() {

    }
}
