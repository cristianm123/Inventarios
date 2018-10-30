package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelResultados extends JPanel implements ActionListener {

	public static final String BUT_GENERAR_ESTADO = "Generar Estado";
	public static final String BUT_ATRAS_GENERAR_ESTADO = "atras generar estado";
	
	private VentanaGenerarEstado principal;
	private JButton butAtras;
	private JButton butGenerar;
	
	public PanelResultados(VentanaGenerarEstado ventanaGenerarEstado) {
		principal= ventanaGenerarEstado;
		inicializarComponentes();
		// TODO Auto-generated constructor stub
	}

	private void inicializarComponentes() {
		this.setBorder(BorderFactory.createTitledBorder("Generar Estado"));
		this.setLayout(new GridLayout(1,5));
		this.setPreferredSize(new Dimension(500,50));
		butAtras = new JButton("Atras");
		butAtras.setBackground(PanelBotones.COLOR_BOTONES);
		butAtras.addActionListener(this);
		butAtras.setActionCommand(BUT_ATRAS_GENERAR_ESTADO);
		butGenerar = new JButton("Generar");
		butGenerar.setBackground(PanelBotones.COLOR_BOTONES);
		butGenerar.setActionCommand(this.BUT_GENERAR_ESTADO);
		butGenerar.addActionListener(this);
		this.add(new JLabel());
		this.add(butGenerar);
		this.add(new JLabel());
		this.add(butAtras);
		this.add(new JLabel());
		
	}

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(BUT_ATRAS_GENERAR_ESTADO)) {
			principal.botonAtras();
		}
		else {
			
			try {
				principal.generarEstado();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al insertar datos", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		
		
	}

}
