package model;
import util.Pair;
import util.Queue;
import util.QueueException;
import util.Stack;

public class PEPS {
	private Queue<Pair<Double, Integer>> inventory;
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
		last_price = price;
		initial_inventory = price*purch;
		initial_units = purch;
	}
	
	public Queue<Pair<Double, Integer>> sell(int units, double price) throws QueueException
	{
		int sold = 0;
		Queue<Pair<Double, Integer>> sale = new Queue<>();
		while(sold<units)
		{
			if(inventory.front().getValue()<(units-sold))
			{
				sold+=inventory.front().getValue();
				num_sales+=inventory.front().getValue();
				cost_sales+=(inventory.front().getValue()*inventory.front().getKey());
				last_price=inventory.front().getKey();
				sale.enqueue(inventory.dequeue());
				
			}
			else if(inventory.front().getValue()>(units-sold))
			{
				num_sales+=(units-sold);
				cost_sales+=(units-sold)*inventory.front().getKey();
				inventory.front().setValue(inventory.front().getValue()-(units-sold));
				sale.enqueue(new Pair<Double, Integer>(inventory.front().getKey(), units-sold));
				last_price=inventory.front().getKey();
				sold=units;
			}
			else
			{
				num_sales += inventory.front().getValue();
				cost_sales+=(inventory.front().getKey()*inventory.front().getValue());
				last_price=inventory.front().getKey();
				sale.enqueue(inventory.dequeue());
				sold=units;
			}
		}
		sales+=units*price;
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
			return queue;
		}
		while(!inventory.isEmpty())
		{
			stack.push(inventory.dequeue());
		}
		while(!stack.isEmpty())
		{
			inventory.enqueue(stack.top());
			stack.pop();
		}
		num_purchases-=units;
		return queue;
	}
	
	public void returnSale(int units, double price) throws QueueException
	{
		if(inventory.isEmpty())
		{
			inventory.enqueue(new Pair<Double, Integer>(last_price, units));
		}
		else
		{
			inventory.enqueue(new Pair<Double, Integer>(last_price, units));
		}
		num_sales_returned+=units;
		num_sales-=units;
		sales_returned+=(inventory.front().getValue()*inventory.front().getKey());
		sales-=(units*price);
		cost_sales-= inventory.front().getValue()*inventory.front().getKey();
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
