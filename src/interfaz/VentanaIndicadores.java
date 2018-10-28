package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class VentanaIndicadores extends JFrame {

	private VentanaPrincipal principal;
	private PanelIndicadores panelIndicadores;
	private PanelBotonesIndicadores panelBotones;
	
	
	public VentanaIndicadores(VentanaPrincipal ventanaPrincipal) {
		principal = ventanaPrincipal;
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.setSize(350,250);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Indicadores");
		panelIndicadores = new PanelIndicadores();
		panelBotones = new PanelBotonesIndicadores(this);
		this.add(panelIndicadores,BorderLayout.NORTH);
		this.add(panelBotones, BorderLayout.SOUTH);
		
		
		
		
		
	}

	public void atras() {
		principal.setVisible(true);
		this.setVisible(false);
		
		
	}
	
	public void mostrarIndicadores(double rentabilidadBruta,double rentabilidadOperativa, double rentabilidadNeta) {
		panelIndicadores.mostrarIndicadores(rentabilidadBruta,rentabilidadOperativa,rentabilidadNeta);
	}
	
	

}
