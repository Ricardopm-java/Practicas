package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Modelo.BBDD;
import Modelo.Usuario;

public class UsuarioDAO implements DAO<Usuario> {

	private String nombre;
	private String contrasena;

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

		System.out.println("usuario: " + usuarioOK);
		return usuarioOK;

	}

	public boolean tieneResena(Usuario usuario) {
		boolean usuarioOK = false;
		String SQL_SELECT = "SELECT USUARIO FROM RESENAS WHERE USUARIO='" + usuario.getUsuario() + "'";

		try {
			Connection conn = BBDD.get();
			;
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
			;
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

		System.out.println("usuario: " + usuarioOK);
		return usuarioOK;
	}

	public boolean registrarUsuario(Usuario usuario) {

		boolean registrado = false;
		// String SQL_SELECT = "INSERT INTO USUARIOS (USUARIO, PASS) VALUES
		// ('"+usuario.getUsuario()+"', '"+usuario.getPass()+"')";

		try {
			Connection conn = BBDD.get();
//			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
//			preparedStatement.executeUpdate();

			CallableStatement st = conn.prepareCall("{EXECUTE P_REGISTRAR_USUARIO (?,?) }");

			st.setString(1, usuario.getUsuario());
			st.setString(2, usuario.getPass());
			int resultado = st.executeUpdate();
			if (resultado == 1)	registrado = true;
		} catch (SQLException e) {
			System.out.println("No se ha podido registrar al usuario");
			e.printStackTrace();
			registrado = false;
		}

		return registrado;
	}

	
	public ArrayList<Usuario> listar(String interesado) {
		ArrayList<Usuario> resultado = new ArrayList<Usuario>();
		String SQL_SELECT = "{CALL F_USUARIOSREGISTRADOS (?) }";
		try {

			Connection conn = BBDD.get();
			
			// PreparedStatement st = conn.prepareStatement(SQL_SELECT);
			CallableStatement st = conn.prepareCall(SQL_SELECT); // uno para el registro de actividad (entrada) y otro
																	// para mostrar ne la lista (salida)

			
			st.setString(1, interesado);
			// st.execute();
			ResultSet resultSet = st.executeQuery(SQL_SELECT);
			// String resul = st.getString(1);
			while (resultSet.next()) {
				resultado.add(recomponerUsuario(resultSet.getString("USUARIO")));
			}
		} catch (SQLException e) {
			System.out.println("No se ha podido mostrar la lista de usuarios registrados");
			e.printStackTrace();
		}

		return resultado;

	}

	private Usuario recomponerUsuario(String actual) throws SQLException {

		return new Usuario(actual);
	}

	public boolean seguir(String usuario, String seguido) {
		boolean seguidoOK = false;
		try {
			if (comprobarseguido(seguido)) {

				try {
					Connection conn = BBDD.get();

					CallableStatement st = conn.prepareCall("{EXECUTE P_SEGUIR (?,?)}");
					st.setString(1, usuario);
					st.setString(2, seguido);
					st.executeUpdate();
					seguidoOK = true;

				} catch (SQLException e) {
					seguidoOK = false;
					System.out.println("No se ha podido añadir a la lista de seguidos");
					e.printStackTrace();
				}
			} else {
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
		String SQL_SELECT = "SELECT * FROM SEGUIDOS WHERE USUARIO = '" + seguido + "'";
		Connection conn = BBDD.get();

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
		ResultSet resultSet = preparedStatement.executeQuery(); // ejecutamos la sentencia
		if (resultSet != null)
			comprobado = true;
		else
			comprobado = false;

		return comprobado;
	}


	@Override
	public boolean seguir(String seguido) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Usuario> listar() {
		// TODO Auto-generated method stub
		return null;
	}

}
