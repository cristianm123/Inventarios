package model;

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
	
	public double getCostSales()
	{
		double ret = 0;
		if(peps!=null)
		{
			ret = peps.getCost_sales();
		}
		else if(pp != null)
			ret = pp.getSaleCost();
		return ret;
	}
}
