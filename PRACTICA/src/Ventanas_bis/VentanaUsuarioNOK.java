package Ventanas_bis;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VentanaUsuarioNOK extends JFrame {
	
	public VentanaUsuarioNOK() {
		super("Login incorrecto");
		JFrame ventana = new JFrame();
		
		JPanel principal = new JPanel(new BorderLayout());
		ventana.add(principal);
		JLabel titulo = new JLabel("Usuario no existente");
		principal.add(titulo, BorderLayout.NORTH);
		titulo.setHorizontalAlignment(0);
		
		JLabel registro = new JLabel("Quieres registrarte?");
		registro.setHorizontalAlignment(0);
		principal.add(registro, BorderLayout.CENTER);
		
		principal.add(panelSur(), BorderLayout.SOUTH);	
		
		ventana.setVisible(true);
		this.add(principal);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		
	}
	
	private JPanel panelSur() {
		JPanel sur = new JPanel();
		JButton si = new JButton("SI");
		si.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro();
				
			}
		});
		
		JButton no = new JButton("NO");
		no.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaInicial();
				
				
			}
		});
		sur.add(si);
		sur.add(no);
		
		return sur;
	}
	
	
}
