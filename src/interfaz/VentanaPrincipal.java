package interfaz;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.Factory;
import util.QueueException;

public class VentanaPrincipal extends JFrame {

	private PanelBotones panelBotones;
	private PanelBanner panelBanner;
	private VentanaAgregarTransaccion ventanaAgregarTransaccion;
	private VentanaGenerarEstado ventanaEstado;
	//AQUI PONEN LAS RELACIONES CON EL MUNDO
	private Factory fabrica;
	
	
	public VentanaPrincipal() throws QueueException {
		inicializarComponentes();
	}
	
	public void inicializarComponentes() throws QueueException {
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
		ventanaEstado = new VentanaGenerarEstado(this);
	}


	public static void main(String[] args) throws QueueException {
		VentanaPrincipal vp= new VentanaPrincipal();
		vp.setVisible(true);
	}


	public void agregarTransaccion() throws QueueException {
		String[] o = {"PEPS", "Promedio ponderado"};
		if(JOptionPane.showOptionDialog(null, "Elija el metodo para la valoracion de los inventarios", "Metodo", 0, 0, null, o, -1)==0)
		{
			int q = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique las cantidades iniciales", "Saldo inicial", 0));
			int v = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique el precio por unidad inicial", "Saldo inicial", 0));
			fabrica = new Factory(q, v);
			ventanaAgregarTransaccion.getPanelTabla().saldo();
		}
		ventanaAgregarTransaccion.setVisible(true);
		this.setVisible(false);
		
	}


	public void generarEstado() {
		ventanaEstado.setVisible(true);
		this.setVisible(false);
		
	}


	public void mostrarIndicadores() {
		// TODO Auto-generated method stub
		
	}

	public Factory getFactory()
	{
		return fabrica;
	}
}