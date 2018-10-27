package util;

public class Node<E> {
	
	private Node<E> next;
	private Object item;
	
	public Node(Object item) {
		this.item = item;
	}
	
	public Node<E> getNext() {
		return next;
	}
	public void setNext(Node<E> next) {
		this.next = next;
	}
	public Object getItem() {
		return item;
	}
	public void setItem(Object item) {
		this.item = item;
	}
	
	
	
	
}
