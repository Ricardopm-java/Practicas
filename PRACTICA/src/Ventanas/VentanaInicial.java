package Ventanas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vista.Cliente;

public class VentanaInicial extends JFrame {

	JTextField nombre = new JTextField("usuario", 20);
	JTextField pass = new JTextField("contraseña", 20);
	JButton login;
	String usuario;
	String contrasena;
	
	
	

	public VentanaInicial() throws HeadlessException {
		super("Loggin");
		crearVentana();
	}

	public void crearVentana() {
		
		
		JFrame ventana = new JFrame();

		JPanel principal = new JPanel(new BorderLayout());
		ventana.add(principal);
		principal.setPreferredSize(new Dimension(500, 65));

		principal.add(nombre, BorderLayout.NORTH);
		principal.add(pass, BorderLayout.CENTER);
		login = new JButton("LOGIN");
		principal.add(login, BorderLayout.SOUTH);
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				usuario = nombre.getText();
				contrasena = pass.getText();
			}
		});

		String total = usuario + "#" + contrasena;
		try {
			new Cliente().enviar(total);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		pack();

	}
	
	public void VentanaInicialNOK() {
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel();
		ventana.add(principal);
		JLabel encabezado = new JLabel("Usuario no encontrado");
		principal.add(encabezado, BorderLayout.NORTH);
		
		JPanel izq = new JPanel();
		
		JButton op1 = new JButton("Reintentar");
		izq.add(op1,BorderLayout.SOUTH);
		principal.add(izq, BorderLayout.WEST);
		op1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInicial nueva = new VentanaInicial();
				nueva.setVisible(true);
				
				
			}
		});
		JPanel der = new JPanel();
		JButton op2 = new JButton("Registro");
		der.add(op2);
		op2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					new Cliente().enviar("registro");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				new VentanaRegistro().setVisible(true);
				
				
			}
		});
		principal.add(der, BorderLayout.EAST);
		
		
	}

	
}