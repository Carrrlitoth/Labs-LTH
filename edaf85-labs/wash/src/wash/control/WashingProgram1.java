package wash.control;

import actor.ActorThread;
import wash.control.WashingMessage.Order;
import wash.io.WashingIO;
import static wash.control.WashingMessage.Order.*;

public class WashingProgram1 extends ActorThread<WashingMessage> {

    private WashingIO io;
    private ActorThread<WashingMessage> temp;
    private ActorThread<WashingMessage> water;
    private ActorThread<WashingMessage> spin;

    WashingProgram1(WashingIO io, ActorThread<WashingMessage> temp, 
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

            System.out.println("washing program 1 started");


            //Lock the hatch
            io.lock(true);


            System.out.println("filling with water");
            water.send(new WashingMessage(this, WATER_FILL));
            WashingMessage ack1 = receive();
            System.out.println("washing program 1 got " + ack1);

            
            System.out.println("setting TEMP_SET_40");
            temp.send(new WashingMessage(this, TEMP_SET_40));
            WashingMessage ack2 = receive();
            System.out.println("washing program 1 got " + ack2);
            

            //Instruct SpinController to rotate barrel slowly, back and forth
            //Expect an acknowledgment in response.
            System.out.println("setting SPIN_SLOW...");
            spin.send(new WashingMessage(this, SPIN_SLOW));
            WashingMessage ack3 = receive();
            System.out.println("washing program 1 got " + ack3);


            //Spin for five simulated minutes (one minute == 60000 milliseconds)
            Thread.sleep(30 * 60000 / Settings.SPEEDUP);
            
            //Instruct SpinController to stop spin barrel spin.
            //Expect an acknowledgment in response.
            System.out.println("setting SPIN_OFF...");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack4 = receive();
            System.out.println("washing program 1 got " + ack4);


            System.out.println("Setting TEMP_40_OFF");
            temp.send(new WashingMessage(this, TEMP_IDLE));
            WashingMessage ack5 = receive();
            System.out.println("washing program 1 got " + ack5);

    
            System.out.println("DRAINING..");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack6 = receive();
            System.out.println("washing program 1 got " + ack6);

            System.out.println("setting SPIN_SLOW...");
            spin.send(new WashingMessage(this, SPIN_SLOW));
            WashingMessage ack7 = receive();
            System.out.println("washing program 1 got " + ack7);
            
            for(int i = 0; i < 5; i++){

                
                System.out.println("FILLING..");
                water.send(new WashingMessage(this, WATER_FILL));
                WashingMessage ack8 = receive();
                System.out.println("washing program 1 got " + ack8);
                
                Thread.sleep(2 * 60000 / Settings.SPEEDUP);
                
                System.out.println("DRAINING..");
                water.send(new WashingMessage(this, WATER_DRAIN));
                WashingMessage ack9 = receive();
                System.out.println("washing program 1 got " + ack9);
                
            }
            
            System.out.println("SPIN_OFF");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack10 = receive();
            System.out.println("washing program 1 got " + ack10);

            System.out.println("DRAINING..");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack11 = receive();
            System.out.println("washing program 1 got " + ack11);


            System.out.println("Centrifuging...");
            spin.send(new WashingMessage(this, SPIN_FAST));
            WashingMessage ack12 = receive();
            System.out.println("washing program 1 got " + ack12);
    

            System.out.println("Running drain pump...");
            water.send(new WashingMessage(this, WATER_DRAIN));
            WashingMessage ack13 = receive();
            System.out.println("washing program 1 got " + ack13);
    
            // Centrifuge for simulated time 30 minutes
            Thread.sleep(5 * 60000 / Settings.SPEEDUP);
    
            System.out.println("Stopping centrifuge...");
            spin.send(new WashingMessage(this, SPIN_OFF));
            WashingMessage ack14 = receive();
            System.out.println("washing program 1 got " + ack14);
    
            System.out.println("Stopping drain pump...");
            water.send(new WashingMessage(this, WATER_IDLE)); // Assuming WATER_IDLE represents drain pump off
            WashingMessage ack15 = receive();
            System.out.println("washing program 1 got " + ack15);
            
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