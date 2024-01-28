package testqueue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import queue_delegate.FifoQueue;
import queue_singlelinkedlist.FifoQueue;

import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Iterator;

class TestFifoQueue {
	private Queue<Integer> myIntQueue;
	private Queue<String> myStringQueue;

	@BeforeEach
	void setUp() {
		myIntQueue = new FifoQueue<Integer>();
		myStringQueue = new FifoQueue<String>();
	}

	@AfterEach
	void tearDown(){
		myIntQueue = null;
		myStringQueue = null;
	}

	/**
	 * Test if a newly created queue is empty.
	 */
	@Test
	void testNewFifoQueue() {
		assertTrue(myIntQueue.isEmpty(), "Wrong result from empty of queue");
		assertEquals(0, myIntQueue.size(), "Wrong size of empty queue");
	}

	/** Test a single offer followed by a single peek. */
	@Test
	void testPeek() {
		myIntQueue.offer(1);
		int i = myIntQueue.peek();
		assertEquals(1, i, "peek on queue of size 1");
		assertEquals(1, myIntQueue.size(), "peek on queue of size 1");
	}

	/**
	 * Test a single offer followed by a single poll.
	 */
	@Test
	void testPoll() {
		myIntQueue.offer(1);
		int i = myIntQueue.poll();
		assertEquals(1, i, "poll on queue of size 1");
		assertEquals(0, myIntQueue.size(), "Wrong size after poll");
	}

	/**
	 * Test peek of empty queue.
	 */
	@Test
	void testPeekOfEmpty() {
		assertEquals(null, myIntQueue.peek(), "Front of empty queue not null");
	}

	/**
	 * Test poll of empty queue.
	 */
	@Test
	void testPollOfEmpty() {
		assertEquals(null, myIntQueue.poll(), "Poll of empty queue should return null") ;
	}

	/**
	 * Test that implementation works for a queue of strings.
	 */
	@Test
	void testStringQueue() {
		myStringQueue.offer("First");
		myStringQueue.offer("Second");
		myStringQueue.offer("Third");
		assertEquals(3, myStringQueue.size(), "Wrong size of queue");
		assertEquals("First", myStringQueue.peek(), "peek on queue of strings");
		assertEquals("First", myStringQueue.poll(), "String First expected");
		assertEquals("Second", myStringQueue.poll(), "String Second expected");
		assertEquals("Third", myStringQueue.poll(), "String Third expected");
		assertTrue(myStringQueue.isEmpty(), "Queue of strings should be empty");
	}

	/**
	 * Test that polling gives elements in right order.
	 */
	@Test
	void testOrder() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		myIntQueue.offer(5);
		for (int i = 1; i <= 5; i++) {
			int k = myIntQueue.poll();
			assertEquals(i, k, "poll returns incorrect element");
		}
		assertTrue(myIntQueue.isEmpty(), "Queue not empty");
	}

	/**
	 * Test that polling all elements gives an empty queue.
	 */
	@Test
	void testMakeQueueEmpty() {
		myIntQueue.offer(1);
		myIntQueue.offer(2);
		myIntQueue.poll();
		myIntQueue.poll();
		assertEquals(0,  myIntQueue.size(), "Wrong size after poll");
		assertTrue(myIntQueue.isEmpty(), "Queue not empty after poll");
		myIntQueue.offer(3);
		myIntQueue.offer(4);
		assertEquals(2, myIntQueue.size(), "Wrong size after offer");
		for (int i = 3; i <= 4; i++) {
			int k = myIntQueue.poll();
			assertEquals(i, k, "poll returns incorrect element");
		}
		assertEquals(0, myIntQueue.size(), "Wrong size after poll");
		assertTrue(myIntQueue.isEmpty(), "Queue not empty after poll");
	}

	/**
	 * Test iterator on empty queue.
	 */
	@Test
	void testIteratorEmptyQueue() {
		Iterator<Integer> itr = myIntQueue.iterator();
		assertFalse(itr.hasNext(), "Wrong result from next in empty queue");
		assertThrows(NoSuchElementException.class, () -> itr.next());
	}

	/**
	 * Test iterator on non empty queue.
	 */
	@Test
	void testIteratorNonEmptyQueue() {
		int nbr = 5;
		for (int i = 1; i <= nbr; i++) {
			myIntQueue.offer(i);
		}
		Iterator<Integer> itr = myIntQueue.iterator();
		assertTrue(itr.hasNext(), "Wrong result from hasNext");
		for (int i = 1; i <= nbr; i++) {
			assertTrue(itr.hasNext(), "Wrong result from hasNext");
			assertEquals(Integer.valueOf(i), itr.next(), "Wrong result from next");
		}
		assertFalse(itr.hasNext(), "Wrong result from hasNext");
		assertThrows(NoSuchElementException.class, () -> itr.next());
	}

	/**
	 * Test iterator multiple times, to ensure that the iterator does not modify the
	 * queue.
	 */
	@Test
	void testThreeIterators() {
		int nbr = 5;
		for (int i = 1; i <= nbr; i++) {
			myIntQueue.offer(i);
		}

		// first, set up two iterators at the same time, and check that they both work

		Iterator<Integer> itr1 = myIntQueue.iterator();
		Iterator<Integer> itr2 = myIntQueue.iterator();
		
		for (int i = 1; i <= nbr; i++) {
			assertTrue(itr1.hasNext(), "Wrong result from itr1.hasNext");
			assertEquals(Integer.valueOf(i), itr1.next(), "Wrong result from itr1.next");
		}
		
		for (int i = 1; i <= nbr; i++) {
			assertEquals(Integer.valueOf(i), itr2.next(), "Wrong result from itr2.next");
		}

		// then, test a third iterator after the previous iterations
		
		Iterator<Integer> itr3 = myIntQueue.iterator();
		for (int i = 1; i <= nbr; i++) {
			assertTrue(itr3.hasNext(), "Wrong result from itr3.hasNext");
			assertEquals(Integer.valueOf(i), itr3.next(), "Wrong result from itr3.next");
		}
	}
}
