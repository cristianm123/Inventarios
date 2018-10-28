package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBotonesIndicadores extends JPanel implements ActionListener {

	public static final String BUT_ATRAS_INDICADORES ="atras_indicadores";
	
	private JButton butAtras;
	private VentanaIndicadores principal;
	
	public PanelBotonesIndicadores(VentanaIndicadores p) {
		principal = p;
		inicializarComponentes();
	}

	private void inicializarComponentes() {
		this.setPreferredSize(new Dimension(150,50));
		butAtras = new JButton("Atras");
		butAtras.setActionCommand(this.BUT_ATRAS_INDICADORES);
		butAtras.setBackground(PanelBotones.COLOR_BOTONES);
		butAtras.addActionListener(this);
		this.setLayout(new GridLayout(1,3));
		this.setBorder(BorderFactory.createTitledBorder("Atras"));
		this.add(new JLabel());
		this.add(butAtras,BorderLayout.CENTER);
		this.add(new JLabel());
	}
	

	@Override
	public void actionPerformed(ActionEvent evento) {
		String comando = evento.getActionCommand();
		if(comando.equals(BUT_ATRAS_INDICADORES)) {
			principal.atras();
		}
		// TODO Auto-generated method stub
		
	}
	
	
}
