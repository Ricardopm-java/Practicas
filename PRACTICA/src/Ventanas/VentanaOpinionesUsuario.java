package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.Resena;
import Vista.Cliente;

public class VentanaOpinionesUsuario extends JFrame {
	String nombreConsultas;
	JTextField textoUsuario;
	public VentanaOpinionesUsuario(Cliente cliente) {
		super("Opiniones de los usuarios");

		JPanel principal = new JPanel(new BorderLayout());

		JLabel encabezado = new JLabel("De quién quieres conocer sus opiniones.");
		principal.add(encabezado, BorderLayout.NORTH);

		JPanel central = central();
		principal.add(central, BorderLayout.CENTER);

		JButton consultar = new JButton("Consultar");
		principal.add(consultar, BorderLayout.SOUTH);

		consultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					nombreConsultas = textoUsuario.getText(); 
					cliente.enviar(nombreConsultas);
					String mensaje = cliente.lectura();
					ObjectMapper mapper = new ObjectMapper();
					Resena[] array = mapper.readValue(mensaje, Resena[].class);

					new VentanaMostrar(Arrays.toString(array)).setVisible(true);

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

	private JPanel central() {
		JPanel central = new JPanel(new BorderLayout());
		JLabel usuario = new JLabel("Usuario: ");
		textoUsuario = new JTextField(20);
		central.add(usuario, BorderLayout.WEST);
		central.add(textoUsuario, BorderLayout.EAST);
		
		
		return central;
	}
}
