package util;

public class Stack<T> implements IStack<T>{

	private Object[] array;
	private int size;
	public Stack() {
		array = new Object[10];
		size = 0;
	}
	@Override
	public void push(T o)
	{
		if(size==array.length)
		{
			Object[] a = new Object[size+10];
			for (int i = 0; i < array.length; i++) {
				a[i] = array[i];
			}
			array = a;
		}
		array[size] = (Object)o;
		size++;
	}
	@Override
	public void pop() throws IndexOutOfBoundsException
	{
		if(!isEmpty())
		{
			array[size-1] = null;
			size--;
		}
		else
		{
			throw new IndexOutOfBoundsException();
		}
	}
	@Override
	public boolean isEmpty()
	{
		return size==0;
	}
	@Override
	public T top()
	{
		return (T)array[size-1];
	}
	@Override
	public int size()
	{
		return size;
	}
}
