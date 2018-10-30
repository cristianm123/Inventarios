package model;
import util.Pair;
import util.Queue;
import util.QueueException;
import util.Stack;

public class PEPS {
	private Queue<Pair<Double, Integer>> inventory;
	private Stack<Pair<Double, Integer>> stack;
	private double cost_sales, purchases, sales, purchases_returned, 
	sales_returned, last_price, initial_inventory;
	private int num_sales, num_purchases, num_purchases_returned, num_sales_returned, initial_units;
	
	public PEPS(int purch, double price) 
	{
		inventory = new Queue<>();
		inventory.enqueue(new Pair<Double, Integer>(price, purch));
		cost_sales = 0;
		purchases = 0;
		num_purchases = 0;
		num_sales = 0;
		sales = 0;
		last_price = 0;
		initial_inventory = price*purch;
		initial_units = purch;
		stack = new Stack<>();
	}
	
	public Queue<Pair<Double, Integer>> sell(int units, double price) throws QueueException
	{
		if(initial_units+num_purchases+num_sales_returned-num_sales-num_purchases_returned<units)
		{
			throw new QueueException("No hay suficientes unidades para vender");
		}
		int sold = 0;
		Queue<Pair<Double, Integer>> sale = new Queue<>();
		while(sold<units)
		{
			if(inventory.front().getValue()<(units-sold))
			{
				sold+=inventory.front().getValue();
				num_sales+=inventory.front().getValue();
				cost_sales+=(inventory.front().getValue()*inventory.front().getKey());
				stack.push(inventory.front());
				sale.enqueue(inventory.dequeue());
				
			}
			else if(inventory.front().getValue()>(units-sold))
			{
				num_sales+=(units-sold);
				cost_sales+=(units-sold)*inventory.front().getKey();
				inventory.front().setValue(inventory.front().getValue()-(units-sold));
				sale.enqueue(new Pair<Double, Integer>(inventory.front().getKey(), units-sold));
				stack.push(new Pair<Double, Integer>(inventory.front().getKey(), units-sold));
				sold=units;
			}
			else
			{
				num_sales += inventory.front().getValue();
				cost_sales+=(inventory.front().getKey()*inventory.front().getValue());
				stack.push(inventory.front());
				sale.enqueue(inventory.dequeue());
				sold=units;
			}
		}
		
		sales+=units*price;
		last_price = price;
		return sale;
	}
	
	public void buy(int units, double price)
	{
		inventory.enqueue(new Pair<Double, Integer>(price, units));
		num_purchases+=units;
		purchases+=(units*price);
	}
	
	public Queue<Pair<Double, Integer>> returnPurchase(int units) throws QueueException
	{
		if(num_purchases+num_sales_returned-num_sales-num_purchases_returned<units)
		{
			throw new QueueException("No hay suficientes unidades para devolver");
		}
		int returned = 0;
		Stack<Pair<Double, Integer>> stack = new Stack<>();
		Queue<Pair<Double, Integer>> queue = new Queue<>();
		while(!inventory.isEmpty())
		{
			stack.push(inventory.dequeue());
		}
		while(!stack.isEmpty())
		{
			inventory.enqueue(stack.top());
			stack.pop();
		}
		while(returned<units)
		{
			if(inventory.front().getValue()<(units-returned))
			{
				returned+=inventory.front().getValue();
				num_purchases_returned+=inventory.front().getValue();
				purchases_returned+=(inventory.front().getValue()*inventory.front().getKey());
				purchases-=inventory.front().getValue()*inventory.front().getKey();
				queue.enqueue(inventory.dequeue());
				
			}
			else if(inventory.front().getValue()>(units-returned))
			{
				num_purchases_returned+=(units-returned);
				purchases_returned+=(units-returned)*inventory.front().getKey();
				purchases-=(units-returned)*inventory.front().getKey();
				inventory.front().setValue(inventory.front().getValue()-(units-returned));
				queue.enqueue(new Pair<Double, Integer>(inventory.front().getKey(), units-returned));
				returned=units;
			}
			else
			{
				num_purchases_returned += inventory.front().getValue();
				purchases_returned+=(inventory.front().getKey()*inventory.front().getValue());
				purchases-=inventory.front().getKey()*inventory.front().getValue();
				queue.enqueue(inventory.dequeue());
				returned=units;
				
			}
		}
		return queue;
	}
	
	public void returnSale(int units) throws QueueException
	{
		if(num_sales<units)
		{
			throw new QueueException("No hay suficientes unidades para devolver");
		}
		int returned = 0;
		while(returned<units)
		{
			if(stack.top().getValue()<(units-returned))
			{
				returned+=stack.top().getValue();
				num_sales_returned+=stack.top().getValue();
				sales_returned+=(stack.top().getValue()*last_price);
				cost_sales-=stack.top().getValue()*stack.top().getKey();
				Stack<Pair<Double, Integer>> st = new Stack<>();
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
				inventory.enqueue(stack.top());
				stack.pop();
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
			}
			else if(stack.top().getValue()>(units-returned))
			{
				num_sales_returned+=(units-returned);
				sales_returned+=(units-returned)*last_price;
				cost_sales-=(units-returned)*stack.top().getKey();
				stack.top().setValue(stack.top().getValue()-(units-returned));
				Stack<Pair<Double, Integer>> st = new Stack<>();
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
				inventory.enqueue(new Pair<Double, Integer>(stack.top().getKey(), units-returned));
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
				returned=units;
			}
			else
			{
				num_sales_returned += stack.top().getValue();
				sales_returned+=(stack.top().getKey()*stack.top().getValue());
				cost_sales-=stack.top().getKey()*stack.top().getValue();
				Stack<Pair<Double, Integer>> st = new Stack<>();
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
				inventory.enqueue(stack.top());
				stack.pop();
				while(!inventory.isEmpty())
				{
					st.push(inventory.dequeue());
				}
				while(!st.isEmpty())
				{
					inventory.enqueue(st.top());
					st.pop();
				}
				returned=units;
			}
		}
		sales-=(units*last_price);
	}

	public double getFinalInventory() throws QueueException
	{
		double a = 0;
		Queue<Pair<Double, Integer>> j = new Queue<>();
		while(!inventory.isEmpty())
		{
			a+=(inventory.front().getKey()*inventory.front().getValue());
			j.enqueue(inventory.dequeue());
		}
		inventory = j;
		return a;
	}
	
	public Queue<Pair<Double, Integer>> getInventory()
	{
		return inventory;
	}
	
	public double getCost_sales() {
		return cost_sales;
	}

	public double getPurchases() {
		return purchases;
	}

	public double getSales() {
		return sales;
	}

	public int getNum_sales() {
		return num_sales;
	}

	public int getNum_purchases() {
		return num_purchases;
	}
	
	public double getInitialInventory()
	{
		return initial_inventory;
	}
	
	public int getInitialUnits()
	{
		return initial_units;
	}

	public int getNum_purchases_returned() {
		return num_purchases_returned;
	}

	public int getNum_sales_returned() {
		return num_sales_returned;
	}
}
