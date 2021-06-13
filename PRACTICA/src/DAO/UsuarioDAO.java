package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.CallableStatement;

import Modelo.BBDD;
import Modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

	private String nombre;
	private String contrasena;
	private boolean seguido;

	public UsuarioDAO(String nombre, String contrasena) {
		super();
		this.nombre = nombre;
		this.contrasena = contrasena;
	}

	public UsuarioDAO(String nombre) {
		super();
		this.nombre = nombre;
	}

	public UsuarioDAO() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public boolean comprobarUsuario(Usuario usuario) {

		boolean usuarioOK = false;
		String SQL_SELECT = "SELECT * FROM USUARIOS WHERE USUARIO=? AND PASS=? ";

		try {
			Connection conn = BBDD.get();
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			preparedStatement.setString(1, usuario.getUsuario());
			preparedStatement.setString(2, usuario.getPass());
			ResultSet resultSet = preparedStatement.executeQuery(); 

			while (resultSet.next()) {
				String nombre = resultSet.getString("USUARIO");
				String pass = resultSet.getString("PASS");
				if (nombre.equalsIgnoreCase(usuario.getUsuario()) && pass.equals(usuario.getPass()))
					usuarioOK = true;
				else {
					usuarioOK = false;
					
				}
			}

//			conn.close();

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("usuario: "+usuarioOK);
		return usuarioOK;

	}
	
	public boolean tieneResena(Usuario usuario) {
		boolean usuarioOK = false;
		String SQL_SELECT = "SELECT USUARIO FROM RESENAS WHERE USUARIO='"+usuario.getUsuario()+"'";

		try {
			Connection conn = BBDD.get();
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			ResultSet resultSet = preparedStatement.executeQuery(); 

			if (resultSet.next()) {
					usuarioOK = true;
			} 
			
		// con.close();
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return usuarioOK;
	}
	
	public boolean comprobarSolicitado(Usuario usuario) {
		boolean usuarioOK = false;
		String SQL_SELECT = "SELECT USUARIO FROM USUARIOS WHERE USUARIO=?";

		try {
			Connection conn = BBDD.get();
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			preparedStatement.setString(1, usuario.getUsuario());
			
			ResultSet resultSet = preparedStatement.executeQuery(); 

			while (resultSet.next()) {
				String nombre = resultSet.getString("USUARIO");
				
				if (nombre.equalsIgnoreCase(usuario.getUsuario()))
					usuarioOK = true;
				else {
					usuarioOK = false;
					
				}
			}

//			conn.close();

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("usuario: "+usuarioOK);
		return usuarioOK;
	}
	
	

	public boolean registrarUsuario(Usuario usuario) {

		boolean registrado;
		//String SQL_SELECT = "INSERT INTO USUARIOS (USUARIO, PASS) VALUES ('"+usuario.getUsuario()+"', '"+usuario.getPass()+"')";

		try {
			Connection conn = BBDD.get();
//			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
//			preparedStatement.executeUpdate();

			CallableStatement st = (CallableStatement) conn.prepareCall( "{REGISTRAR_USUARIO (?,?)}");
			
			st.setString(1, usuario.getUsuario());
			st.setString(2, usuario.getPass());
			
			registrado = st.execute();
			
		} catch (SQLException e) {
			System.out.println("No se ha podido registrar al usuario");
			registrado = false;
		}

		return registrado;
	}

	@Override
	public ArrayList<Usuario> listar() {
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		//String SQL_SELECT = "SELECT * FROM USUARIOS";
		try {

			Connection conn =  BBDD.get();
			//PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			CallableStatement st = (CallableStatement) conn.prepareCall( "{CALL REGISTRO.F_USUARIOSREGISTRADOS (?,?) }");
			st.registerOutParameter(1, Types.VARCHAR);
			st.setString(2, nombre);
			
			ResultSet resultSet = st.executeQuery();
			while (resultSet.next()) {
				resultado.add(recomponerUsuario(resultSet));
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido mostrar la lista de usuarios registrados");
			e.printStackTrace();
		}

		return resultado;

	}
	
	private Usuario recomponerUsuario(ResultSet actual) throws SQLException {
				
		return new Usuario(actual.getString("USUARIO"));
	}

	public boolean seguir(String usuario,String seguido) {
		boolean seguidoOK = false;
		try {
			if(comprobarseguido(seguido)) {
			

			try {
				Connection conn = BBDD.get();

				CallableStatement st = (CallableStatement) conn.prepareCall( "{REGISTRO.P_SEGUIR (?,?)}");
				st.setString(1, usuario);
				st.setString(2, seguido);
				
				seguidoOK = true;
				

			} catch (SQLException e) {
				seguidoOK = false;
				System.out.println("No se ha podido añadir a la lista de seguidos");
				e.printStackTrace();
			}
			}else {
				seguidoOK = false;
			}
		} catch (SQLException e) {
			System.out.println("El usuario indicado ya esta seguido.");
			e.printStackTrace();
		}

		return seguidoOK;
	}

	private boolean comprobarseguido(String seguido) throws SQLException {
		boolean comprobado;
		String SQL_SELECT = "SELECT * FROM SEGUIDOS WHERE USUARIO = '"+seguido+"'";
		Connection conn = BBDD.get();

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
		ResultSet resultSet = preparedStatement.executeQuery(); // ejecutamos la sentencia
		if(resultSet != null) comprobado = true;
		else comprobado = false;
		
		return comprobado;
	}
	
	private void registrarMovimiento(Usuario usuario) {
		
	}

	@Override
	public boolean seguir(String seguido) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
