package Ventanas;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.google.gson.JsonElement;

public class VentanaMostrar extends JFrame{

	public VentanaMostrar(String resultadoRecibido) {
		super("Resultados de la petición");
		
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel(new BorderLayout());
		ventana.add(principal);
		JScrollPane panelResultado = new JScrollPane();
		JTextArea resultado = new JTextArea();
		panelResultado.add(resultado);
		principal.add(panelResultado);
		
		resultado.setText(resultadoRecibido);
		
		ventana.setVisible(true);
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
	}
	
}
