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

import com.google.gson.Gson;

import Modelo.Resena;
import Modelo.ResenaEscribir;
import Vista.Cliente;

public class VentanaResena extends JFrame {

	
	private ResenaEscribir resena;
	String usuario;
	String lugarResena;
	String calificacionResena;
	String opinionResena;
	String notaResena;
	JTextField textoLugar;
	JTextField textoCalificacion;
	JTextField textoOpinion;
	JTextField textoNota;

	public VentanaResena(Cliente cliente) {
		super("Escribir una reseña");

		JPanel principal = new JPanel(new BorderLayout());

		principal.add(panelSuperior(), BorderLayout.NORTH);
		principal.add(panelInferior(cliente), BorderLayout.SOUTH);

		this.add(principal);

		pack();
	}

	private ResenaEscribir captarTexto() {
		lugarResena = textoLugar.getText();
		calificacionResena = textoCalificacion.getText();
		opinionResena = textoOpinion.getText();
		notaResena = textoNota.getText();
		resena = new ResenaEscribir(lugarResena, calificacionResena, opinionResena, notaResena);
		return resena;

	}

	private JPanel panelSuperior() {
		JPanel superior = new JPanel(new BorderLayout());

		JLabel lugar = new JLabel("Lugar:");
		textoLugar = new JTextField(50);
		JLabel calificacion = new JLabel("Calificación:");
		textoCalificacion = new JTextField(50);

		JPanel arriba = new JPanel();
		arriba.add(lugar, BorderLayout.EAST);
		arriba.add(textoLugar, BorderLayout.WEST);

		JPanel abajo = new JPanel();
		abajo.add(calificacion, BorderLayout.EAST);
		abajo.add(textoCalificacion, BorderLayout.WEST);

		superior.add(arriba, BorderLayout.NORTH);
		superior.add(abajo, BorderLayout.SOUTH);

		return superior;
	}

	private JPanel panelInferior(Cliente cliente) {
		JPanel inferior = new JPanel(new BorderLayout());

		JLabel opinion = new JLabel("Opinion:");
		textoOpinion = new JTextField(50);
		JLabel nota = new JLabel("Nota:");
		textoNota = new JTextField(50);

		JPanel arriba = new JPanel();
		arriba.add(opinion, BorderLayout.EAST);
		arriba.add(textoOpinion, BorderLayout.WEST);

		JPanel centro = new JPanel();
		centro.add(nota, BorderLayout.EAST);
		centro.add(textoNota, BorderLayout.WEST);

		inferior.add(arriba, BorderLayout.NORTH);
		inferior.add(centro, BorderLayout.CENTER);

		JButton enviar = new JButton("Enviar");

		enviar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// enviar la reseña

				try {
					ResenaEscribir resenaEscrita = captarTexto();
					cliente.enviar(new Gson().toJson(resenaEscrita).toString());
					dispose();
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
