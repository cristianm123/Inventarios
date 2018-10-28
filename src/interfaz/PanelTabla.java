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

public class PanelTabla extends JPanel {
	
	private VentanaAgregarTransaccion principal;

	private JTable jTblTransacciones;
	private JScrollPane scroll;
	private DefaultTableModel dtm;
	private JButton butBuscar;
	private JButton butBorrar;
	private JButton butOrdenarNombre;
	private JButton butOrdenarPuntaje;
	
	public PanelTabla(VentanaAgregarTransaccion ventanaAgregarTransaccion) {
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
	
	public void aniadirEntrada(String detalle, double valorUnitario,int cantidad, double Total,int cantidadSaldo,double totalSaldo) {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		Object[] fila = {fecha,detalle,valorUnitario,cantidad,Total,null,null,cantidadSaldo,totalSaldo};
		dtm.addRow(fila);
	}
	
	public void aniadirSalida(String detalle, double valorUnitario,int cantidad, double Total,int cantidadSaldo,double totalSaldo) {
		Calendar c = new GregorianCalendar();
		String dia = Integer.toString(c.get(Calendar.DATE));
		String mes = Integer.toString(c.get(Calendar.MONTH)+1);
		String annio = Integer.toString(c.get(Calendar.YEAR));
		String fecha =dia+"/"+mes+"/"+annio;
		Object[] fila = {fecha,detalle,valorUnitario,null,null,cantidad,Total,cantidadSaldo,totalSaldo};
		dtm.addRow(fila);
	}
	
	
	
	
	

}
