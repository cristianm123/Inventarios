package model;

public class Factory {

	private PEPS peps;
	
	public Factory(int units, double price)
	{
		peps = new PEPS(units, price);
	}
	
	public PEPS getPEPS()
	{
		return peps;
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
