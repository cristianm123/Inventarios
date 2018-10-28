package interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.QueueException;

public class PanelBotones extends JPanel implements ActionListener{

	public static final String AGREGAR ="agregar";
	public static final String GENERAR_ESTADO = "generar";
	public static final String MOSTRAR_INDICADORES = "mostrar";
	public static final Color COLOR_BOTONES = new Color(220,221,226);
	
	private VentanaPrincipal principal;
	private JButton butAgregarTransacciones;
	private JButton butGenerarEstado;
	private JButton butMostrarIndicadores;
	
	
	
	
	public PanelBotones(VentanaPrincipal p) {
		principal=p;
		inicializarComponentes();
		
	}
	
	public void inicializarComponentes() {
		this.setLayout(new GridLayout(1,7));
		this.setBorder(BorderFactory.createTitledBorder("Botones"));
		this.setPreferredSize(new Dimension(836,100));
		butAgregarTransacciones = new JButton("Agregar Transacciones");
		butAgregarTransacciones.setActionCommand(AGREGAR);
		butAgregarTransacciones.addActionListener(this);
		butAgregarTransacciones.setBackground(COLOR_BOTONES);
		butGenerarEstado = new JButton("Generar E/R");
		butGenerarEstado.setActionCommand(GENERAR_ESTADO);
		butGenerarEstado.addActionListener(this);
		butGenerarEstado.setBackground(COLOR_BOTONES);
		butMostrarIndicadores = new JButton("Mostrar Indicadores");
		butMostrarIndicadores.setActionCommand(MOSTRAR_INDICADORES);
		butMostrarIndicadores.addActionListener(this);
		butMostrarIndicadores.setBackground(COLOR_BOTONES);
		this.add(butAgregarTransacciones);
		this.add(butGenerarEstado);
		this.add(butMostrarIndicadores);

	}




	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando=evento.getActionCommand();
		if(comando.equals(AGREGAR)) {
			try {
				principal.agregarTransaccion();
			} catch (QueueException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(comando.equals(GENERAR_ESTADO)) {
			principal.generarEstado();
		}
		else {
			principal.mostrarIndicadores();
		}
		
		
		
	}

	

}
