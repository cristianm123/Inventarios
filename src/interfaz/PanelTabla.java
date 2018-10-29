package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.*;
import util.NoSuchElementsExceptions;
import util.Pair;
import util.Queue;
import util.QueueException;

public class PanelTabla extends JPanel {
	
	private VentanaAgregarTransaccion principal;

	private JTable jTblTransacciones;
	private JScrollPane scroll;
	private DefaultTableModel dtm;
	private JButton butBuscar;
	private JButton butBorrar;
	private JButton butOrdenarNombre;
	private JButton butOrdenarPuntaje;
	
	public PanelTabla(VentanaAgregarTransaccion ventanaAgregarTransaccion) throws QueueException {
		principal= ventanaAgregarTransaccion;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(904,400));
		inicializarTabla();
		this.setBorder(BorderFactory.createTitledBorder("Tabla Transacciones"));
		this.add(scroll,BorderLayout.CENTER);
	}
	
	
	public void inicializarTabla() {
		
		String[][] data= {};
		String[] titulos= new String[]{"Fecha","Detalle","Valor/U","Cantidad","Total","Cantidad","Total","Cantidad","Total"};
		dtm= new DefaultTableModel(data,titulos);
		jTblTransacciones=new JTable(dtm);
		//jTblTransacciones.setPreferredSize(new Dimension(904, 450));
		scroll= new JScrollPane(jTblTransacciones);	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		jTblTransacciones.getColumnModel().getColumn(0).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(1).setCellRenderer(tcr);
		jTblTransacciones.enable(false);
		
	}
	
	//Esta solo para peps, añadir un condicional para hacerlo tambien para pp
	public void aniadirEntrada(int tipo, String detalle, double valorUnitario,int cantidad) throws QueueException, NoSuchElementsExceptions {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		Object[] fi = {"---","---","---","---", "---", "---", "---", "---","---"};
		dtm.addRow(fi);
		Object[] fil = {fecha,detalle, valorUnitario, cantidad, null, null, null, null,null};
		dtm.addRow(fil);
		PEPS p = principal.getPrincipal().getFactory().getPEPS();
		PP pp = principal.getPrincipal().getFactory().getPP();
		
		
		
		
		if(principal.getPrincipal().getFactory().getPEPS() == null) {
			if(tipo==PanelBotonesTransaccion.COMPRA)
			{
				pp.buy(cantidad, valorUnitario);
			}
			else
			{
				pp.returnSale(cantidad);
			}
			List<Pair<Double, Integer>> q = pp.getInventory();
			List<Pair<Double, Integer>> n = new ArrayList<>();
			
			Object[] fila = {fecha,detalle, pp.getPp(),null,null,null, null, pp.calculateElementsInventory(), pp.getSaldo()};
			dtm.addRow(fila);

		}
		else {
			
			if(tipo==PanelBotonesTransaccion.COMPRA)
			{
				p.buy(cantidad, valorUnitario);
			}
			else
			{
				p.returnSale(cantidad, valorUnitario);
			}
			Queue<Pair<Double, Integer>> q = p.getInventory();
			Queue<Pair<Double, Integer>> n = new Queue<>();

			while(!q.isEmpty())
			{
				
				Object[] fila = {fecha,detalle,q.front().getKey(),null, null, null, null, q.front().getValue(),q.front().getValue()*q.front().getKey()};
				dtm.addRow(fila);
				n.enqueue(q.dequeue());
			}
			while(!n.isEmpty())
			{
				q.enqueue(n.dequeue());
			}
			Object[] fila = {fecha,detalle,null,null,null,null,null, p.getInitialUnits()+p.getNum_purchases()-p.getNum_sales()-p.getNum_purchases_returned(), p.getFinalInventory()};
			dtm.addRow(fila);
		}
		

	}
	//Esta solo para peps, añadir un condicional para hacerlo tambien para pp
	public void aniadirSalida(int tipo, String detalle, double valorUnitario,int cantidad) throws QueueException, NoSuchElementsExceptions {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		PEPS p = principal.getPrincipal().getFactory().getPEPS();
		PP pp = principal.getPrincipal().getFactory().getPP();


		
		
		if(principal.getPrincipal().getFactory().getPEPS() == null) {
			List<Pair<Double, Integer>> queue = new ArrayList<>();
			Object[] fi = {"---","---","---","---", "---", "---", "---", "---","---"};
			dtm.addRow(fi);
			if(tipo==PanelBotonesTransaccion.VENTA)
			{
				queue = pp.sell(cantidad, valorUnitario);
			}
			else
			{
				queue = pp.returnPurchase(cantidad);
			}
			List<Pair<Double, Integer>> q = pp.getInventory();
			List<Pair<Double, Integer>> n = new ArrayList<>();
			
			Object[] fila = {fecha,detalle, pp.getPp(),null,null,cantidad, null, pp.calculateElementsInventory(), pp.getSaldo()};
			dtm.addRow(fila);

		}
		else {
			
			Queue<Pair<Double, Integer>> queue = new Queue<>();
			Object[] fi = {"---","---","---","---", "---", "---", "---", "---","---"};
			dtm.addRow(fi);
			if(tipo==PanelBotonesTransaccion.VENTA)
			{
				queue = p.sell(cantidad, valorUnitario);
			}
			else
			{
				queue = p.returnPurchase(cantidad);
			}
			Queue<Pair<Double, Integer>> q = p.getInventory();
			Queue<Pair<Double, Integer>> n = new Queue<>();

		

			while(!queue.isEmpty())
			{
				
				Object[] fila = {fecha,detalle,queue.front().getKey(),null,null,queue.front().getValue(),queue.front().getValue()*queue.front().getKey(),null,null};
				dtm.addRow(fila);
				queue.dequeue();
			}

			
			while(!q.isEmpty())
			{
				
				Object[] fila = {fecha,detalle,q.front().getKey(),null,null,null, null,q.front().getValue(),q.front().getValue()*q.front().getKey()};
				dtm.addRow(fila);
				n.enqueue(q.dequeue());
			}
			while(!n.isEmpty())
			{
				q.enqueue(n.dequeue());
			}
			Object[] fila = {fecha,detalle, null,null,null,null, null, p.getInitialUnits()+p.getNum_purchases()-p.getNum_purchases_returned()-p.getNum_sales(), p.getFinalInventory()};
			dtm.addRow(fila);
		}
		}
		
	//Sirve para peps y para pp
	public void saldo(int q, double v) throws QueueException
	{
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		Object[] fila = {fecha,"Saldo inicial",v,q,q*v,null, null, q, q*v};
		dtm.addRow(fila);
	}
	

	
	
	
	
	

}
