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
		inicializarTabla();
		panelTitulosEstado = new PanelTitulosEstado();
		this.add(panelTitulosEstado,BorderLayout.NORTH);
		this.add(jTblEstado,BorderLayout.CENTER);
		
		
		
	}

	private void inicializarTabla() {

		Object[] row0 = {"Ventas",0};
		Object[] row1 = {"Costo de Venta",0};
		Object[] row2 = {"Utilidad Bruta",0};
		Object[] row3 = {"Gastos Operacionales",0};
		Object[] row4 = {"Utilidad Operativa",0};
		Object[] row5 = {"Otros Ingresos",0};
		Object[] row6 = {"Otros Gastos",0};
		Object[] row7 = {"Utilidad Antes Impto",0};
		Object[] row8 = {"Impuestos",0};
		Object[] row9 = {"Utilidad despues Impto",0};
		Object[] row10 = {"Reserva",0};
		Object[] row11 = {"Utilidad Neta",0};
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

}
