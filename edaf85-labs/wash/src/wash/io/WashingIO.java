package wash.io;

/** Input/Output (IO) for our washing machine */
public interface WashingIO {

    /** @return water level, in range 0..20 liters */
    double getWaterLevel();
    
    /** @return temperature, in degrees Celsius */
    double getTemperature();

    /** Blocks until a program button (0, 1, 2, 3) is pressed */
    int awaitButton() throws InterruptedException;

    /** Turn heating element on (true) or off (false) */
    void heat(boolean on);

    /** Set inlet valve to open (true) or closed (false) */
    void fill(boolean on);

    /** Turn drain pump on (true) or off (false) */
    void drain(boolean on);

    /** Set hatch to locked (true) or unlocked (false) */
    void lock(boolean locked);
    
    /** @param mode  one of Spin.IDLE, Spin.LEFT, Spin.RIGHT, Spin.FAST */
    void setSpinMode(Spin mode);

    /** Values for setSpinMode */
    enum Spin { IDLE, LEFT, RIGHT, FAST };
}
