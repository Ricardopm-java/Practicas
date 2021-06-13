package Ventanas_bis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;



public class VentanaResena extends JFrame{

	
	public VentanaResena() {
		super("Escribir una reseña");
		JFrame ventana = new JFrame();
		JPanel principal = new JPanel(new BorderLayout());
		
		principal.add(panelSuperior(), BorderLayout.NORTH);
		principal.add(panelInferior(), BorderLayout.SOUTH);
		
		
		ventana.setVisible(true);
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
		enviar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//enviar la reseña
				
			}
		});
		
		inferior.add(enviar, BorderLayout.SOUTH);
		
		return inferior;
	}
	
	
}
