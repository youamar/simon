package g54915.simon.model;

import g54915.simon.view.View;
import javafx.animation.PauseTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Model {
    private int count;
    private int current;
    private int colorCount;
    private final PropertyChangeSupport support;
    private final ArrayList<List<Color>> sequences = new ArrayList<>();
    private static final List<Color> colors = Arrays.asList(Color.BLUE, Color.RED, Color.YELLOW, Color.GREEN);

    public Model() {
        support = new PropertyChangeSupport(this);
    }

    public void initialize() {
        colorCount = 0;
        count = 0;
    }

    public void subscribe(View view) {
        support.addPropertyChangeListener(view);
    }

    public void playSound(int nbNote) throws MidiUnavailableException {
        var synth = MidiSystem.getSynthesizer();
        synth.open();
        var channel = synth.getChannels()[0];
        channel.noteOn(nbNote, 80);
        var pause = new PauseTransition(Duration.seconds(1));
        pause.setOnFinished(event -> channel.noteOff(nbNote)
        );
        pause.play();
    }

    public double setSliderSpeed(double value) {
        double speed = 0.0;
        if (value == 1.0) {
            speed = 2.0;
        } else if (value == 1.25) {
            speed = 1.8;
        } else if (value == 1.5) {
            speed = 1.5;
        } else if (value == 1.75) {
            speed = 1.3;
        } else if (value == 2.0) {
            speed = 1.2;
        }
        return speed;
    }

    public void setColor(Color c) {
        support.firePropertyChange("c", Color.WHITE, c);
    }

    public void last() {
        current = sequences.size() - 1;
        initialize();
    }

    public void longest() {
        int max = 0;
        for (int i = 0; i < sequences.size(); i++) {
            if (sequences.get(i).size() > max) {
                max = sequences.get(i).size();
                current = i;
            }
        }
        initialize();
    }

    public int click(Color color) {
        if (sequences.get(current).get(count) == color) {
            count++;
            if (count == colorCount) {
                return 1;
            }
            return 0;
        }
        return -1;
    }

    public void start() {
        initialize();
        generateSequence();
    }

    public void playSequence() {
        if (colorCount < sequences.get(current).size()) {
            setColor(sequences.get(current).get(colorCount));
            colorCount++;
        }
    }

    private void generateSequence() {
        List<Color> colorList = new ArrayList<>();
        sequences.add(colorList);
        addColorInSeq();
        current = sequences.size() - 1;
    }

    public void addColorInSeq() {
        List<Color> colorList = sequences.get(sequences.size() - 1);
        Random r = new Random();
        int col = r.nextInt(4);
        colorList.add(colors.get(col));
    }

    public int getSequenceSize() {
        return sequences.get(current).size();
    }

    public void fail() {
        count = 0;
        colorCount = 0;
    }

}
