package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelTablaEstadoDeResultados extends JPanel {
	
	
	private VentanaGenerarEstado principal;
	private DefaultTableModel dtm;
	private JTable jTblEstado;
	private PanelTitulosEstado panelTitulosEstado;
	private DecimalFormat df; 
	private DefaultTableCellRenderer tcr;

	public PanelTablaEstadoDeResultados(VentanaGenerarEstado ventanaGenerarEstado) {
		principal = ventanaGenerarEstado;
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
		df = new DecimalFormat("#.00");
		this.setPreferredSize(new Dimension(350,450));
		this.setBorder(BorderFactory.createTitledBorder("TablaEstado"));
		this.setLayout(new BorderLayout());
		String[][] data= {};
		String[] title = {"Detalle","Total"};
		dtm = new DefaultTableModel(data,title);
		jTblEstado = new JTable(dtm);
		jTblEstado.setPreferredSize(new Dimension(350,500));
		jTblEstado.setEnabled(false);
		panelTitulosEstado = new PanelTitulosEstado();
		inicializarTabla(0,0,0,0,0,0,false);
		panelTitulosEstado = new PanelTitulosEstado();
		this.add(panelTitulosEstado,BorderLayout.NORTH);
		this.add(jTblEstado,BorderLayout.CENTER);
		tcr = new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(SwingConstants.RIGHT);
		jTblEstado.getColumnModel().getColumn(1).setCellRenderer(tcr);
		
		
	}

	public void inicializarTabla(double ventas, double cventas, double gopera, double oting, double otgast, double imp, boolean res) {

		while(dtm.getRowCount()!=0)
		{
			dtm.removeRow(0);
		}
		double utilbru = ventas - cventas;
		double utilope = utilbru-gopera;
		double utilant = utilope+oting-otgast;
		double impuestos = imp*utilant;
		double utildesp = utilant-impuestos;
		double reserva = 0;
		if(res)
		{
			reserva = utildesp*0.1;
		}
		double utilnet = utildesp-reserva;
		
		Object[] row0 = {"Ventas","$"+df.format(ventas)};
		Object[] row1 = {"Costo de Venta","($"+df.format(cventas)+")"};
		Object[] row2 = {"Utilidad Bruta","$"+df.format(ventas-cventas)};
		Object[] row3 = {"Gastos Operacionales","($"+df.format(gopera)+")"};
		Object[] row4 = {"Utilidad Operativa","$"+df.format(utilope)};
		Object[] row5 = {"Otros Ingresos","$"+df.format(oting)};
		Object[] row6 = {"Otros Gastos","($"+df.format(otgast)+")"};
		Object[] row7 = {"Utilidad Antes Impto","$"+df.format(utilant)};
		Object[] row8 = {"Impuestos","($"+df.format(impuestos)+")"};
		Object[] row9 = {"Utilidad despues Impto","$"+df.format(utildesp)};
		Object[] row10 = {"Reserva","($"+df.format(reserva)+")"};
		Object[] row11 = {"Utilidad Neta","$"+df.format(utilnet)};
		dtm.addRow(row0);
		dtm.addRow(row1);
		dtm.addRow(row2);
		dtm.addRow(row3);
		dtm.addRow(row4);
		dtm.addRow(row5);
		dtm.addRow(row6);
		dtm.addRow(row7);
		dtm.addRow(row8);
		dtm.addRow(row9);
		dtm.addRow(row10);
		dtm.addRow(row11);
		// TODO Auto-generated method stub
		
	}
	
	public DefaultTableModel getDtm()
	{
		return dtm;
	}
	
	public PanelTitulosEstado getTitulos()
	{
		return panelTitulosEstado;
	}

}
