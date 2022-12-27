package g54915.simon.controller;

import g54915.simon.model.Model;
import g54915.simon.view.View;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.midi.MidiUnavailableException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

import static g54915.simon.model.Sounds.*;

public class Controller {
    private Timer timer;
    private int gameStatus;
    private final Stage stage;
    private final View view;
    private final Model model;
    private boolean isPlaying = false;
    private final PauseTransition p = new PauseTransition(Duration.seconds(1));

    public Controller(Model model, Stage stage) {
        view = new View(this, model);
        this.model = model;
        this.stage = stage;
        timer = new Timer();
        model.initialize();
    }

    public void enlightenBt(Color c, int soundValue) {
        enlightenButton(c);
        try {
            if (!view.getvBox().getSilentBox().isSelected()) {
                model.playSound(soundValue);
            }
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void enlightenButton(Color c) {
        view.setInfo();
        if (!isPlaying) {
            clickableButtons(false);
        }
        if (c == Color.BLUE) {
            view.setEnlightenedBlueBtStyle();
        } else if (c == Color.GREEN) {
            view.setEnlightenedGreenBtStyle();
        } else if (c == Color.RED) {
            view.setEnlightenedRedBtStyle();
        } else if (c == Color.YELLOW) {
            view.setEnlightenedYellowBtStyle();
        }
        p.play();
        p.setOnFinished(e -> {
            if (c == Color.BLUE) {
                view.setBlueBtStyle();
            } else if (c == Color.RED) {
                view.setRedBtStyle();
            } else if (c == Color.GREEN) {
                view.setGreenBtStyle();
            } else if (c == Color.YELLOW) {
                view.setYellowBtStyle();
            }
            if (!isPlaying) {
                clickableButtons(true);
                verifyStatus();
            }
        });
    }

    private void verifyStatus() {
        if (gameStatus == -1) {
            view.getGridPane().getBts().forEach(bt -> bt.setMouseTransparent(true));
            Platform.runLater(view::setFailMessage);
            if (!view.getvBox().getSilentBox().isSelected()) {
                try {
                    model.playSound(FAIL_SOUND.getSoundValue());
                } catch (MidiUnavailableException e) {
                    System.out.println("");
                }
            }
            model.fail();
        } else if (gameStatus == 1) {
            view.setWinMessage();
            model.initialize();
            cancelTimer();
            model.addColorInSeq();
            playSequence();
        }
    }

    private void cancelTimer() {
        timer.cancel();
    }

    public void playSequence() {
        var timeline = new Timeline(new KeyFrame(Duration.seconds(model.setSliderSpeed(view.getvBox().getSpeedSl().getValue())), actionEvent -> {
            model.playSequence();
        }));
        gameStatus = -2;
        clickableButtons(false);
        isPlaying = true;
        timeline.setCycleCount(model.getSequenceSize());
        timeline.play();
        timeline.setOnFinished(actionEvent -> {
            clickableButtons(true);
            isPlaying = false;
            beginTimer();
        });
    }

    private void beginTimer() {
        var timerTask = new TimerTask() {
            @Override
            public void run() {
                gameStatus = -1;
                verifyStatus();
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 3000L * model.getSequenceSize());
    }

    public void playAnimation(Color currentColor) {
        if (currentColor == Color.BLUE) {
            enlightenBt(Color.BLUE, BLUE_SOUND.getSoundValue());
        } else if (currentColor == Color.GREEN) {
            enlightenBt(Color.GREEN, GREEN_SOUND.getSoundValue());
        } else if (currentColor == Color.RED) {
            enlightenBt(Color.RED, RED_SOUND.getSoundValue());
        } else if (currentColor == Color.YELLOW) {
            enlightenBt(Color.YELLOW, YELLOW_SOUND.getSoundValue());
        }
    }

    public void last() {
        view.setInfo();
        model.last();
        playSequence();
        cancelTimer();
    }

    public void longest() {
        view.setInfo();
        model.longest();
        playSequence();
        cancelTimer();
    }

    public void click(Color color) {
        gameStatus = model.click(color);
        playAnimation(color);
    }

    public void start() {
        view.setInfo();
        model.start();
        playSequence();
        cancelTimer();
        view.setMouse(false);
    }

    public void clickableButtons(boolean isClickable) {
        view.getGridPane().getBts().forEach((Button bt) -> bt.setMouseTransparent(!isClickable));
    }

    public void run() {
        this.view.start(stage);
    }
}
