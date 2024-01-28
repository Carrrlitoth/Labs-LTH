import lift.LiftView;
import lift.Passenger;

public class passengers {
    private monitor mon;

    public passengers(monitor view) {
        mon = view;
    }
    
    Thread passenger1 = new Thread (() -> {
        try {
            Passenger pass = mon.getView().createPassenger();
            pass.begin();
            int fromFloor = pass.getStartFloor();
            mon.requestFloor(fromFloor);
            pass.enterLift();
            int toFloor = pass.getDestinationFloor();
            mon.requestExit(toFloor);
            pass.exitLift();
            mon.walking();
            pass.end();
            } catch (InterruptedException e) {
            throw new Error(e);
        }});
    
    Thread passenger2 = new Thread (() -> {
        try {
            Passenger pass = mon.getView().createPassenger();
            pass.begin();
            int fromFloor = pass.getStartFloor();
            mon.requestFloor(fromFloor);
            pass.enterLift();
            int toFloor = pass.getDestinationFloor();
            mon.requestExit(toFloor);
            pass.exitLift();
            mon.walking();
            pass.end();
            } catch (InterruptedException e) {
            throw new Error(e);
        }
    });

    Thread passenger3 = new Thread (() -> {
        Passenger pass = mon.getView().createPassenger();
        pass.begin();
        
    });

    Thread passenger4 = new Thread (() -> {
        Passenger pass = mon.getView().createPassenger();
        pass.begin();
        
    });

    Thread passenger5 = new Thread (() -> {
        Passenger pass = mon.getView().createPassenger();
        pass.begin();
        
    });

    public void begin() {
        passenger1.start();
        passenger2.start();
        //passenger3.start();
        //passenger4.start();
        //passenger5.start();
    }
}
