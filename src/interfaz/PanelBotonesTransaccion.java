package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelBotonesTransaccion extends JPanel implements ActionListener{

	public static final String BUT_AGREGAR = "but_agregar";
	public static final String BUT_ATRAS = "atras";
	public static final int COMPRA = 0;
	public static final int VENTA = 1;
	public static final int DEV_COMPRA = 2;
	public static final int DEV_VENTA = 3;
	
	private VentanaAgregarTransaccion principal;
	private JButton butAtras;
	private JComboBox comboBox;
	private JLabel labTipo;
	private JLabel labCantidad;
	private JLabel labValor;
	private JLabel labDetalle;
	private JButton butAgregar;
	private DefaultComboBoxModel dcbm;
	private JTextField jTxtCantidad;
	private JTextField jTxtValor;
	private JTextField jTxtDetalle;

	public PanelBotonesTransaccion(VentanaAgregarTransaccion ventana) {
		principal= ventana;
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		dcbm = new DefaultComboBoxModel(new String[] { "Compra", "Venta", "Dev.Compra", "Dev.Venta" });
		comboBox= new JComboBox(dcbm);
		labTipo= new JLabel("Tipo");
		labCantidad = new JLabel("Cantidad");
		labValor = new JLabel("Valor/U");
		labDetalle = new JLabel("Detalle");
		butAgregar = new JButton("Agregar");
		butAgregar.setBackground(PanelBotones.COLOR_BOTONES);
		butAgregar.setActionCommand(BUT_AGREGAR);
		butAgregar.addActionListener(this);
		butAtras = new JButton("Atras");
		butAtras.setBackground(PanelBotones.COLOR_BOTONES);
		butAtras.setActionCommand(BUT_ATRAS);
		butAtras.addActionListener(this);
		jTxtCantidad = new JTextField();
		jTxtValor = new JTextField();
		jTxtDetalle = new JTextField();
		this.setPreferredSize(new Dimension(904,100));
		this.setLayout(new GridLayout(2,6));
		this.setBorder(BorderFactory.createTitledBorder("Agregar"));
		this.add(labTipo);
		this.add(labDetalle);
		this.add(labCantidad);
		this.add(labValor);
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(comboBox);
		this.add(jTxtDetalle);
		this.add(jTxtCantidad);
		this.add(jTxtValor);
		this.add(butAgregar);
		this.add(butAtras);
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(BUT_AGREGAR)) {
			int tipo = comboBox.getSelectedIndex();
			principal.agregarTransaccion(tipo,jTxtDetalle.getText(),Integer.parseInt(jTxtCantidad.getText()),Double.parseDouble(jTxtValor.getText()));
			
		}
		else {
			principal.atras();
		}
		// TODO Auto-generated method stub
		
	}
	

	
	
	
	
	
	
	
	
}
