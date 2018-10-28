package model;
import java.util.*;
import java.util.Stack;

import util.*;


public class PP {
	
	private Stack<Pair<Double,Integer>> doitSales;
	private int contSales; //Cantidad de unidades vendidas
	private List<Pair<Double, Integer >> inventory;
	private Stack<Pair<Double, Double>> lastPrice; // Precios de Venta(No de Costo) de las ventas
	private double saleCost; // Costo de venta
	private double saleValue; // Valor de ventas
	private double saldo ;
 	private double pp; //Precio unitario Ponderado
	
	
	public PP(int purch, double price)
	{
		doitSales = new Stack<>();
		contSales = 0;
		inventory = new ArrayList<>();
		inventory.add(new Pair<Double, Integer>(price, purch));
		lastPrice = new Stack<>();
		saleCost = 0;
		saleValue = 0;
		saldo = price*purch;
		pp = price;
	}
	
	
	public int calculateElementsInventory() {
		double units = 0;
		for (int i = 0; i < inventory.size(); i++) {
			units += inventory.get(i).getValue();	
		}
		
		return (int) units;
	}
	

	// En el pairs el value es la cantidad de unidades y la key es el precio unitario
	public void sell(int units, double price) throws NoSuchElementsExceptions
	{
		if(calculateElementsInventory() < units)
			throw new NoSuchElementsExceptions();
		
		int sold = 0;
		while(sold!=units) {
			if(inventory.get(0).getValue()<(units - sold)) {
				contSales +=inventory.get(0).getValue();
				saleValue += inventory.get(0).getValue()*price;
				saleCost -= inventory.get(0).getValue()*pp;
				saldo -= inventory.get(0).getValue()*pp;
				sold +=inventory.get(0).getValue();
				doitSales.push(inventory.remove(0));
				lastPrice.push(new Pair<Double, Double>(price, pp));
				
			}
			else if(inventory.get(0).getValue()>(units - sold)){
				contSales += (units - sold);
				doitSales.push(new Pair<Double, Integer>(inventory.get(0).getKey(), (units -sold)));
				saleValue += inventory.get(0).getValue()*price;
				lastPrice.push(new Pair<Double, Double>(price, pp));
				saleCost += (units -sold)*pp;
				saldo -= (units - sold)*pp;
				inventory.get(0).setValue(inventory.get(0).getValue() - (units - sold));
				
				sold += (units -sold);
			}
			else {
				contSales +=inventory.get(0).getValue();
				saleValue += inventory.get(0).getValue()*price;
				saleCost += inventory.get(0).getValue()*pp;
				saldo -= inventory.get(0).getValue()*pp;
				sold +=inventory.get(0).getValue();
				doitSales.push(inventory.remove(0));
				lastPrice.push(new Pair<Double, Double>(price, pp));
			}
		}
	}
	
	public void buy(int units, double price)
	{
		
		inventory.add(new Pair<Double, Integer>(price, units));
		saldo = saldo + (units*price);
		pp = saldo/calculateElementsInventory();
	}
	
	public void returnPurchase(int units) throws NoSuchElementsExceptions
	{
		if(calculateElementsInventory() < units)
			throw new NoSuchElementsExceptions();
		
		int ret = 0;
		while(ret!=units) {
			if(inventory.get(inventory.size()-1).getValue()<(units - ret)) {
				
				saldo -= inventory.get(inventory.size()-1).getValue() * inventory.get(inventory.size()-1).getKey();
				inventory.remove(inventory.size()-1);
				
				ret +=inventory.get(inventory.size()-1).getValue();
			}
			else if(inventory.get(inventory.size()-1).getValue()>(units - ret)){
				saldo -= (units - ret) * inventory.get(inventory.size()-1).getKey();
				inventory.get(inventory.size()-1).setValue(inventory.get(inventory.size()-1).getValue() - (units - ret));
				
				ret += units -ret;
			}
			else {
				
				saldo -= inventory.get(inventory.size()-1).getValue()*inventory.get(inventory.size()-1).getKey();;
				inventory.remove(inventory.size()-1);
				
				ret +=inventory.get(inventory.size()-1).getValue();
			}
		}
		
		pp = saldo / calculateElementsInventory();
	}
	 // Last Price : Key: Price ; Value: pp
	public void returnSale(int units) throws NoSuchElementsExceptions{
		
		if(units > contSales)
			throw new NoSuchElementsExceptions();
		int ret = 0;
		while(ret!=units) {
			if(doitSales.peek().getValue()<=(units - ret)) {
				contSales -= doitSales.peek().getValue();			
				saleCost -= (doitSales.peek().getValue() * lastPrice.peek().getValue());
				saldo += (doitSales.peek().getValue()* lastPrice.peek().getValue());
				saleValue -= (doitSales.peek().getValue()*lastPrice.peek().getKey());
				ret +=doitSales.peek().getValue();
				inventory.add(doitSales.pop());
				lastPrice.pop();
			}
			else {
				contSales -= (units - ret);			
				saleCost -= ((units - ret) * lastPrice.peek().getValue());
				saldo += ((units - ret)* lastPrice.peek().getValue());
				saleValue -= ((units - ret)*lastPrice.peek().getKey());
				
				inventory.add(new Pair<Double, Integer>(doitSales.peek().getKey(), (doitSales.peek().getValue()- ( units - ret))));
				doitSales.peek().setValue((doitSales.peek().getValue()- ( units - ret)));

				ret = units;
			}
			
		}
		 
		pp = saldo / calculateElementsInventory();
	}


	public Stack<Pair<Double, Integer>> getDoitSales() {
		return doitSales;
	}


	public void setDoitSales(Stack<Pair<Double, Integer>> doitSales) {
		this.doitSales = doitSales;
	}


	public int getContSales() {
		return contSales;
	}


	public void setContSales(int contSales) {
		this.contSales = contSales;
	}


	public List<Pair<Double, Integer>> getInventory() {
		return inventory;
	}


	public void setInventory(List<Pair<Double, Integer>> inventory) {
		this.inventory = inventory;
	}


	public double getSaleCost() {
		return saleCost;
	}


	public void setSaleCost(double saleCost) {
		this.saleCost = saleCost;
	}


	public double getSaleValue() {
		return saleValue;
	}


	public void setSaleValue(double saleValue) {
		this.saleValue = saleValue;
	}


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}


	public double getPp() {
		return pp;
	}


	public void setPp(double pp) {
		this.pp = pp;
	}


	public Stack<Pair<Double, Double>> getLastPrice() {
		return lastPrice;
	}


	public void setLastPrice(Stack<Pair<Double, Double>> lastPrice) {
		this.lastPrice = lastPrice;
	}

	
}