package interfaz;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class VentanaAgregarTransaccion extends JFrame {

	private PanelTabla panelTabla;
	private PanelBotonesTransaccion panelBotonesTransaccion;
	private VentanaPrincipal principal;
	private PanelTitulos panelTitulos;
	
	
	
	public VentanaAgregarTransaccion(VentanaPrincipal p) {
		principal = p;
		inicializarComponentes();
	}
	
	public void inicializarComponentes() {
		panelTabla = new PanelTabla(this);
		panelBotonesTransaccion = new PanelBotonesTransaccion(this);
		panelTitulos = new PanelTitulos();
		this.setSize(904, 550);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
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

	public void agregarTransaccion(int tipo,String detalle, int cantidad, double valor) {
		if(tipo==PanelBotonesTransaccion.COMPRA) {
			//COMPRA
			int cantidadSaldo =0;//Aqui deben llamar al metodo que les cancule cuanto queda en la cantidad total del saldo
			double totalSaldo =0;//Aqui deben llamar al metodo que les calculo cuanto queda en el valor total del saldo.
			panelTabla.aniadirEntrada(detalle, valor, cantidad, valor*cantidad, cantidadSaldo, totalSaldo);
		}
		else if(tipo==PanelBotonesTransaccion.VENTA) {
			//VENTA
			int cantidadSaldo =0;//Aqui deben llamar al metodo que les cancule cuanto queda en la cantidad total del saldo
			double totalSaldo =0;//Aqui deben llamar al metodo que les calculo cuanto queda en el valor total del saldo.
			panelTabla.aniadirSalida(detalle, valor, cantidad, valor*cantidad, cantidadSaldo, totalSaldo);
		}
		else if(tipo==PanelBotonesTransaccion.DEV_COMPRA) {
			//DEVOLUCION EN COMPRA
			int cantidadSaldo =0;//Aqui deben llamar al metodo que les cancule cuanto queda en la cantidad total del saldo
			double totalSaldo =0;//Aqui deben llamar al metodo que les calculo cuanto queda en el valor total del saldo.
			panelTabla.aniadirSalida(detalle, valor, cantidad, valor*cantidad, cantidadSaldo, totalSaldo);
		}
		else {
			//DEVOLUCION EN VENTA
			int cantidadSaldo =0;//Aqui deben llamar al metodo que les cancule cuanto queda en la cantidad total del saldo
			double totalSaldo =0;//Aqui deben llamar al metodo que les calculo cuanto queda en el valor total del saldo.
			panelTabla.aniadirEntrada(detalle, valor, cantidad, valor*cantidad, cantidadSaldo, totalSaldo);
		}
		
	}

	public void atras() {
		principal.setVisible(true);
		this.setVisible(false);
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
