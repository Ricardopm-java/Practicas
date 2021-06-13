package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.jdbc.CallableStatement.CallableStatementParamInfo;
import com.mysql.cj.jdbc.JdbcConnection;

import Modelo.BBDD;
import Modelo.Resena;
import Modelo.Usuario;

public class ResenaDAO implements DAO {
	
	
	private String lugar;
	private String calificacion;
	private String opinion;
	private String notas;
	private String usuario;

	
	
	public ResenaDAO() {
		super();
	}

	public boolean nuevo (Resena resena) {

		boolean resenaGuardada;
		//String SQL_SELECT = "INSERT INTO RESENAS (USUARIO, LUGAR, CALIFICACION, OPINION, NOTA) VALUES (?,?,?,?,?) ";
		
		try {
			Connection conn = BBDD.get();
			//PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			CallableStatement st = (CallableStatement) conn.prepareCall( "{REGISTRO.REGISTRAR_RESENAS (?,?,?,?,?)}");
			
			st.setString(1, resena.getUsuario());
			st.setString(2, resena.getLugar());
			st.setString(3, resena.getCalificacion());
			st.setString(4, resena.getOpinion());
			st.setString(5, resena.getNotas());
			
			boolean result = st.execute(); // ejecutamos la sentencia
			
			resenaGuardada = result;
			
			
		} catch (SQLException e) {
			resenaGuardada = false;
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			resenaGuardada = false;
		}

		return resenaGuardada;
	}

	@Override
	public ArrayList<String> listar() {
		return null;
		
		
	}

	
	public ArrayList<Resena> opinionesLugar(String lugar) {
		ArrayList<Resena> resultado = new ArrayList<Resena>();
		
		try {
			Connection conn = BBDD.get();
			CallableStatement st = (CallableStatement) conn.prepareCall( "{CALL REGISTRO.F_RESENASPORLUGAR (?,?,?,?,?) }");
			st.registerOutParameter(1, Types.VARCHAR);
			st.registerOutParameter(2, Types.VARCHAR);
			st.registerOutParameter(3, Types.VARCHAR);
			st.registerOutParameter(4, Types.VARCHAR);
			
			st.setString(5, lugar);
			ResultSet rs = st.executeQuery();
				
			
			
			while(rs.next()) {
				
				resultado.add(new Resena(st.getString(1), st.getString(2), st.getString(3), st.getString(4)));
			}
		}catch(SQLException e) {
			e.getMessage();
		}
		
		return resultado;
		
	}
	
	public ArrayList<Resena> opinionesUsuario(Usuario usuario) {

		ArrayList<Resena> resultado = new ArrayList<Resena>();
		String nombre = usuario.getUsuario();

		String SQL_SELECT = "SELECT LUGAR, CALIFICACION, OPINION FROM RESENAS WHERE USUARIO = '"+nombre+"'  ";
		try {
			Connection conn = BBDD.get();

			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String lugar = resultSet.getString("LUGAR");
				String calificacion = resultSet.getString("CALIFICACION");
				String opinion = resultSet.getString("OPINION");
				
				resultado.add(new Resena(lugar, calificacion, opinion));
				
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println("No se puede mostrar las reseñas.");
			e.printStackTrace();
		}

		return resultado;
	}
	
	public boolean comprobarLugar(String lugarBuscado) {
		boolean existe = false;
		String SQL_SELECT = "SELECT LUGAR FROM RESENAS WHERE LUGAR = '"+lugarBuscado+"'";
		try {
			Connection conn = BBDD.get();
			PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				existe = true;
			}
		}catch(SQLException e) {
			System.out.println("Lugar elegido no valido.");
			existe = false;
		}
		
		return existe;
	}


	@Override
	public boolean seguir(String seguido) {
		
		return false;
	}
	

}
