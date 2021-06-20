package Ventanas;

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

public class VentanaOpinionesUsuario extends JFrame {

	public VentanaOpinionesUsuario(Cliente cliente) {
		super("Opiniones de los usuarios");

		JPanel principal = new JPanel(new BorderLayout());

		JLabel encabezado = new JLabel("De quién quieres conocer sus opiniones.");
		principal.add(encabezado, BorderLayout.NORTH);

		JPanel central = new JPanel(new BorderLayout());
		principal.add(central);
		
		JLabel usuario = new JLabel("Usuario: ");
		JTextField textoUsuario = new JTextField(20);
		central.add(usuario, BorderLayout.WEST);
		central.add(textoUsuario, BorderLayout.EAST);
		
		
		String nombreConsultas = textoUsuario.getText();
		
		JButton consultar = new JButton("Consultar");
		principal.add(consultar, BorderLayout.SOUTH);

		consultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cliente.enviar(nombreConsultas);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		this.add(principal);
		
		pack();

	}
}
