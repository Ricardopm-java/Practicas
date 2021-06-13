package Ventanas_bis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VentanaMenu extends JFrame {

	
	public VentanaMenu() {
		super("Menú de opciones");
		JFrame ventana =  new JFrame();
		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
	
		
		
		
		JButton escribirResena = new JButton("Escribir una nueva reseña");
		escribirResena.setAlignmentX(CENTER_ALIGNMENT);
		principal.add(escribirResena);
		escribirResena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaResena();
				
			}
		});
		
		JButton listaUsuarios = new JButton("Lista de los usuarios registrados");
		principal.add(listaUsuarios);
		listaUsuarios.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton seguir = new JButton("Seguir a un usuario");
		principal.add(seguir);
		seguir.setAlignmentX(CENTER_ALIGNMENT);
		seguir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaSeguir();
				
			}
		});
		
		JButton opiniones = new JButton("Opiniones de un usuario");
		principal.add(seguir);
		opiniones.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton lugar = new JButton("Oponiones sobre un lugar");
		principal.add(seguir);
		lugar.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton salir = new JButton("Salir");
		principal.add(seguir);
		salir.setAlignmentX(CENTER_ALIGNMENT);
		salir.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventana.setVisible(false);
				ventana.dispose();
				
			}
		});
		
		ventana.setVisible(true);
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	
	
}
