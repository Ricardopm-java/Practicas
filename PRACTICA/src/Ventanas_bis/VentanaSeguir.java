package Ventanas_bis;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaSeguir extends JFrame {

	
	public VentanaSeguir() {
		super("Seguir a un usuario registrado");
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel(new BorderLayout());
		JLabel encabezado = new JLabel("Dime el nombre del usuario que deseas seguir");
		principal.add(encabezado, BorderLayout.NORTH);
		
		JPanel central = new JPanel(new BorderLayout());
		JLabel nombre = new JLabel("Nombre:");
		JTextField textoNombre = new JTextField(20);
		central.add(nombre, BorderLayout.EAST);
		central.add(textoNombre, BorderLayout.WEST);
		principal.add(central, BorderLayout.CENTER);
		
		JButton seguir = new JButton("Seguir");
		principal.add(seguir, BorderLayout.SOUTH);		
		
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		pack();
	}
}
