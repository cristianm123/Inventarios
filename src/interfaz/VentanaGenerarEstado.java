package interfaz;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import model.Factory;

public class VentanaGenerarEstado extends JFrame {

	private VentanaPrincipal principal;
	private PanelBotonesEstadoResultados panelBotonesEstado;
	private PanelTablaEstadoDeResultados panelTablaEstadoDeResultados;
	private PanelResultados panelResultados;
	
	
	
	
	public VentanaGenerarEstado(VentanaPrincipal ventanaPrincipal) {
		principal = ventanaPrincipal;
		inicializarComponentes();
		
		// TODO Auto-generated constructor stub
	}


	private void inicializarComponentes() {
		this.setSize(500,450);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.setTitle("Estado de Resultados");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		panelBotonesEstado = new PanelBotonesEstadoResultados(this);
		panelTablaEstadoDeResultados = new PanelTablaEstadoDeResultados(this);
		panelResultados = new PanelResultados(this);
		this.add(panelBotonesEstado,BorderLayout.EAST);
		this.add(panelResultados, BorderLayout.SOUTH);
		this.add(panelTablaEstadoDeResultados, BorderLayout.CENTER);
		
		
	}


	public void botonAtras() {
		principal.setVisible(true);
		this.setVisible(false);
		
	}

	public PanelTablaEstadoDeResultados getTabla()
	{
		return panelTablaEstadoDeResultados;
	}

	public void generarEstado() {
		
		try {
			Factory f = principal.getFactory();
			String nombre = panelBotonesEstado.getjTxtNombreCompania().getText();
			String fecha = panelBotonesEstado.getjTxtFecha().getText();
			panelTablaEstadoDeResultados.getTitulos().cambiarNombreCia(("Nombre compañia: " + nombre));
			panelTablaEstadoDeResultados.getTitulos().cambiarPeriodo(("Periodo: "+fecha));
			panelTablaEstadoDeResultados.inicializarTabla(f.getSales(), f.getCostSales(), panelBotonesEstado.getjTxtGastosOp(),
			panelBotonesEstado.getjTxtOtrosIngresos(), panelBotonesEstado.getjTxtOtrosGastos(),
			panelBotonesEstado.getjTxtImpuesto(), panelBotonesEstado.getReservas());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error al insertar datos", "Error", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}

}
