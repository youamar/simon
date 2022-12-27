package g54915.simon.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuView extends VBox {
    private HBox hb;
    private Slider speedSl;
    private CheckBox silentBox;
    private Button longest, start, last;
    private Label simonLb, speedLb, gameInfosLb;

    public MenuView() {
        createLabels();
        createSlider();
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color: #f4f4f4;");
        createSettings();
    }

    public void createLabels() {
        simonLb = new Label("simon");
        speedLb = new Label("Speed");
        gameInfosLb = new Label("Info");
    }

    private void createSlider() {
        speedSl = new Slider(1, 2, 1.5);
        speedSl.setMajorTickUnit(1);
        speedSl.setShowTickMarks(true);
        speedSl.setSnapToTicks(true);
        speedSl.setMaxWidth(120);
        speedSl.setBlockIncrement(0.25);
        silentBox = new CheckBox("Silent mode");
    }

    public void createSettings() {
        hb = new HBox(3);
        longest = new Button("Longest");
        start = new Button("Start");
        last = new Button("Last");
        last.setMinHeight(50);
        start.setMinHeight(50);
        longest.setMinHeight(50);
        last.setMinWidth(70);
        start.setMinWidth(50);
        longest.setMinWidth(70);
    }

    public void addToContainers() {
        hb.getChildren().addAll(longest, start, last);
    }

    public Label getSimonLb() {
        return simonLb;
    }

    public Label getGameInfosLb() {
        return gameInfosLb;
    }

    public CheckBox getSilentBox() {
        return silentBox;
    }

    public Slider getSpeedSl() {
        return speedSl;
    }

    public Button getLongest() {
        return longest;
    }

    public Button getStart() {
        return start;
    }

    public Button getLast() {
        return last;
    }

    public HBox getHb() {
        return hb;
    }

    public void setHb(HBox hb) {
        getChildren().addAll(simonLb, speedSl, speedLb, hb, gameInfosLb, silentBox);
    }

    public void setAlignmentAndPadding() {
        hb.setAlignment(Pos.CENTER);
        hb.setPadding(new Insets(25, 0, 25, 0));
    }

    public void setGp(GameView gp) {
        prefWidthProperty().bind(gp.widthProperty());
        prefHeightProperty().bind(gp.prefHeightProperty());
    }

}
