import java.util.concurrent.Semaphore;

import clock.AlarmClockEmulator;
import clock.io.Choice;
import clock.io.ClockInput;
import clock.io.ClockInput.UserInput;
import clock.io.ClockOutput;

public class ClockMain {
    public static void main(String[] args) throws InterruptedException {
        Monitor mon = new Monitor();

        AlarmClockEmulator emulator = new AlarmClockEmulator();

        ClockInput in  = emulator.getInput();
        ClockOutput out = emulator.getOutput();
        int[] time = new int[3];
        int[] alarmTime = new int[3];

        out.displayTime(0, 0, 0);   // arbitrary time: just an example

        Thread ticking = new Thread (() -> {
            try {
                time[0] = 0;
                time[1] = 0;
                time[2] = 0;
                while(true) {
                mon.tickGet(time, alarmTime, out);
                }
            } catch(InterruptedException e) {
                throw new Error(e);
            }});

            Thread setTimes = new Thread (() -> {
                try {
                    while(true){
                        mon.setTime(time, alarmTime, out, in);
                    }
                } catch (InterruptedException e) {
                    throw new Error(e);
                }
            });
        ticking.start();
        setTimes.start();
        
    }

}
