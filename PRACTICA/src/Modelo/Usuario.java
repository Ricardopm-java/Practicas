package Modelo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable{

	private String usuario;
	private String pass;
	private List<String> seguidos;
	
	
	public Usuario(String usuario, String pass) {
		super();
		this.usuario = usuario;
		this.pass = pass;
	}

	

	public Usuario(String usuario) {
		super();
		this.usuario = usuario;
	}



	public Usuario(String seguido, boolean seguir) {
		
	}



	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getPass() {
		return pass;
	}


	public void setPass(String pass) {
		this.pass = pass;
	}



	@Override
	public String toString() {
		return usuario +" ";
	}
	
	
	
}
