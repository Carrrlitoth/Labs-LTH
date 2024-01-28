package queue_singlelinkedlist;
import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**	
	 * Inserts the specified element into this queue, if possible
	 * post:	The specified element is added to the rear of this queue
	 * @param	e the element to insert
	 * @return	true if it was possible to add the element 
	 * 			to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode<E> q = new QueueNode<>(e);   	//skapar den nya noden som ska sättas in 
		if(last == null) {						//pekar på sig själv när endast 1 nod i listan
			q.next = q; 						
		} else {								//q är nya sista elementet så då vill vi att next ska peka på det första
			q.next = last.next;					//last.next är gamla sista då ska den peka på q
			last.next = q;

		}
		last = q; 								//sätter att last blir q som är det senaste noden i listan
		size++; 								//öka storleken
		return true;
	}
	
	/**	
	 * Returns the number of elements in this queue
	 * @return the number of elements in this queue
	 */
	public int size() {		
		return size;
	}
	
	/**	
	 * Retrieves, but does not remove, the head of this queue, 
	 * returning null if this queue is empty
	 * @return 	the head element of this queue, or null 
	 * 			if this queue is empty
	 */
	public E peek() {
		if(last == null) {
			return null;
		}
		return last.next.element;
	}

	/**	
	 * Retrieves and removes the head of this queue, 
	 * or null if this queue is empty.
	 * post:	the head of the queue is removed if it was not empty
	 * @return 	the head of this queue, or null if the queue is empty 
	 */
	public E poll() {
	    E element = null; 					//används som en hjälp variabel för att ta ut rätt element
	    if (last != null) {
	        element = last.next.element;  	//elementet sätts till elementet i first

	        if (last.next == last) { 		//om det enbart är 1 nod i listan så ska noden nollställas när listan ska bli tom
	            last = null;
            } else {
	            last.next = last.next.next;	//om inte så länkar man last.next till det andra objektet i listan så att det första klipps
            }

            size--;							//minska storleken på listan
        }

		return element;
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}
	
	/**	
	 * Returns an iterator over the elements in this queue
	 * @return an iterator over the elements in this queue
	 */	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E>{

		private QueueNode<E> pos; 						//hjälp nod för att hållla reda på positionen

		private QueueIterator(){
			if(last != null) {							//checkar om listan är tom, om inte sätts pos till first
				pos = last.next;
			}
			else {
				pos = null;
			}
				
		}

		public boolean hasNext(){						//snabb check för att se om listan blivit tom
			return pos != null;
		}

		public E next(){
			if (hasNext()) {							//om det finns noder kvar så sätter vi elementet till pos elementet
	            E element = pos.element;
	            if (pos != last) {						//om pos ej är sista noden i listan hoppar den till nästa annars nollställs
	                pos = pos.next;
                } else {
	                pos = null;
                }
                return element;
            } else {
	            throw new NoSuchElementException();		//ifall hasNext är tom ska inget göras
            }
		}
		
	}

		public void append(FifoQueue<E> q) {
			if (q != this) {								//checka att man inte försöker appenda samma kö som redan arbetas med
				if (q.last != null) {						//checka så listan ej är tom
					QueueNode<E> last_node = q.last;		//temp var för att överföra q till this
					QueueNode<E> first_node = q.last.next;	//--||--
	
					if (last != null) {						//länka ihop de sista och första elementen
						last_node.next = last.next;
						last.next = first_node;
					}
	
					last = last_node; //  Pekar till nya list enden
					size += q.size; // Uppdaterar storleken
	
					// nollställter q
					q.last = null;
					q.size = 0;
				}
			} else {
				throw new IllegalArgumentException();
			}
		
	}

}
