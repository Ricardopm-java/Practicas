package Ventanas;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import Vista.Cliente;

public class VentanaMenu extends JFrame {

	private String nik;
	private String eleccion;
	private Cliente cliente;


	public VentanaMenu(Cliente cliente) {
		super("Menú de opciones");
		
		JFrame ventana =  new JFrame();
		JPanel principal = new JPanel();
		principal.setLayout(new BoxLayout(principal, BoxLayout.Y_AXIS));
	
		//nik = usuarioNombre;
		
		
		JButton escribirResena = new JButton("Escribir una nueva reseña");
		escribirResena.setAlignmentX(CENTER_ALIGNMENT);
		principal.add(escribirResena);
		escribirResena.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = "resena";
				try {
					cliente.enviar(eleccion);
					new VentanaResena(cliente).setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		JButton listaUsuarios = new JButton("Lista de los usuarios registrados");
		principal.add(listaUsuarios);
		listaUsuarios.setAlignmentX(CENTER_ALIGNMENT);
		listaUsuarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = "lista";
				try {
					cliente.enviar(eleccion);
					cliente.listadoUsuarios();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		JButton seguir = new JButton("Seguir a un usuario");
		principal.add(seguir);
		seguir.setAlignmentX(CENTER_ALIGNMENT);
		seguir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = "seguir";
				try {
					cliente.enviar(eleccion);
					new VentanaSeguir(cliente).setVisible(true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		
		JButton opiniones = new JButton("Opiniones de un usuario");
		principal.add(opiniones);
		opiniones.setAlignmentX(CENTER_ALIGNMENT);
		opiniones.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					eleccion = "opiniones";
					cliente.enviar(eleccion);
					cliente.opinionesUsuario();
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton lugar = new JButton("Oponiones sobre un lugar");
		principal.add(lugar);
		lugar.setAlignmentX(CENTER_ALIGNMENT);
		lugar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = "lugar";
				try {
					cliente.enviar(eleccion);
					cliente.opninionesLugar();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JButton salir = new JButton("Salir");
		principal.add(salir);
		salir.setAlignmentX(CENTER_ALIGNMENT);
		salir.addActionListener(new AbstractAction() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				eleccion = "salir";
				try {
					cliente.enviar(eleccion);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ventana.setVisible(false);
				ventana.dispose();
				
			}
		});
		
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	
	
}
