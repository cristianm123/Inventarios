package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanelTablaEstadoDeResultados extends JPanel {
	
	
	private VentanaGenerarEstado principal;
	private DefaultTableModel dtm;
	private JTable jTblEstado;
	private PanelTitulosEstado panelTitulosEstado;

	public PanelTablaEstadoDeResultados(VentanaGenerarEstado ventanaGenerarEstado) {
		principal = ventanaGenerarEstado;
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
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
		
		Object[] row0 = {"Ventas",ventas};
		Object[] row1 = {"Costo de Venta",cventas};
		Object[] row2 = {"Utilidad Bruta",ventas-cventas};
		Object[] row3 = {"Gastos Operacionales",gopera};
		Object[] row4 = {"Utilidad Operativa",utilope};
		Object[] row5 = {"Otros Ingresos",oting};
		Object[] row6 = {"Otros Gastos",otgast};
		Object[] row7 = {"Utilidad Antes Impto",utilant};
		Object[] row8 = {"Impuestos",impuestos};
		Object[] row9 = {"Utilidad despues Impto",utildesp};
		Object[] row10 = {"Reserva",reserva};
		Object[] row11 = {"Utilidad Neta",utilnet};
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
