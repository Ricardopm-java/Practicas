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

public class VentanaOpinionesLugar extends JFrame {

	private JTextField textoUsuario;

	public VentanaOpinionesLugar(Cliente cliente) {

		JPanel principal = new JPanel(new BorderLayout());

		JLabel encabezado = new JLabel("De qué lugar conocer las opiniones.");
		principal.add(encabezado, BorderLayout.NORTH);

		JPanel central = central();
		principal.add(central, BorderLayout.CENTER);

		JButton consultar = new JButton("Consultar");
		principal.add(consultar, BorderLayout.SOUTH);

		consultar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String nombreConsultas = textoUsuario.getText(); 
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
		JLabel usuario = new JLabel("Lugar: ");
		textoUsuario = new JTextField(20);
		central.add(usuario, BorderLayout.WEST);
		central.add(textoUsuario, BorderLayout.EAST);
		
		
		return central;
	}
		
		
		/*super("Opiniones sobre un lugar");
		
		

		JPanel principal = new JPanel(new BorderLayout());
		JLabel encabezado = new JLabel("De que lugar quieres conocer sus opiniones.");
		principal.add(encabezado, BorderLayout.NORTH);
		this.add(principal);

		JPanel central = new JPanel(new BorderLayout());
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
					String lugarIndicado = textoLugar.getText();
					cliente.enviar(lugarIndicado);
					
					String mensaje = cliente.lectura();
					ObjectMapper mapper = new ObjectMapper();
					Resena[] lista = mapper.readValue(mensaje, Resena[].class);

					new VentanaMostrar(lista.toString()).setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		this.add(principal);

		pack();

	}*/
	
}
