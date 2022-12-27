package g54915.simon.model;

public enum Sounds {
    BLUE_SOUND(71),
    GREEN_SOUND(68),
    YELLOW_SOUND(69),
    RED_SOUND(70),
    FAIL_SOUND(10);
    private final int soundValue;

    Sounds(int soundValue) {
        this.soundValue = soundValue;
    }

    public int getSoundValue() {
        return soundValue;
    }
}
