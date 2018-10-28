package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import model.PEPS;
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
		jTblTransacciones.setPreferredSize(new Dimension(904, 450));
		scroll= new JScrollPane(jTblTransacciones);	
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		jTblTransacciones.getColumnModel().getColumn(0).setCellRenderer(tcr);
		jTblTransacciones.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
	}
	
	public void aniadirEntrada(int tipo, String detalle, double valorUnitario,int cantidad) throws QueueException {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		PEPS p = principal.getPrincipal().getFactory().getPEPS();
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
			
			Object[] fila = {fecha,detalle,q.front().getKey(),q.front().getValue(),q.front().getValue()*q.front().getKey(),null,null,null,null};
			dtm.addRow(fila);
			n.enqueue(q.dequeue());
		}
		while(!n.isEmpty())
		{
			q.enqueue(n.dequeue());
		}
		Object[] fila = {fecha,detalle, null,null,null,null, null, p.getInitialUnits()+p.getNum_purchases()-p.getNum_sales(), p.getFinalInventory()};
		dtm.addRow(fila);
		
	}
	
	public void aniadirSalida(int tipo, String detalle, double valorUnitario,int cantidad) throws QueueException {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		PEPS p = principal.getPrincipal().getFactory().getPEPS();
		if(tipo==PanelBotonesTransaccion.VENTA)
		{
			p.sell(cantidad, valorUnitario);
		}
		else
		{
			p.returnPurchase(cantidad);
		}
		Queue<Pair<Double, Integer>> q = p.getInventory();
		Queue<Pair<Double, Integer>> n = new Queue<>();
		while(!q.isEmpty())
		{
			
			Object[] fila = {fecha,detalle,q.front().getKey(),q.front().getValue(),q.front().getValue()*q.front().getKey(),null,null,null,null};
			dtm.addRow(fila);
			n.enqueue(q.dequeue());
		}
		while(!n.isEmpty())
		{
			q.enqueue(n.dequeue());
		}
		Object[] fila = {fecha,detalle, null,null,null,null, null, p.getInitialUnits()+p.getNum_purchases()-p.getNum_sales(), p.getFinalInventory()};
		dtm.addRow(fila);
	}
	
	public void saldo() throws QueueException
	{
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		PEPS p = principal.getPrincipal().getFactory().getPEPS();
		Object[] fila = {fecha,"Saldo inicial",p.getInventory().front().getKey(),p.getInventory().front().getValue(),p.getInventory().front().getValue()*p.getInventory().front().getKey(),null, null, p.getInitialUnits()+p.getNum_purchases()-p.getNum_sales(), p.getFinalInventory()};
		dtm.addRow(fila);
	}
	
	
	
	
	

}
