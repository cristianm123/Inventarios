package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class PanelBotonesEstadoResultados extends JPanel {

	
	
	private VentanaGenerarEstado principal;
	private JLabel labGastosOp;
	private JTextField jTxtGastosOp;
	private JLabel labOtrosIngresos;
	private JTextField jTxtOtrosIngresos;
	private JLabel labOtrosGastos;
	private JTextField jTxtOtrosGastos;
	private JLabel labImpto;
	private JTextField jTxtImpuesto;
	private JLabel labReserva;
	private ButtonGroup bgReserva;
	private JRadioButton jRadioButSi;
	private JRadioButton jRadioButNo;
	private JLabel labNombreCompania;
	private JTextField jTxtNombreCompania;
	private JLabel labFecha;
	private JTextField jTxtFecha;
	
	
	
	public PanelBotonesEstadoResultados(VentanaGenerarEstado ventanaGenerarEstado) {
		principal= ventanaGenerarEstado;
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
		this.setPreferredSize(new Dimension(150,400));
		this.setLayout(new GridLayout(14,1));
		this.setBorder(BorderFactory.createTitledBorder("Datos Extra"));
		labGastosOp = new JLabel("Gastos Operacionales");
		jTxtGastosOp = new JTextField();
		labOtrosIngresos = new JLabel("Otros Ingresos");
		jTxtOtrosIngresos = new JTextField();
		labOtrosGastos = new JLabel("Otros Gastos");
		jTxtOtrosGastos = new JTextField();
		labNombreCompania = new JLabel("Nombre Compañia");
		jTxtNombreCompania = new JTextField();
		labFecha = new JLabel("Periodo");
		jTxtFecha = new JTextField();
		labImpto = new JLabel("Impuesto (Porcentaje)");
		jTxtImpuesto = new JTextField();
		labReserva = new JLabel("Guardar Reserva ?");
		jRadioButSi = new JRadioButton("Si");
		jRadioButNo = new JRadioButton("No");
		bgReserva = new ButtonGroup();
		bgReserva.add(jRadioButSi);
		bgReserva.add(jRadioButNo);
		this.add(labNombreCompania);
		this.add(jTxtNombreCompania);
		this.add(labFecha);
		this.add(jTxtFecha);
		this.add(labGastosOp);
		this.add(jTxtGastosOp);
		this.add(labOtrosIngresos);
		this.add(jTxtOtrosIngresos);
		this.add(labOtrosGastos);
		this.add(jTxtOtrosGastos);
		this.add(labImpto);
		this.add(jTxtImpuesto);
		this.add(labReserva);
		JPanel panelAux= new JPanel();
		panelAux.setLayout(new GridLayout(1,2));
		panelAux.add(jRadioButSi);
		panelAux.add(jRadioButNo);
		this.add(panelAux);
	}
	
	public void reestablecerCampos() {
		jTxtGastosOp.setText("");
		jTxtOtrosIngresos.setText("");
		jTxtOtrosGastos.setText("");
		jTxtImpuesto.setText("");
		jTxtFecha.setText("");
		jTxtNombreCompania.setText("");
	}
	

}
