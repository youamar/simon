package g54915.simon.view;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;

public class GameView extends GridPane {
    private Button greenBt, greenBt2, greenBt3,
            redBt, redBt2, redBt3, yellowBt,
            yellowBt2, yellowBt3,
            blueBt, blueBt2, blueBt3;
    private List<Button> bts, greenBts, redBts, yellowBts, blueBts;

    public GameView() {
        setUpGp();
    }

    public void createColoredButtons() {
        greenBt = new Button();
        greenBt2 = new Button();
        greenBt3 = new Button();
        redBt = new Button();
        redBt2 = new Button();
        redBt3 = new Button();
        yellowBt = new Button();
        yellowBt2 = new Button();
        yellowBt3 = new Button();
        blueBt = new Button();
        blueBt2 = new Button();
        blueBt3 = new Button();
        createBtsLists();
    }

    public void createBtsLists() {
        bts = new ArrayList<>();
        greenBts = new ArrayList<>();
        redBts = new ArrayList<>();
        yellowBts = new ArrayList<>();
        blueBts = new ArrayList<>();
        addBtsToBts();
        addBtsToBlueBts();
        addBtsToGreenBts();
        addBtsToRedBts();
        addBtsToYellowBts();
    }

    public void setUpGp() {
        createColoredButtons();
        add(blueBt, 3, 2);
        add(blueBt2, 3, 3);
        add(blueBt3, 2, 3);
        add(greenBt, 0, 0);
        add(greenBt2, 1, 0);
        add(greenBt3, 0, 1);
        add(redBt, 2, 0);
        add(redBt2, 3, 0);
        add(redBt3, 3, 1);
        add(yellowBt, 0, 2);
        add(yellowBt2, 0, 3);
        add(yellowBt3, 1, 3);
    }

    private void addBtsToBts() {
        bts.add(greenBt);
        bts.add(greenBt2);
        bts.add(greenBt3);
        bts.add(redBt);
        bts.add(redBt2);
        bts.add(redBt3);
        bts.add(yellowBt);
        bts.add(yellowBt2);
        bts.add(yellowBt3);
        bts.add(blueBt);
        bts.add(blueBt2);
        bts.add(blueBt3);
    }

    private void addBtsToGreenBts() {
        greenBts.add(greenBt);
        greenBts.add(greenBt2);
        greenBts.add(greenBt3);
    }

    private void addBtsToRedBts() {
        redBts.add(redBt);
        redBts.add(redBt2);
        redBts.add(redBt3);
    }

    private void addBtsToYellowBts() {
        yellowBts.add(yellowBt);
        yellowBts.add(yellowBt2);
        yellowBts.add(yellowBt3);
    }

    private void addBtsToBlueBts() {
        blueBts.add(blueBt);
        blueBts.add(blueBt2);
        blueBts.add(blueBt3);
    }

    public List<Button> getBts() {
        return bts;
    }

    public List<Button> getGreenBts() {
        return greenBts;
    }

    public List<Button> getRedBts() {
        return redBts;
    }

    public List<Button> getYellowBts() {
        return yellowBts;
    }

    public List<Button> getBlueBts() {
        return blueBts;
    }

    public void setVb(MenuView vb) {
        add(vb, 1, 1, 2, 2);
    }
}

