package Vista;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Arrays;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Modelo.Resena;
import Modelo.Usuario;
import Ventanas.VentanaInicial;
import Ventanas.VentanaInicialNOK;
import Ventanas.VentanaMenu;
import Ventanas.VentanaMostrar;
import Ventanas.VentanaOpinionesLugar;
import Ventanas.VentanaOpinionesUsuario;
import Ventanas.VentanaResena;
import Ventanas.VentanaSeguir;

public class Cliente {

	private Socket socket;
	private BufferedReader entrada;
	private BufferedWriter salida;

	public Cliente() {

		try {
			socket = new Socket("localhost", 1001);
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			salida = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("Cliente conectado.");

		} catch (IOException e) {
			System.out.println("Error de comunicacion");
		}

	}

	public String lectura() throws IOException {
		String leido = entrada.readLine();
		return leido;
	}

	public void enviar(String mensaje) throws IOException {
		salida.write(mensaje);
		salida.newLine();
		salida.flush();
	}

	private void menuInicial() {

		boolean seguir = true;
		while (seguir) {

			try {
				new VentanaInicial(this).setVisible(true);

				String mensaje = lectura(); // login : correcto
				// Usuario y contraseña correctos || usuario no existente quieres
				// registrarte
				System.out.println(mensaje);

				if (mensaje.equalsIgnoreCase("correcto")) {
					menu();
					seguir = false;
				}
				if (mensaje.equalsIgnoreCase("incorrecto")) {
					new VentanaInicialNOK(this).setVisible(true);
					
					if (entrada.readLine().equalsIgnoreCase("registrado")) {
						menu();
						seguir=false;
					}
					if(entrada.readLine().equalsIgnoreCase("Registro fallido")) {
						new VentanaMostrar("No se ha podido completar el registro");
						menuInicial();
					}
					
				}

			} catch (IOException e) {
				System.out.println("Credenciales incorrectas.");
				e.printStackTrace();
			}

		}
	}

	private void menu() {
		new VentanaMenu(this).setVisible(true);

	}

	private void escribirResena() throws IOException {
		new VentanaResena(this).setVisible(true);
		

	}

	public void listadoUsuarios() throws IOException {

		String mensaje = entrada.readLine();

		String[] arraylectura = mensaje.split(" ");

		new VentanaMostrar(Arrays.toString(arraylectura)).setVisible(true);

		

	}

	private void seguirUsuario() throws IOException {
		new VentanaSeguir(this).setVisible(true);
		String mensaje = lectura();
		Usuario usuarioSeguido = new Gson().fromJson(mensaje, Usuario.class);
		String confirmacion = usuarioSeguido.getPass();
		if (confirmacion.equalsIgnoreCase("true")) {
			new VentanaMostrar("Usuario seguido correctamente");
		} else {
			new VentanaMostrar("No se ha podido añadir a este usuario a tu lista de seguidos");
		}

		

	}

	public void opinionesUsuario() {

		new VentanaOpinionesUsuario(this).setVisible(true);
		

	}

	public void opninionesLugar() {


			new VentanaOpinionesLugar(this).setVisible(true);


	}
	
	public void cerrarAplicacion() throws IOException {
		String mensaje = lectura(); //hasta pronto
		new VentanaMostrar(mensaje);
		socket.close();
		
		
	}

	/*
	 * Si quiero enviar un dato en JSON: String json = new
	 * Gson().toJson(objetoQueQuieroTransformar); enviar(json);
	 * 
	 * Si quiero recibir un dato en JSON: String mensaje = lectura(); Tipo miObjeto
	 * = new Gson().fromJson(mensaje, Tipo.class);
	 */

	public static void main(String[] args) {
		new Cliente().menuInicial(); // Este es el unico new Cliente() que puede haber en todo el proyecto

	}

}
