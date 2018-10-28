package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTitulosEstado extends JPanel {

	private JLabel nombreCia;
	private JLabel periodo;
	
	public PanelTitulosEstado() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.setLayout(new GridLayout(3,1));
		this.setPreferredSize(new Dimension(350,150));
		this.setBorder(BorderFactory.createTitledBorder("E / R"));
		periodo = new JLabel("Perido");
		nombreCia = new JLabel("Nombre Compañia");
		JLabel labEstado= new JLabel("Estado de Resultados");
		labEstado.setHorizontalAlignment(SwingConstants.CENTER);
		periodo.setHorizontalAlignment(SwingConstants.CENTER);
		nombreCia.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(nombreCia);
		this.add(labEstado);
		this.add(periodo);
		
	}
	
	public void cambiarNombreCia(String nombre) {
		nombreCia.setText(nombre);
	}
	public void cambiarPeriodo(String periodo) {
		this.periodo.setText(periodo);
	}
	
}
