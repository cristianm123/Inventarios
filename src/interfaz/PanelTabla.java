package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.DecimalFormat;
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
	private DecimalFormat df; 
	private DefaultTableCellRenderer tcr;

	
	public PanelTabla(VentanaAgregarTransaccion ventanaAgregarTransaccion) throws QueueException {
		principal= ventanaAgregarTransaccion;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
//	 System.out.println(df.format(number));
		df = new DecimalFormat("#.00");
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
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer tcrCenter = new DefaultTableCellRenderer();
		tcrCenter.setHorizontalAlignment(SwingConstants.CENTER);
		jTblTransacciones.getColumnModel().getColumn(2).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(4).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(6).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(8).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(3).setCellRenderer(tcrCenter);
		jTblTransacciones.getColumnModel().getColumn(5).setCellRenderer(tcrCenter);
		jTblTransacciones.getColumnModel().getColumn(7).setCellRenderer(tcrCenter);
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
		if(tipo == PanelBotonesTransaccion.DEV_VENTA)
		{
			Object[] fil = {fecha,detalle, "---", cantidad, null, null, null, null,null};
			dtm.addRow(fil);
		}
		else
		{
			Object[] fil = {fecha,detalle, "$"+df.format(valorUnitario), cantidad, null, null, null, null,null};
			dtm.addRow(fil);
		}
		
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
			
			Object[] fila = {fecha,detalle, "$"+df.format(pp.getPp()),null,null,null, null, pp.calculateElementsInventory(), "$"+df.format(pp.getSaldo())};
			dtm.addRow(fila);

		}
		else {
			
			if(tipo==PanelBotonesTransaccion.COMPRA)
			{
				p.buy(cantidad, valorUnitario);
			}
			else
			{
				p.returnSale(cantidad);
			}
			Queue<Pair<Double, Integer>> q = p.getInventory();
			Queue<Pair<Double, Integer>> n = new Queue<>();

			while(!q.isEmpty())
			{
				
				Object[] fila = {fecha,detalle,"$"+df.format(q.front().getKey()),null, null, null, null, q.front().getValue(),"$"+df.format(q.front().getValue()*q.front().getKey())};
				dtm.addRow(fila);
				n.enqueue(q.dequeue());
			}
			while(!n.isEmpty())
			{
				q.enqueue(n.dequeue());
			}
			Object[] fila = {fecha,detalle,null,null,null,null,null, p.getInitialUnits()+p.getNum_purchases()+p.getNum_sales_returned()-p.getNum_sales()-p.getNum_purchases_returned(),"$"+ df.format(p.getFinalInventory())};
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
			
			Object[] fila = {fecha,detalle, "$"+df.format(pp.getPp()),null,null,cantidad, null, pp.calculateElementsInventory(),"$"+ df.format(pp.getSaldo())};
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
				
				Object[] fila = {fecha,detalle,"$"+df.format(queue.front().getKey()),null,null,queue.front().getValue(),"$"+df.format(queue.front().getValue()*queue.front().getKey()),null,null};
				dtm.addRow(fila);
				queue.dequeue();
			}

			
			while(!q.isEmpty())
			{
				
				Object[] fila = {fecha,detalle,"$"+df.format(q.front().getKey()),null,null,null, null,q.front().getValue(),"$"+df.format(q.front().getValue()*q.front().getKey())};
				dtm.addRow(fila);
				n.enqueue(q.dequeue());
			}
			while(!n.isEmpty())
			{
				q.enqueue(n.dequeue());
			}
			Object[] fila = {fecha,detalle, null,null,null,null, null,  p.getInitialUnits()+p.getNum_purchases()+p.getNum_sales_returned()-p.getNum_sales()-p.getNum_purchases_returned(), "$"+df.format(p.getFinalInventory())};
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
		Object[] fila = {fecha,"Saldo inicial","$"+df.format(v),q,"$"+df.format(q*v),null, null, q, "$"+df.format(q*v)};
		dtm.addRow(fila);
	}
	

	
	
	
	
	

}
