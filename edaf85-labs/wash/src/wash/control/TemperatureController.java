package wash.control;

import actor.ActorThread;
import wash.control.WashingMessage.Order;
import wash.io.WashingIO;

public class TemperatureController extends ActorThread<WashingMessage> {

    // TODO: add attributes
    //state machine
    private enum State {HEATING_40, HEATING_60 , COOLING, IDLE}
    State state;
    private WashingIO io;
    private int dt;
    private ActorThread<WashingMessage> sender;
    private boolean sendAck;

    public TemperatureController(WashingIO io) {
        // TODO
        this.io = io;
        dt = 10;
        state = State.IDLE;
        sendAck = false;
    }

    @Override
    public void run() {
        // TODO
        try {
            while(true){
                
                WashingMessage m = receiveWithTimeout(dt * 1000 / Settings.SPEEDUP);

                if(m != null){

                    System.out.println("System got " + m);

                    sendAck = true;

                    sender = m.sender();
                    Order order = m.order();

                    switch(order){
                        case TEMP_SET_40:
                            state = State.HEATING_40;
                            
                        break;

                        case TEMP_SET_60:
                            state = State.HEATING_60;
                        break;

                        case TEMP_IDLE:
                            state = State.IDLE;
                            io.heat(false);
                            sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));

                        default:
                            break;
                    }

                }

                double upperBound;
                double lowerBound;
                double currentTemperature;
                double mu;
                double ml;

                switch(state){
                    case HEATING_40:

                        
                        upperBound = 40.0;
                        lowerBound = 38.0;
                        currentTemperature = io.getTemperature();

                        mu = 0.0478 * dt;
                        ml = dt * 9.52 * Math.pow(10, -2);

                        if(currentTemperature <= lowerBound + ml){
                            io.heat(true);
                        } else if(currentTemperature >= upperBound - mu) {
                            io.heat(false);

                            
                            if(sendAck){
                                sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
                                sendAck = false;
                                System.out.println("SendAck = true");
                            }
                        }
                        
                    
 
                    break;

                    case HEATING_60:
                        
                        upperBound = 60.0;
                        lowerBound = 58.0;
                        currentTemperature = io.getTemperature();

                        mu = 0.0478 * dt;
                        ml = dt * 9.52 * Math.pow(10, -2);

                        if(currentTemperature <= lowerBound + ml){
                            io.heat(true);
                        } else if(currentTemperature >= upperBound - mu) {
                            io.heat(false);

                            if(sendAck){
                                sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
                                sendAck = false;
                                System.out.println("SendAck = true");
                            }
                        }
                    break;

                    default:
                        break;
                }
                
            }
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
    }
}
