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
import Modelo.Resena;
import Modelo.Usuario;
import oracle.jdbc.OracleTypes;

public class ResenaDAO implements DAO {
	
	public ResenaDAO() {
		super();
	}

	public boolean nuevo (Resena resena) {

		boolean resenaGuardada = false;
		//String SQL_SELECT = "INSERT INTO RESENAS (USUARIO, LUGAR, CALIFICACION, OPINION, NOTA) VALUES (?,?,?,?,?) ";
		
		try {
			Connection conn = BBDD.get();
			//PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			CallableStatement st = conn.prepareCall("CALL P_REGISTRAR_RESENAS (?,?,?,?,?)");
			System.out.println(resena.toString());
			st.setString(1, resena.getUsuario());
			st.setString(2, resena.getLugar());
			st.setString(3, resena.getOpinion());
			st.setString(4, resena.getNotas());
			st.setString(5, resena.getCalificacion());
			
			int filaInsertada = st.executeUpdate(); // ejecutamos la sentencia
			
			 resenaGuardada = true;
			
			
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

	
	public ArrayList<Resena> opinionesLugar(String lugar, String usuario) {
		ArrayList<Resena> resultado = new ArrayList<Resena>();
		
		try {
			Connection conn = BBDD.get();
			CallableStatement st = conn.prepareCall( "{? = call F_RESENAPORLUGAR (?,?)}");
			st.registerOutParameter(1, OracleTypes.CURSOR);
			st.setString(2, lugar);
			st.setString(3, usuario);
			st.execute();
			ResultSet rs = (ResultSet) st.getObject(1);
			
			while(rs.next()) {
				
				resultado.add(new Resena(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
			}
		}catch(SQLException e) {
			e.printStackTrace();
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
