package Ventanas;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.fasterxml.jackson.databind.ObjectMapper;

import Modelo.Resena;

public class VentanaMostrar extends JFrame{

	public VentanaMostrar(String resultadoRecibido) {
		super("Resultados de la petición");
		
		JPanel principal = new JPanel(new BorderLayout());

		JLabel encabezado = new JLabel(resultadoRecibido);
		principal.add(encabezado, BorderLayout.NORTH);

		JPanel central = new JPanel();
		principal.add(central, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		this.add(principal);

		pack();
	}
	
}
