package interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBanner extends JPanel {

	private ImageIcon imagen;
	private JLabel banner;
	
	public PanelBanner(String ruta) {
		inicializarComponentes(ruta);


		
		
	}
	
	public void inicializarComponentes(String ruta) {
		this.setLayout(new BorderLayout());
		this.setBorder(BorderFactory.createTitledBorder("Banner"));
		this.setPreferredSize(new Dimension(836,557));
		banner = new JLabel();
		imagen= new ImageIcon(ruta);
		Image aux = imagen.getImage();
		Image nuevaImagen=aux.getScaledInstance(836, 557, Image.SCALE_SMOOTH);
		imagen.setImage(nuevaImagen);
		banner.setIcon(imagen);
		this.add(banner,BorderLayout.CENTER);
	}
	
	
	
	
	
}
