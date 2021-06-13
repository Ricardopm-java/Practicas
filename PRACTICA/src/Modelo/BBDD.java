package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/*class BBDD2 {

	private Connection conn = null;
	
	private BBDD2() {
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/listado_de_usuarios?serverTimezone=UTC","root", "root");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} 
		
	}
	
	public Connection getInstance() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/listado_de_usuarios?serverTimezone=UTC","root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}
}*/

public class BBDD {
	private static BBDD instancia = null;
	private Connection conn;

	private BBDD() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "REGISTRO", "cice");
			Statement statement = conn.createStatement();
		} catch (SQLException e) {

		}

		/*
		 * try { conn = DriverManager.getConnection(
		 * "jdbc:mysql://localhost:3306/listado_de_usuarios?serverTimezone=UTC","root",
		 * "root");
		 * 
		 * } catch (SQLException e) {
		 * 
		 * e.printStackTrace(); }
		 */

	}

	public static Connection get() {
		if (instancia == null)
			instancia = new BBDD();

		return instancia.conn;
	}
}