package interfaz;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PanelIndicadores extends JPanel {


	private JLabel labRentabilidadBruta;
	private JLabel valorRentabilidadBruta;
	private JLabel labRentabilidadOperativa;
	private JLabel valorRentabilidadOperativa;
	private JLabel labRentabilidadNeta;
	private JLabel valorRentabilidadNeta;
	
	
	public PanelIndicadores() {
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
		this.setPreferredSize(new Dimension(150,150));
		this.setLayout(new GridLayout(3,2));
		this.setBorder(BorderFactory.createTitledBorder("Indicadores"));
		labRentabilidadBruta = new JLabel("Rentabilidad Bruta");
		valorRentabilidadBruta = new JLabel();
		valorRentabilidadBruta.setHorizontalAlignment(SwingConstants.CENTER);
		labRentabilidadOperativa = new JLabel("Rentabilidad Operativa");
		valorRentabilidadOperativa = new JLabel();
		valorRentabilidadOperativa.setHorizontalAlignment(SwingConstants.CENTER);
		labRentabilidadNeta = new JLabel("Rentabilidad Neta");
		valorRentabilidadNeta = new JLabel();
		valorRentabilidadNeta.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(labRentabilidadBruta);
		this.add(valorRentabilidadBruta);
		this.add(labRentabilidadOperativa);
		this.add(valorRentabilidadOperativa);
		this.add(labRentabilidadNeta);
		this.add(valorRentabilidadNeta);
	}

	public void mostrarIndicadores(double rentabilidadBruta, double rentabilidadOperativa, double rentabilidadNeta) {
		valorRentabilidadBruta.setText(rentabilidadBruta+"%");
		valorRentabilidadOperativa.setText(rentabilidadOperativa+"%");
		valorRentabilidadNeta.setText(rentabilidadNeta+"%");
		
	}
	
	
	
	
	
	

}
