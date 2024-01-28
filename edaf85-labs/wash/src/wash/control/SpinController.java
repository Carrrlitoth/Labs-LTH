package wash.control;

import actor.ActorThread;
import wash.control.WashingMessage.Order;
import wash.io.WashingIO;
import wash.io.WashingIO.Spin;

public class SpinController extends ActorThread<WashingMessage> {

    // TODO: add attributes
    private WashingIO io;
    private Spin spin;
    private ActorThread<WashingMessage> sender;

    public SpinController(WashingIO io) {
        this.io = io;
        spin = Spin.IDLE;
    }

    @Override
    public void run() {

        // this is to demonstrate how to control the barrel spin:
        io.setSpinMode(Spin.IDLE);

        try {

            // ... TODO ...

            while (true) {
                // wait for up to a (simulated) minute for a WashingMessage
                WashingMessage m = receiveWithTimeout(60000 / Settings.SPEEDUP);

                // if m is null, it means a minute passed and no message was received
                if (m != null) {
                    System.out.println("got " + m);

                    sender = m.sender();
                    Order order = m.order();
                    
                    switch(order){
    
                        //When SpinController receives a SPIN_SLOW message, the barrel should alternate between slow
                        //left rotation and slow right rotation, changing direction every minute.
                        case SPIN_SLOW:
                            spin = Spin.RIGHT;
                            break;


                        case SPIN_FAST:
                            spin = Spin.FAST;
                            break;
                        
                        
                        case SPIN_OFF:
                            spin = Spin.IDLE;
                            break;
    
                        default:
                            break;
    
                    }
                }

                // ... TODO ...

                if(spin == Spin.LEFT) spin = Spin.RIGHT;
                else if(spin == Spin.RIGHT) spin = Spin.LEFT;

                io.setSpinMode(spin);

                if(m != null) sender.send(new WashingMessage(this, Order.ACKNOWLEDGMENT));
            }
        } catch (InterruptedException unexpected) {
            // we don't expect this thread to be interrupted,
            // so throw an error if it happens anyway
            throw new Error(unexpected);
        }
    }
}
