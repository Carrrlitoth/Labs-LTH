import lift.LiftView;

public class monitor2 {
    private int[] toEnter;
    private int[] toExit;
    private int currentFloor;
    private int nextFloor;
    private boolean closed = true;
    private int direction;
    private int walking;
    private int capacity;

    final int NBR_FLOORS = 7, MAX_PASSENGERS = 4;
    LiftView  view = new LiftView(NBR_FLOORS, MAX_PASSENGERS);

    public monitor2() {
        currentFloor = 0;
        toEnter = new int[7];
        toExit = new int[7];
        walking = 0;
        direction = 1;
        capacity = 0;
    }

    public synchronized LiftView getView() {
        return view;
    }
    public synchronized int getFloor() {
        return currentFloor;
    }
    public synchronized void setFloor(int floor) {
        currentFloor = floor;
    }
    private int sum() {
        int sum = 0;
        for(int i=0; i<7; i++) {
            sum += toEnter[i];
            sum += toExit[i];
        }
        return sum;
    }

    public synchronized int requestMove() throws InterruptedException {
        while(sum() == 0) {
            wait();
        }
        if(currentFloor == 6) {
            direction = 0;
        } else if(currentFloor == 0) {
            direction = 1;
        }
        if(direction == 1) {
            nextFloor = currentFloor+1;
        } else {
            nextFloor = currentFloor-1;
        }
        return nextFloor;
    }

    public synchronized boolean floorCheck() {
        boolean check = true;
        if((toEnter[currentFloor] == 0 || capacity == 4) && toExit[currentFloor] == 0) {
            check = false;
        }
        return check;
    }

    public synchronized void requestOpen() {
        closed = false;
        notifyAll();
    }

    public synchronized void requestClose() throws InterruptedException {
        while(toEnter[currentFloor] > 0 && capacity < 4 || toExit[currentFloor] > 0 || walking > 0) {
            wait();
        }
        closed = true;
        notifyAll();
    }

    public synchronized void walking() {
        toExit[currentFloor]--;
        walking--;
        capacity--;
        notifyAll();
    }

    public synchronized void requestFloor(int fromFloor) throws InterruptedException {
        toEnter[fromFloor]++;
        notifyAll();
        while(closed || currentFloor != fromFloor || capacity >= 4) {
            wait();
        }
        walking++;
        capacity++;
    }

    public synchronized void requestExit(int toFloor) throws InterruptedException {
        toEnter[currentFloor]--;
        toExit[toFloor]++;
        walking--;
        notifyAll();
        while(closed || currentFloor != toFloor) {
            wait();
        }
        walking++;
    }

}
