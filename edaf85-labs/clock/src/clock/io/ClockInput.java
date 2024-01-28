package clock.io;

import java.util.concurrent.Semaphore;

/**
 * Input signals from clock hardware.
 * 
 * NOTE: you are not expected to modify this interface,
 * nor to implement it yourself. Instead, to read from
 * the emulated hardware, do as follows:
 * 
 *   AlarmClockEmulator emulator = new AlarmClockEmulator();
 *   ClockInput in = emulator.getInput();
 *
 * Then use the reference 'in' to read the input signals.
 */
public interface ClockInput {

    /** @return  semaphore signaled on user input (via hardware interrupt) */
    Semaphore getSemaphore();

    /** @return  an item of user input (available only when semaphore is signaled) */
    UserInput getUserInput();

    // -----------------------------------------------------------------------
    
    /** An item of input, entered by the user. */
    interface UserInput {
        /** @return  a value indicating the type of choice made by the user. */
        Choice choice();
    
        /**
         * These methods return a time set by the user (clock time or alarm time).
         * 
         * If choice() returns SET_TIME, these return the time the user entered.
         * If choice() returns SET_ALARM, these return the alarm time the user entered.
         * If choice() returns TOGGLE_ALARM, these return an invalid value.
         */
        int hours();
        int minutes();
        int seconds();
    }
}
