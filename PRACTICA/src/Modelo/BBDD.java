package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;



public class BBDD {
	private static BBDD instancia = null;
	private Connection conn;

	private BBDD() {

		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "cice");
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