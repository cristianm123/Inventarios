package interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import util.QueueException;

public class VentanaAgregarTransaccion extends JFrame {

	private PanelTabla panelTabla;
	private PanelBotonesTransaccion panelBotonesTransaccion;
	private VentanaPrincipal principal;
	private PanelTitulos panelTitulos;
	
	
	
	public VentanaAgregarTransaccion(VentanaPrincipal p) throws QueueException {
		principal = p;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() throws QueueException {
		panelTabla = new PanelTabla(this);
		panelBotonesTransaccion = new PanelBotonesTransaccion(this);
		panelTitulos = new PanelTitulos();
		this.setSize(904, 550);
		this.setLayout(new BorderLayout());
		this.setTitle("Transacciones");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(panelTabla,BorderLayout.CENTER);
		this.add(panelBotonesTransaccion, BorderLayout.SOUTH);
		this.add(panelTitulos, BorderLayout.NORTH);
		
		
	}

	public int calcularCantidadSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public double calcularTotalSaldo() {
		// TODO Auto-generated method stub
		return 0;
	}

	public void agregarTransaccion(int tipo,String detalle, int cantidad, double valor) throws QueueException {
		if(tipo==PanelBotonesTransaccion.COMPRA) {
			//COMPRA
			panelTabla.aniadirEntrada(tipo, detalle, valor, cantidad);
		}
		else if(tipo==PanelBotonesTransaccion.VENTA) {
			//VENTA
			panelTabla.aniadirSalida(tipo, detalle, valor, cantidad);
		}
		else if(tipo==PanelBotonesTransaccion.DEV_COMPRA) {
			//DEVOLUCION EN COMPRA
			panelTabla.aniadirSalida(tipo, detalle, valor, cantidad);
		}
		else {
			//DEVOLUCION EN VENTA
			panelTabla.aniadirEntrada(tipo, detalle, valor, cantidad);
		}
		
	}

	public void atras() {
		principal.setVisible(true);
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}
	
	public VentanaPrincipal getPrincipal()
	{
		return principal;
	}
	
	public PanelTabla getPanelTabla()
	{
		return panelTabla;
	}
	
	
	
	
}
