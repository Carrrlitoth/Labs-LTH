
import java.util.concurrent.Semaphore;

import clock.io.Choice;
import clock.io.ClockInput;
import clock.io.ClockOutput;
import clock.io.ClockInput.UserInput;

public class Monitor {

    private Semaphore sem = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private boolean toggle = false;
    private boolean alarm = false;
    private int alarmCounter = 0;
    
    public void tickGet(int[] time, int[] alarmTime, ClockOutput out) throws InterruptedException {
        mutex.acquire();
        time[0]++;
        if(time[0] == 60) {
            time[0] = 0;
            time[1]++;
        } if(time[1] == 60) {
            time[1] = 0;
            time[2]++;
        } if(time[2] == 24) {
            time[2] = 0;
        }
        if(alarmTime[0] == time[0] && alarmTime[1] == time[1] && alarmTime[2] == alarmTime[2]) {
            alarm = true;
        }
        if(alarm && alarmCounter<20) {
        out.alarm();
        alarmCounter++;
        } 
        out.displayTime(time[2], time[1], time[0]);
        mutex.release();
        sem.release();
        Thread.sleep(1000);
        
    }

    public void setTime(int[] time,  int[] alarmTime, ClockOutput out, ClockInput in) throws InterruptedException {
        in.getSemaphore().acquire();
        mutex.acquire();
        
        UserInput userInput = in.getUserInput();
        Choice c = userInput.choice();
        int h = userInput.hours();
        int m = userInput.minutes();
        int s = userInput.seconds();
        
        if(c == Choice.SET_TIME) {
            time[0] = s;
            time[1] = m;
            time[2] = h;
            System.out.println("choice=" + c + " h=" + h + " m=" + m + " s=" + s);
        }
        if(c == Choice.SET_ALARM) {
            alarmTime[0] = s;
            alarmTime[1] = m;
            alarmTime[2] = h;
            toggle = true;
            out.setAlarmIndicator(true);
            System.out.println("choice=" + c + " h=" + h + " m=" + m + " s=" + s);
        }
        if(c == Choice.TOGGLE_ALARM) {
            if(toggle) {
            toggle = false;
            out.setAlarmIndicator(false);
            } else {
            toggle = true;
            out.setAlarmIndicator(true);
            }
        }
        mutex.release();
    }

}
