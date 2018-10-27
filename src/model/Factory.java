package model;
import util.QueueException;

public class Factory {

	
	public static void main(String[] args) throws QueueException
	{
		PEPS p = new PEPS(3, 5);
		p.buy(7, 3);
		p.buy(5, 10);
		System.out.println("#Purchases= "+p.getNum_purchases()+". Purchases= "+p.getPurchases());
		p.sell(2, 7);
		p.sell(13, 10);
		System.out.println("#Sales= "+p.getNum_sales()+". Cost sales= "+p.getCost_sales()+". sales="+p.getSales());
	}
}
