package clock.io;

/**
 * Output signals to clock hardware.
 * 
 * NOTE: you are not expected to modify this interface,
 * nor to implement it yourself. Instead, to control
 * the emulated hardware, do as follows:
 * 
 *   AlarmClockEmulator emulator = new AlarmClockEmulator();
 *   ClockInput out = emulator.getoutput();
 *
 * Then use the reference 'out' to control the output signals.
*/
public interface ClockOutput {

    /** Display the given time on the display, for example (15, 2, 37) for
        15 hours, 2 minutes and 37 seconds since midnight. */
    void displayTime(int hours, int mins, int secs);

    /** Indicate on the display whether the alarm is on or off. */
    void setAlarmIndicator(boolean on);

    /** Signal the alarm. (In the emulator, only a visual alarm is given.) */
    void alarm();
}
