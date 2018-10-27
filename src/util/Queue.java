package util;

public class Queue<E> implements IQueue<E>{
	
	private Node<E> first;
	private Node<E> last;

	public Queue() {
		first = null;
		last = null;
	}
	
	public boolean isEmpty() {
		return last == null;
	}
	
	public void enqueue(E item) {
		Node newNode = new Node(item);
		if (isEmpty( )) { 
		first = newNode;
		last = newNode;
		}
		else {last.setNext(newNode);} 
		last = newNode;
	}
	
	public E dequeue( ) throws QueueException{
		if (!isEmpty( )) {
			Node firstNode = first;
		if (first == last) {last = null;
				first = null;}
		else{first = first.getNext( );}
		return (E)firstNode.getItem( ); }
		else{throw new QueueException("QueueException on dequeue: empty");}
		}
	
	public E front( ) throws QueueException {
		if (!isEmpty( )) {
		// queue is not empty. Retrieve the front item;
		return (E)first.getItem( );
		}
		else {throw new QueueException("QueueException on front: queue empty");}
		}

	public Node<E> getLast() {
		return last;
	}
	
	
}
