package actor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ActorThread<M> extends Thread {

    private LinkedBlockingQueue<M> queue = new LinkedBlockingQueue<>();

    /** Called by another thread, to send a message to this thread. */
    public void send(M message) {
        try {
            queue.put(message);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("Something wrong in code");
        }
    }
    
    /** Returns the first message in the queue, or blocks if none available. */
    protected M receive() throws InterruptedException {
        return queue.take();
    }
    
    /** Returns the first message in the queue, or blocks up to 'timeout'
        milliseconds if none available. Returns null if no message is obtained
        within 'timeout' milliseconds. */
    protected M receiveWithTimeout(long timeout) throws InterruptedException {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }
}