package actor.test;

import actor.ActorThread;

/**
 * This test was introduced to detect possible errors in the implementation
 * of ActorThread.receiveWithTimeout().
 * 
 * So if this test is the only one that fails, review your
 * receiveWithTimeout() implementation. It must not remove more than one
 * message from the queue.
 */
public class ExampleReceiveWithTimeoutKeepsMessagesInOrder {
    
    public static void main(String[] args) throws InterruptedException {

        ActorThread<String> c = new ActorThread<String>() {
            @Override
            public void run() {
                try {
                    System.out.println("consumer eagerly awaiting messages...");
                    for (int k = 0; k < 4; k++) {
                        String s = receiveWithTimeout(100);
                        System.out.println("received [" + s + "]");
                    }
                } catch (InterruptedException e) {
                    // not expected to happen
                    throw new Error(e);
                }
            }
        };

        c.send("yxi");
        c.send("kaxi");
        c.send("kolme");

        c.start();
        c.join();
        System.out.println("all done");
    }
}
