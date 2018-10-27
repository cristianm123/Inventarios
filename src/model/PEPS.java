package model;
import util.Pair;
import util.Queue;
import util.QueueException;

public class PEPS {
	private Queue<Pair<Double, Integer>> inventory;
	private double cost_sales;
	private double purchases;
	private double sales;
	private double purchases_returned;
	private double sales_returned;
	private int num_sales;
	private int num_purchases;
	private int num_purchases_returned;
	private int num_sales_returned;
	
	public PEPS(int purch, double price)
	{
		inventory = new Queue<>();
		inventory.enqueue(new Pair<Double, Integer>(price, purch));
		cost_sales = 0;
		purchases = 0;
		num_purchases = 0;
		num_sales = 0;
		sales = 0;
	}
	
	public void sell(int units, double price) throws QueueException
	{
		int sold = 0;
		while(sold<units)
		{
			if(inventory.front().getValue()<(units-sold))
			{
				sold+=inventory.front().getValue();
				num_sales+=inventory.front().getValue();
				cost_sales+=(inventory.front().getValue()*inventory.front().getKey());
				inventory.dequeue();
				
			}
			else if(inventory.front().getValue()>(units-sold))
			{
				num_sales+=(units-sold);
				cost_sales+=(units-sold)*inventory.front().getKey();
				inventory.front().setValue(inventory.front().getValue()-(units-sold));
				sold=units;
			}
			else
			{
				num_sales += inventory.front().getValue();
				cost_sales+=(inventory.front().getKey()*inventory.front().getValue());
				inventory.dequeue();
				sold=units;
			}
		}
		sales+=units*price;
	}
	
	public void buy(int units, double price)
	{
		inventory.enqueue(new Pair<Double, Integer>(price, units));
		num_purchases+=units;
		purchases+=(units*price);
	}
	
	public void returnPurchase(int units) throws QueueException
	{
		int returned = 0;
		while(returned<units)
		{
			if(inventory.front().getValue()<(units-returned))
			{
				returned+=inventory.front().getValue();
				num_purchases_returned+=inventory.front().getValue();
				purchases_returned+=(inventory.front().getValue()*inventory.front().getKey());
				inventory.dequeue();
				
			}
			else if(inventory.front().getValue()>(units-returned))
			{
				num_purchases_returned+=(units-returned);
				purchases_returned+=(units-returned)*inventory.front().getKey();
				inventory.front().setValue(inventory.front().getValue()-(units-returned));
				returned=units;
			}
			else
			{
				num_purchases_returned += inventory.front().getValue();
				purchases_returned+=(inventory.front().getKey()*inventory.front().getValue());
				inventory.dequeue();
				returned=units;
			}
		}
	}
	
	public void returnSale(int units)
	{
		if(inventory.isEmpty())
		{
			
		}
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
}
