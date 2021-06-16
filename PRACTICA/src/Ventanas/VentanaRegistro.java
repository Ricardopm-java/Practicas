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
import javax.swing.border.Border;

import com.google.gson.Gson;

import Modelo.Usuario;
import Vista.Cliente;

public class VentanaRegistro extends JFrame {
	
	private Usuario usuarioNuevo;
	private String usuario;
	private String pass;
	private Cliente cliente;

	public Usuario VentanaRegistro() {
		
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel(new BorderLayout());
		ventana.add(principal);

		JLabel titulo = new JLabel("Ventana de registro");
		principal.add(titulo, BorderLayout.NORTH);

		principal.add(panelCentral(), BorderLayout.CENTER);
		principal.add(panelSur(), BorderLayout.SOUTH);
		
		usuarioNuevo = new Usuario(usuario, pass);
		
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		pack();
		
		return usuarioNuevo;
	}

	private JPanel panelCentral() {

		JPanel central = new JPanel();
		JPanel centralSuperior = new JPanel(new BorderLayout());
		JLabel nombre = new JLabel("Escribe un nik:");
		JTextField textoNombre = new JTextField();
		centralSuperior.add(nombre, BorderLayout.NORTH);
		centralSuperior.add(textoNombre, BorderLayout.SOUTH);

		JPanel centralInferior = new JPanel(new BorderLayout());
		JLabel contrasena = new JLabel("Escribe una contraseña:");
		JTextField textoContrasena = new JTextField();
		centralInferior.add(contrasena, BorderLayout.NORTH);
		centralInferior.add(textoContrasena, BorderLayout.SOUTH);
		
		usuario = textoNombre.getText();
		pass = textoContrasena.getText();

		central.add(centralSuperior);
		central.add(centralInferior);

		return central;
	}
	
	private JPanel panelSur() {
		JPanel sur = new JPanel(new BorderLayout());
		JButton enviar = new JButton("Registrarse");
		sur.add(enviar, BorderLayout.CENTER);
		
		enviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					cliente.enviar(new Gson().toJson(usuarioNuevo).toString());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		return sur;
	}

	

}
