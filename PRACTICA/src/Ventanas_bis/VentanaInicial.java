package Ventanas_bis;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Vista.Cliente;


public class VentanaInicial extends JFrame {
	
	
	private JTextField nombre =  new JTextField("usuario", 20);
	private JTextField pass = new JTextField("contraseña", 20);
	private JButton login;
	public String usuarioNombre = nombre.getText();

	
	public VentanaInicial() {
		super("Login");
		
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
				String usuario = nombre.getText();
				String contrasena = pass.getText();
				String total = usuario+"#"+contrasena;
				try {
					new Cliente().enviar(total);
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
	
	public static void main(String[] args) {
		new VentanaInicial().setVisible(true);
	}
}
