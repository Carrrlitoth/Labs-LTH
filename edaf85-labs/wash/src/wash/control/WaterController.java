package wash.control;

import actor.ActorThread;
import wash.control.WashingMessage.Order;
import wash.io.WashingIO;

public class WaterController extends ActorThread<WashingMessage> {

    // TODO: add attributes

    private WashingIO io;
    private ActorThread<WashingMessage> sender;
    private State state;
    private enum State {FILLING, DRANING, IDLE};

    public WaterController(WashingIO io) {
        // TODO
        this.io = io;
        state = State.IDLE;
    }

    @Override
    public void run() {
        // TODO
        try {
            while (true) {
                WashingMessage m = receiveWithTimeout(1000 / Settings.SPEEDUP);

                if (m != null) {
                    
                    System.out.println("System got " + m);

                    sender = m.sender();
                    Order order = m.order();

                    switch (order) {
                        case WATER_FILL:
                            state = State.FILLING;
                            break;

                        case WATER_DRAIN:
                            state = State.DRANING;
                            break;

                        case WATER_IDLE:
                            state = State.IDLE;
                            io.fill(false);
                            sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
                            break;

                        default:
                            break;
                    }


                    
                }

                switch(state){

                    case FILLING:
                        
                        io.drain(false);
                        
                        if(io.getWaterLevel() < 10){
                            io.fill(true);
                        } else {
                            io.fill(false);
                            sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
                            state = State.IDLE;
                        }
                    break;

                    case DRANING:
                        if(io.getWaterLevel() > 0){
                            io.drain(true);
                        } else {
                            sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
                            state = State.IDLE;
                        }
                    break;

                    default:
                        break;

                }

            }
        } catch (InterruptedException unexpected) {
            throw new Error(unexpected);
        }
    }
}
