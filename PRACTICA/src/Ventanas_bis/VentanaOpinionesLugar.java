package Ventanas_bis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vista.Cliente;

public class VentanaOpinionesLugar extends JFrame {

	public VentanaOpinionesLugar() {
		super("Opiniones sobre un lugar");
		
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel(new BorderLayout());
		JLabel encabezado = new JLabel("De que lugar quieres conocer sus opiniones.");
		principal.add(encabezado, BorderLayout.NORTH);
		
		JPanel central = new JPanel();
		JLabel lugar = new JLabel("Lugar: ");
		JTextField textoLugar = new JTextField(50);
		central.add(lugar, BorderLayout.WEST);
		central.add(textoLugar, BorderLayout.EAST);
		principal.add(central);
		
		JButton consultar = new JButton("Consultar");
		principal.add(consultar, BorderLayout.SOUTH);
		
		
		consultar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					new Cliente().enviar(textoLugar.getText());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		
		
	}
}
