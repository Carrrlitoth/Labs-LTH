package wash.control;

import actor.ActorThread;
import wash.io.WashingIO;

import static wash.control.WashingMessage.Order.*;

/**
 * Program 3 for washing machine. This also serves as an example of how washing
 * programs can be structured.
 * 
 * This short program stops all regulation of temperature and water levels,
 * stops the barrel from spinning, and drains the machine of water.
 * 
 * It can be used after an emergency stop (program 0) or a power failure.
 */
public class WashingProgram3 extends ActorThread<WashingMessage> {

    private WashingIO io;
    private ActorThread<WashingMessage> temp;
    private ActorThread<WashingMessage> water;
    private ActorThread<WashingMessage> spin;
    
    public WashingProgram3(WashingIO io,
                           ActorThread<WashingMessage> temp,
                           ActorThread<WashingMessage> water,
                           ActorThread<WashingMessage> spin) 
    {
        this.io = io;
        this.temp = temp;
        this.water = water;
        this.spin = spin;
    }
    
    @Override
    public void run() {
        try {
            System.out.println("washing program 3 started");
            
            // Switch off heating
            temp.send(new WashingMessage(this, TEMP_IDLE));
            
            // Wait for temperature controller to acknowledge 
            WashingMessage ack1 = receive();
            System.out.println("got " + ack1);

            // Drain barrel, which may take some time. To ensure the barrel
            // is drained before we continue, an acknowledgment is required.
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack2 = receive();  // wait for acknowledgment
            System.out.println("got " + ack2);

            // Now that the barrel is drained, we can turn off water regulation.
            // For the WATER_IDLE order, we assume the water level regulator to
            // NOT send any acknowledgment. (Note: in your solution, you
            // are free to introduce an acknowledgment here if you wish.)
            water.send(new WashingMessage(this, WATER_IDLE));

            // Switch off spin. We expect an acknowledgment, to ensure
            // the hatch isn't opened while the barrel is spinning.
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack3 = receive();  // wait for acknowledgment
            System.out.println("got " + ack3);

            // Unlock hatch
            io.lock(false);
            
            System.out.println("washing program 3 finished");
        } catch (InterruptedException e) {
            
            // If we end up here, it means the program was interrupt()'ed:
            // set all controllers to idle

            temp.send(new WashingMessage(this, TEMP_IDLE));
            water.send(new WashingMessage(this, WATER_IDLE));
            spin.send(new WashingMessage(this, SPIN_OFF));
            System.out.println("washing program terminated");
        }
    }
}
