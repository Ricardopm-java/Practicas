package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.internal.bind.JsonTreeWriter;

import Modelo.Resena;
import Vista.Cliente;



public class VentanaResena extends JFrame{

	private Resena resena;
	String nikResena;
	String lugarResena;
	String calificacionResena;
	String opinionResena;
	String notaResena;
	JFrame ventana = new JFrame();
	
	
	public VentanaResena(Cliente cliente) {
		super("Escribir una reseña");
		
		JPanel principal = new JPanel(new BorderLayout());
		
		principal.add(panelSuperior(), BorderLayout.NORTH);
		principal.add(panelInferior(), BorderLayout.SOUTH);
		
		resena = new Resena(lugarResena, calificacionResena, opinionResena, notaResena, nikResena);
		
		
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
	private JPanel panelSuperior() {
		JPanel superior = new JPanel(new BorderLayout());
		
		
		JLabel lugar = new JLabel("Lugar:");
		JTextField textoLugar = new JTextField(50);
		JLabel calificacion = new JLabel("Calificación:");
		JTextField textoCalificacion = new JTextField(50);
		
		JPanel arriba = new JPanel();
		arriba.add(lugar, BorderLayout.EAST);
		arriba.add(textoLugar, BorderLayout.WEST);
		
		JPanel abajo = new JPanel();
		abajo.add(calificacion, BorderLayout.EAST);
		abajo.add(textoCalificacion, BorderLayout.WEST);
		
		superior.add(arriba, BorderLayout.NORTH);
		superior.add(abajo, BorderLayout.SOUTH);
		
		lugarResena = textoLugar.getText();
		calificacionResena = textoCalificacion.getText();
		
		return superior;
	}
	
	
	
	private JPanel panelInferior() {
		JPanel inferior = new JPanel(new BorderLayout());
		
		JLabel opinion = new JLabel("Opinion:");
		JTextField textoOpinion = new JTextField(50);
		JLabel nota = new JLabel("Nota:");
		JTextField textoNota = new JTextField(50);
		
		JPanel arriba = new JPanel();
		arriba.add(opinion, BorderLayout.EAST);
		arriba.add(textoOpinion, BorderLayout.WEST);
		
		JPanel centro = new JPanel();
		centro.add(nota, BorderLayout.EAST);
		centro.add(textoNota, BorderLayout.WEST);
		
		
		inferior.add(arriba, BorderLayout.NORTH);
		inferior.add(centro, BorderLayout.CENTER);
		
		JButton enviar = new JButton("Enviar");
		
		opinionResena = textoOpinion.getText();
		notaResena = textoNota.getText();
		
		enviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//enviar la reseña
				
				ArrayList<Resena> nueva = new ArrayList<Resena>();
				nueva.add(resena);
								
				try {
					cliente.enviar(new Gson().toJson(nueva).toString());
					ventana.dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		inferior.add(enviar, BorderLayout.SOUTH);
		
		return inferior;
	}
	
	
}
