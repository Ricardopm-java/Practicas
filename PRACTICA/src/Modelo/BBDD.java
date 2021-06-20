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
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "SYSTEM", "cice");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static synchronized Connection get() { // hay que poner synchronized para que solo pueda haber una conexion
													// simultanea.
		if (instancia == null)
			instancia = new BBDD();

		return instancia.conn;
	}
}