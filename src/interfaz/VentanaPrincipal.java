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
	private VentanaIndicadores ventanaIndicadores;
	private boolean estado;
	
	//AQUI PONEN LAS RELACIONES CON EL MUNDO
	private Factory fabrica;
	
	public VentanaPrincipal() throws QueueException {
		inicializarComponentes();
		estado = false;
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
		ventanaIndicadores = new VentanaIndicadores(this);
	}

	public static void main(String[] args) throws QueueException {
		VentanaPrincipal vp= new VentanaPrincipal();
		vp.setVisible(true);
	}

	//a�adir el caso en el que se use pp (Ya esta)
	public void agregarTransaccion() throws QueueException {
		ventanaAgregarTransaccion = new VentanaAgregarTransaccion(this);
		String[] o = {"PEPS", "Promedio ponderado"};
		try {
			int op = JOptionPane.showOptionDialog(null, "Elija el metodo para la valoracion de los inventarios", "Metodo", 0, JOptionPane.QUESTION_MESSAGE, null, o, -1);
			if(op==Factory.PEPS)
			{
				int q = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique las cantidades iniciales", "Saldo inicial", 0));
				int v = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique el precio por unidad inicial", "Saldo inicial", 0));
				fabrica = new Factory(q, v, Factory.PEPS);
				ventanaAgregarTransaccion.getPanelTabla().saldo(q, v);
				ventanaAgregarTransaccion.setVisible(true);
				this.setVisible(false);
			}
			else if(op==Factory.PROM)
			{
				int q = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique las cantidades iniciales", "Saldo inicial", 0));
				int v = Integer.parseInt(JOptionPane.showInputDialog(null, "Indique el precio por unidad inicial", "Saldo inicial", 0));
				fabrica = new Factory(q, v, Factory.PROM);
				ventanaAgregarTransaccion.getPanelTabla().saldo(q, v);
				ventanaAgregarTransaccion.setVisible(true);
				this.setVisible(false);
			}
			estado = false;
		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error al insertar datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public void generarEstado() {
		ventanaEstado = new VentanaGenerarEstado(this);
		if(fabrica==null)
		{
			JOptionPane.showMessageDialog(null, "Tiene que agregar elementos al inventario", "Error", JOptionPane.ERROR_MESSAGE);
		}
		else
		{
			ventanaEstado.setVisible(true);
			this.setVisible(false);
			estado = true;
		}
	}


	public void mostrarIndicadores() {
		if(estado)
		{
			ventanaIndicadores = new VentanaIndicadores(this);
			calcularIndicadores();
			ventanaIndicadores.setVisible(true);
			this.setVisible(false);
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Tiene que generar un estado de resultados primero", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	private void calcularIndicadores() {
		double utilbru = (Double)ventanaEstado.getTabla().getDtm().getValueAt(2, 1);
		double utilope = (Double)ventanaEstado.getTabla().getDtm().getValueAt(4, 1);
		double utilneta = (Double)ventanaEstado.getTabla().getDtm().getValueAt(11, 1);
		double ventas = (Double)ventanaEstado.getTabla().getDtm().getValueAt(0, 1);
		double indicadorRentabilidadBruta = utilbru/ventas;
		double indicadorRentabilidadOperativa =utilope/ventas;
		double indicadoresRentabilidadNeta =utilneta/ventas;
		ventanaIndicadores.mostrarIndicadores(indicadorRentabilidadBruta, indicadorRentabilidadOperativa, indicadoresRentabilidadNeta);
		
	}

	public Factory getFactory()
	{
		return fabrica;
	}
}
