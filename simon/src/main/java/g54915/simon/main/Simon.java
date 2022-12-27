package g54915.simon.main;

import g54915.simon.controller.Controller;
import g54915.simon.model.Model;
import javafx.application.Application;
import javafx.stage.Stage;

public class Simon extends Application {

    @Override
    public void start(Stage stage) {
        var model = new Model();
        var controller = new Controller(model, stage);
        controller.run();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
