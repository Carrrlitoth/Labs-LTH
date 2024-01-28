
import lift.LiftView;
import lift.Passenger;

public class OnePersonRidesLift {

    public static void main(String[] args) {
        monitor2 mon = new monitor2();
        passengers2 pas = new passengers2(mon);
    
    Thread lift1 = new Thread (() -> {
        try {
            while(true){
                int toFloor = mon.requestMove();
                mon.getView().moveLift(mon.getFloor(), toFloor);
                //mon.setFloor(toFloor);
                mon.getView().openDoors(mon.getFloor());
                //mon.requestOpen();
                //mon.requestClose();
                mon.getView().closeDoors();
            }
        } catch (InterruptedException e) {
            throw new Error(e);
        }});

        Thread lift = new Thread (() -> {
            try {
            while(true){
                int toFloor = mon.requestMove();
                mon.getView().moveLift(mon.getFloor(), toFloor);
                mon.setFloor(toFloor);
                if(mon.floorCheck()) {
                    mon.getView().openDoors(mon.getFloor());
                    mon.requestOpen();
                    mon.requestClose();
                    mon.getView().closeDoors();
                }
            }
        } catch (InterruptedException e) {
            throw new Error(e);
        }});
        pas.begin();
        lift.start();
    }

}