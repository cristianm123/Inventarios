package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelTitulos extends JPanel {

	private JLabel labEntradas;
	private JLabel labSalidas;
	private JLabel labSaldo;
	
	public PanelTitulos() {
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.setLayout(new GridLayout(1,9));
		this.setPreferredSize(new Dimension(904,50));
		labEntradas= new JLabel("Entradas");
		labEntradas.setHorizontalTextPosition(SwingConstants.CENTER);
		labSalidas = new JLabel("Salidas");
		labSalidas.setHorizontalTextPosition(SwingConstants.CENTER);
		labSaldo = new JLabel("Saldo");
		labSaldo.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setBorder(BorderFactory.createTitledBorder("Tipo de Transaccion"));
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(new JLabel());
		this.add(labEntradas);
		this.add(new JLabel());
		this.add(labSalidas);
		this.add(new JLabel());
		this.add(labSaldo);
		this.add(new JLabel());
		
		
	}
	
	
	
	
	
}
