import java.util.LinkedList;

import lift.LiftView;

public class monitor {

    private int[] toEnter;
    private int[] toExit;
    private int currentFloor;
    private boolean moving = false;
    private boolean closed = true;
    private int walking;

    private LinkedList<Integer> queue = new LinkedList<>();

    final int NBR_FLOORS = 7, MAX_PASSENGERS = 4;
    LiftView  view = new LiftView(NBR_FLOORS, MAX_PASSENGERS);

    public monitor() {
        currentFloor = 0;
        toEnter = new int[7];
        toExit = new int[7];
        walking = 0;
    }

    public synchronized LiftView getView() {
        return view;
    }

    public synchronized void requestFloor(int fromFloor) throws InterruptedException {
        toEnter[fromFloor]++;
        queue.addLast(fromFloor);
        notifyAll();
        while(closed) {
            wait();
        }
    }

    public synchronized void requestExit(int toFloor) throws InterruptedException {
        toEnter[currentFloor]--;
        toExit[toFloor]++;
        queue.addLast(toFloor);
        notifyAll();
        while(closed || currentFloor != toFloor) {
            wait();
        }
        walking++;
    }

    public synchronized void walking() {
        toExit[currentFloor]--;
        walking--;
        notifyAll();
    }

    public synchronized int requestMove() throws InterruptedException {
        while(queue.size() == 0 || moving==true) {
            wait();
        }
        moving = true;
        return queue.removeFirst();
    }

    public synchronized void requestOpen() {
        moving = false;
        closed = false;
        notifyAll();
    }

    public synchronized void requestClose() throws InterruptedException {
        while(queue.size() == 0 || moving==true || walking > 0) {
            wait();
        }
        closed = true;
        notifyAll();
    }

    public synchronized int getFloor() {
        return currentFloor;
    }

    public synchronized void setFloor(int floor) {
        currentFloor = floor;
    }
    
}
