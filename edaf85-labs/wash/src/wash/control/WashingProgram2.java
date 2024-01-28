package wash.control;

import actor.ActorThread;
import wash.control.WashingMessage.Order;
import wash.io.WashingIO;
import static wash.control.WashingMessage.Order.*;

public class WashingProgram2 extends ActorThread<WashingMessage> {

    private WashingIO io;
    private ActorThread<WashingMessage> temp;
    private ActorThread<WashingMessage> water;
    private ActorThread<WashingMessage> spin;

    WashingProgram2(WashingIO io, ActorThread<WashingMessage> temp, 
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

            System.out.println("washing program 2 started");


            //Lock the hatch
            io.lock(true);


            System.out.println("filling with water");
            water.send(new WashingMessage(this, WATER_FILL));
            WashingMessage ack1 = receive();
            System.out.println("washing program 2 got " + ack1);

            
            System.out.println("setting TEMP_SET_40");
            temp.send(new WashingMessage(this, TEMP_SET_40));
            WashingMessage ack2 = receive();
            System.out.println("washing program 2 got " + ack2);
            

            System.out.println("setting SPIN_SLOW...");
            spin.send(new WashingMessage(this, SPIN_SLOW));
            WashingMessage ack3 = receive();
            System.out.println("washing program 2 got " + ack3);


            Thread.sleep(20 * 60000 / Settings.SPEEDUP);


            System.out.println("setting SPIN_OFF...");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack4 = receive();
            System.out.println("washing program 2 got " + ack4);


            System.out.println("Setting TEMP_40_OFF");
            temp.send(new WashingMessage(this, TEMP_IDLE));
            WashingMessage ack5 = receive();
            System.out.println("washing program 2 got " + ack5);

    
            System.out.println("DRAINING..");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack6 = receive();
            System.out.println("washing program 2 got " + ack6);


            System.out.println("filling with water");
            water.send(new WashingMessage(this, WATER_FILL));
            WashingMessage ack7 = receive();
            System.out.println("washing program 2 got " + ack7);

            
            System.out.println("setting TEMP_SET_60");
            temp.send(new WashingMessage(this, TEMP_SET_60));
            WashingMessage ack8 = receive();
            System.out.println("washing program 2 got " + ack8);

            System.out.println("setting SPIN_SLOW...");
            spin.send(new WashingMessage(this, SPIN_SLOW));
            WashingMessage ack9 = receive();
            System.out.println("washing program 2 got " + ack9);


            Thread.sleep(30 * 60000 / Settings.SPEEDUP);

            System.out.println("setting SPIN_OFF...");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack10 = receive();
            System.out.println("washing program 2 got " + ack10);


            System.out.println("Setting TEMP_60_OFF");
            temp.send(new WashingMessage(this, TEMP_IDLE));
            WashingMessage ack11 = receive();
            System.out.println("washing program 2 got " + ack11);

            System.out.println("DRAINING..");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack12 = receive();
            System.out.println("washing program 2 got " + ack12);

            System.out.println("setting SPIN_SLOW...");
            spin.send(new WashingMessage(this, SPIN_SLOW));
            WashingMessage ack13 = receive();
            System.out.println("washing program 2 got " + ack13);

            for(int i = 0; i < 5; i++){

                
                System.out.println("FILLING..");
                water.send(new WashingMessage(this, WATER_FILL));
                WashingMessage ack14 = receive();
                System.out.println("washing program 2 got " + ack14);
                
                Thread.sleep(2 * 60000 / Settings.SPEEDUP);
                
                System.out.println("DRAINING..");
                water.send(new WashingMessage(this, WATER_DRAIN));
                WashingMessage ack15 = receive();
                System.out.println("washing program 2 got " + ack15);
                
            }
            
            System.out.println("SPIN_OFF");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack16 = receive();
            System.out.println("washing program 2 got " + ack16);

            System.out.println("DRAINING..");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack17 = receive();
            System.out.println("washing program 2 got " + ack17);


            System.out.println("Centrifuging...");
            spin.send(new WashingMessage(this, SPIN_FAST));
            WashingMessage ack18 = receive();
            System.out.println("washing program 2 got " + ack18);
    

            System.out.println("Running drain pump...");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack19 = receive();
            System.out.println("washing program 2 got " + ack19);
    
            // Centrifuge for simulated time 30 minutes
            Thread.sleep(5 * 60000 / Settings.SPEEDUP);
    
            System.out.println("Stopping centrifuge...");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack20 = receive();
            System.out.println("washing program 2 got " + ack20);
    
            System.out.println("Stopping drain pump...");
            water.send(new WashingMessage(this, WATER_IDLE));
            WashingMessage ack21 = receive();
            System.out.println("washing program 2 got " + ack21);

            Thread.sleep(1 * 60000 / Settings.SPEEDUP);

            //Now that the barrel has stopped, it is safe to open the hatch.
            io.lock(false);
   
        } catch (InterruptedException e) {
            temp.send(new WashingMessage(this, TEMP_IDLE));
            water.send(new WashingMessage(this, WATER_IDLE));
            spin.send(new WashingMessage(this, SPIN_OFF));
            System.out.println("washing program terminated");
        }
    }


}
