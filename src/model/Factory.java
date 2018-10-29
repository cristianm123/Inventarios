package model;

import util.QueueException;

public class Factory {

	public static final int PEPS = 0;
	public static final int PROM = 1;
	private PEPS peps;
	private PP pp;
	public Factory(int units, double price, int method)
	{
		if(method==PEPS)
		{
			peps = new PEPS(units, price);
		}
		else if(method==PROM)
		{
			pp = new PP(units, price);
		}
	}
	
	
	public PEPS getPEPS()
	{
		return peps;
	}
	
	public PP getPP()
	{
		return pp;
	}
	
	//añadir el caso en el que se use pp
	public double getSales()
	{
		double ret = 0;
		if(peps!=null)
		{
			ret = peps.getSales();
		} 
		else if(pp != null)
			ret = pp.getSaleValue();
		return ret;
	}
	//añadir el caso en el que se use pp
	public double getCostSales()
	{
		double ret = 0;
		if(peps!=null)
		{
			ret = peps.getCost_sales();
		}
		else if(pp != null)
			ret = pp.getContSales();
		return ret;
	}
//	public static void main(String[] args) throws QueueException
//	{
//		PEPS p = new PEPS(50, 20);
//		p.buy(300, 22);
//		p.buy(250, 25);
//		p.sell(400, 60);
//		p.buy(500, 24);
//		p.sell(350, 60);
//		p.returnPurchase(80);
//		p.sell(240, 60);
//		p.returnSale(30, 60);
//		
//		System.out.println("#Purchases= "+p.getNum_purchases()+". Purchases= "+p.getPurchases());
//		System.out.println("#Sales= "+p.getNum_sales()+". Cost sales= "+p.getCost_sales()+". sales="+p.getSales());
//		System.out.println("i0 = " + 50*20);
//		System.out.println("compras = " + p.getPurchases());
//		System.out.println("if = " + p.getFinalInventory());
//		System.out.println(p.getInitialInventory()+p.getPurchases()-p.getFinalInventory());
//	}
}
