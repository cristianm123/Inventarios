package interfaz;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class VentanaPrincipal extends JFrame {

	private PanelBotones panelBotones;
	private PanelBanner panelBanner;
	private VentanaAgregarTransaccion ventanaAgregarTransaccion;
	//AQUI PONEN LAS RELACIONES CON EL MUNDO
	
	
	
	public VentanaPrincipal() {
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		this.setSize(836, 657);
		this.setTitle("Inventarios");
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelBotones = new PanelBotones(this);
		panelBanner = new PanelBanner("./imagenes/Banner/Contabilidad.jpg");
		this.setLayout(new BorderLayout());
		this.add(panelBotones,BorderLayout.SOUTH);
		this.add(panelBanner, BorderLayout.NORTH);
		ventanaAgregarTransaccion = new VentanaAgregarTransaccion(this);
	}


	public static void main(String[] args) {
		VentanaPrincipal vp= new VentanaPrincipal();
		vp.setVisible(true);
	}


	public void agregarTransaccion() {
		ventanaAgregarTransaccion.setVisible(true);
		this.setVisible(false);
		
	}


	public void generarEstado() {
		// TODO Auto-generated method stub
		
	}


	public void mostrarIndicadores() {
		// TODO Auto-generated method stub
		
	}

}
