/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g54915.simon.view;

import g54915.simon.controller.Controller;
import g54915.simon.model.Model;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * @author g54915
 */
public class View implements PropertyChangeListener {
    private final Controller controller;
    private final GameView gridPane = new GameView();
    private final MenuView vBox = new MenuView();

    public View(Controller controller, Model model) {
        vBox.setGp(gridPane);
        vBox.setHb(vBox.getHb());
        gridPane.setVb(vBox);
        this.controller = controller;
        model.subscribe(this);
    }

    public void setStage(Stage primaryStage) {
        primaryStage.setTitle("54915 - Simon");
        primaryStage.setMinWidth(515);
        primaryStage.setMinHeight(532);
        vBox.addToContainers();
        setComponentsSizes();
        bindColoredButtons();
        vBox.setAlignmentAndPadding();
        setStyle();
        onClickButtonChangeColor();
        setOnStart();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Color c = (Color) evt.getNewValue();
        controller.playAnimation(c);
    }

    public void bindColoredButtons() {
        bindColoredButtonsHeight();
        bindColoredButtonsWidth();
    }

    public void bindColoredButtonsHeight() {
        gridPane.getBts().forEach(bt -> bt.prefHeightProperty().bind(gridPane.heightProperty()));
    }

    public void bindColoredButtonsWidth() {
        gridPane.getBts().forEach(bt -> bt.prefWidthProperty().bind(gridPane.widthProperty()));
    }

    public void showStage(Stage primaryStage) {
        Scene sc = new Scene(gridPane);
        primaryStage.setScene(sc);
        sc.getStylesheets().add("https://fonts.googleapis.com"
                + "/css2?family=Share:wght@400;700&display=swap");
        primaryStage.show();
    }

    public void onClickButtonChangeColor() {
        gridPane.getBlueBts().forEach(bt -> bt.addEventHandler(ActionEvent.ACTION,
                (ActionEvent event) -> controller.click(Color.BLUE)));
        gridPane.getGreenBts().forEach(bt -> bt.addEventHandler(ActionEvent.ACTION,
                (ActionEvent event) -> controller.click(Color.GREEN)));
        gridPane.getRedBts().forEach(bt -> bt.addEventHandler(ActionEvent.ACTION,
                (ActionEvent event) -> controller.click(Color.RED)));
        gridPane.getYellowBts().forEach(bt -> bt.addEventHandler(ActionEvent.ACTION,
                (ActionEvent event) -> controller.click(Color.YELLOW)));
    }

    public void start(Stage primaryStage) {
        setStage(primaryStage);
        showStage(primaryStage);
    }

    public GameView getGridPane() {
        return gridPane;
    }

    public MenuView getvBox() {
        return vBox;
    }

    public MenuView getHb() {
        return vBox;
    }

    public void setStyle() {
        setButtonsStyle();
        setSettingsStyle();
    }

    public void setFailMessage() {
        getvBox().getGameInfosLb().setText("GAME OVER !");
        getvBox().getGameInfosLb().setTextFill(Color.RED);
    }

    public void setWinMessage() {
        getvBox().getGameInfosLb().setText("PERFECT !");
        getvBox().getGameInfosLb().setTextFill(Color.GREEN);
    }

    public void setInfo() {
        getvBox().getGameInfosLb().setText("Info");
        getvBox().getGameInfosLb().setTextFill(Paint.valueOf("#a0a0a0"));
    }

    public void setOnStart() {
        getHb().getStart().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> controller.start());
        getHb().getLast().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> controller.last());
        getHb().getLongest().addEventHandler(ActionEvent.ACTION, (ActionEvent event) -> controller.longest());
        setMouse(true);
    }

    public void setMouse(boolean t) {
        getHb().getLast().setMouseTransparent(t);
        getHb().getLongest().setMouseTransparent(t);
        getGridPane().getBts().forEach(bt -> bt.setMouseTransparent(t));
    }

    public void setComponentsSizes() {
        setWidth();
        setHeight();
    }

    public void setEnlightenedBlueBtStyle() {
        gridPane.getBlueBts().forEach((Button bt) -> bt.setStyle("-fx-background-color: #14bdff;"));
    }

    public void setEnlightenedGreenBtStyle() {
        gridPane.getGreenBts().forEach((Button bt) -> bt.setStyle("-fx-background-color: #47f700;"));
    }

    public void setEnlightenedRedBtStyle() {
        gridPane.getRedBts().forEach((Button bt) -> bt.setStyle("-fx-background-color: #ff0000;"));
    }

    public void setEnlightenedYellowBtStyle() {
        gridPane.getYellowBts().forEach((Button bt) -> bt.setStyle("-fx-background-color: #ffde00;"));
    }

    public void setHeight() {
        gridPane.getBts().forEach(bt -> bt.setMinHeight(60));
    }

    public void setWidth() {
        gridPane.getBts().forEach(bt -> bt.setMinWidth(60));
    }

    public void setButtonsStyle() {
        setGreenBtStyle();
        setRedBtStyle();
        setYellowBtStyle();
        setBlueBtStyle();
    }

    public void setBlueBtStyle() {
        gridPane.getBlueBts().forEach(bt -> bt.setStyle("-fx-background-color: #0a5775;"));
    }

    public void setGreenBtStyle() {
        gridPane.getGreenBts().forEach(bt -> bt.setStyle("-fx-background-color: #1c6200;"));
    }

    public void setRedBtStyle() {
        gridPane.getRedBts().forEach(bt -> bt.setStyle("-fx-background-color: #b00000;"));
    }

    public void setYellowBtStyle() {
        gridPane.getYellowBts().forEach(bt -> bt.setStyle("-fx-background-color: #998500;"));
    }

    public void setSettingsStyle() {
        vBox.getSimonLb().setStyle("-fx-font-family: 'Share', cursive; -fx-font-size: 40;");
        vBox.getGameInfosLb().setStyle("-fx-text-fill : #a0a0a0; -fx-padding: 0 0 10 0;");
        vBox.getLongest().setStyle("-fx-background-radius: 100; -fx-border-radius: 100; "
                + "-fx-border-width: 4; -fx-border-color: black; "
                + "-fx-text-fill : #fffefc; -fx-background-color: #ffbd00;");
        vBox.getLast().setStyle("-fx-background-radius: 100; -fx-border-radius: 100;"
                + " -fx-border-width: 4; -fx-border-color: black; "
                + "-fx-text-fill : #fffefc; -fx-background-color: #ffbd00;");
        vBox.getStart().setStyle("-fx-background-radius: 100; -fx-border-radius: 100; "
                + "-fx-border-width: 4; -fx-border-color: black; "
                + "-fx-text-fill : #fffefc; -fx-background-color: #871625;");
    }
}
