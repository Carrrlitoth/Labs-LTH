package testqueue;

import static org.junit.Assert.*;

import java.util.Queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {
    private FifoQueue<Integer> q1;
    private FifoQueue<Integer> q2;

    @Before
    public void setUp() throws Exception {
        q1 = new FifoQueue<Integer>();
        q2 = new FifoQueue<Integer>();
    }

    @After
    public void tearDown() throws Exception {
        q1 = null;
        q2 = null;
    }

    @Test
    public void testEmptyConcat(){
        q1.append(q2);
        assertEquals("Front of queue q1 not null", null, q1.peek());
        assertEquals("Front of queue a2 not null", null, q2.peek());
        assertEquals("Size of q1 is not 0", 0, q1.size());
        assertEquals("Size of q2 is not 0", 0, q2.size());
    }

    @Test
    public void testAppendEmptyToNonEmpty(){
        int n = 10;

        for(int i = 0; i < n; i++){
            q1.offer(i);
        }

        q1.append(q2);

        // Check size of q1
        assertEquals("Size of q1 is not n", n, q1.size());

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals("poll returned unexpected value!", i, j);
        }

        // Check that q2 is empty
        assertEquals("Peek of q2 is not null", null, q2.peek());
        assertEquals("Size of q2 is not 0", 0, q2.size());
    }

    @Test
    public void testAppendNonEmptyToEmpty(){
        int n = 10;

        for(int i = 0; i < n; i++){
            q2.offer(i);
        }

        q1.append(q2);

        // Check size of q1
        assertEquals("Size of q1 is not n", n, q1.size());

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals("poll returned unexpected value!", i, j);
        }

        // Check that q2 is empty
        assertEquals("Peek of q2 is not null", null, q2.peek());
        assertEquals("Size of q2 is not 0", 0, q2.size());
    }

    @Test
    public void testNonEmptyQueues(){
        int n = 10;

        for(int i = 0; i < n; i++){
            if(i < n/2){
                q1.offer(i);
            }else{
                q2.offer(i);
            }
        }

        q1.append(q2);

        // Check size of q1
        assertEquals("Size of q1 is not n", n, q1.size());

        // Check order
        for(int i = 0; i < n; i++){
            int j = q1.poll();
            assertEquals("poll returned unexpected value!", i, j);
        }

        // Check that q2 is empty
        assertEquals("Peek of q2 is not null", null, q2.peek());
        assertEquals("Size of q2 is not 0", 0, q2.size());
    }

    @Test
    public void testSelfConcat(){
        try{
            q1.append(q1);
            fail("Should raise IllegalArgumentException");
        } catch(IllegalArgumentException e){
        }
    }

}