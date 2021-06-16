package Vista;

import java.io.BufferedReader; 
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.ResenaDAO;
import DAO.UsuarioDAO;
import Modelo.Opciones;
import Modelo.Resena;
import Modelo.Usuario;
import Ventanas.VentanaInicial;
import Ventanas.VentanaInicialNOK;
import Ventanas.VentanaMenu;
import Ventanas.VentanaRegistro;
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

	private void menuPrincipal() {

		/*
		 * String opcion = "";
		 * 
		 * menu();
		 * 
		 * while (!opcion.equalsIgnoreCase("0")) { try { while (entrada.readLine() !=
		 * null) {// muestra el menu System.out.println(entrada);
		 * 
		 * } opcion = new Scanner(System.in).nextLine(); salida.write(opcion);
		 * 
		 * } catch (IOException e) { System.out.println("Eleccion no valida."); }
		 * 
		 * }
		 */
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
		boolean primeraEtapa;
		boolean seguir = true;
		while (seguir) {

			
				try {
					new VentanaInicial(this).setVisible(true);
					
					String mensaje = lectura(); //login : correcto
					// Usuario y contraseña correctos || usuario no existente quieres
					// registrarte
					System.out.println(mensaje);
					
					  String confirmacion = null;
					  
					  JsonElement jsonlogueado = new JsonParser().parse(mensaje); if
					  (jsonlogueado.isJsonArray()) { for (JsonElement elemento :
					  jsonlogueado.getAsJsonArray()) { if (elemento.isJsonObject()) { JsonObject
					  valor = elemento.getAsJsonObject(); if (valor.has("login")) confirmacion =
					  valor.get("login").getAsString(); } } }
					 
					if (mensaje.equalsIgnoreCase("correcto")) {
						
						menu();
						seguir = false;
					} else {
						new VentanaInicialNOK().setVisible(true);
						if(entrada.readLine().equalsIgnoreCase("registrado")) {
							menu();
						}
						//String respuesta = new Scanner(System.in).nextLine();
						//enviar(respuesta); // le mando "si o no"
						
//						/*if (!"no".equalsIgnoreCase(respuesta)) { // en caso de que la respuesta sea si
//							ArrayList<Usuario> nuevoRegistro = new ArrayList<Usuario>();
//							nuevoRegistro.add(new VentanaRegistro().VentanaRegistro());
//							enviar(new Gson().toJsonTree(nuevoRegistro).toString());*/
						
							/*String mensajeRegistrado = entrada.readLine();
							if (mensajeRegistrado.equalsIgnoreCase("Usuario registrado con éxito.")) {
								// crear una ventana de confirmacion de registro
								menu();
							}
						} else { // en caso de que la respuesta sea no
							System.out.println(entrada.readLine());// "Vuelve a introducir tus datos: "
							menuInicial();
*/
						}
					

				} catch (IOException e) {
					System.out.println("Credenciales incorrectas.");
					e.printStackTrace();
				}
			
		}
	}

	private void menu() {
		new VentanaMenu().setVisible(true);

		/*
		 * System.out.println("-------------------------");
		 * System.out.println("\tMENU\n");
		 * System.out.println("-------------------------\n");
		 * System.out.println("1. Escribir una reseña.\n");
		 * System.out.println("2. Lista de usuarios registrados.\n");
		 * System.out.println("3. Seguir a un usuario.\n");
		 * System.out.println("4. Opiniones escritas por un usuario.\n");
		 * System.out.println("5. Todas las opiniones sobre un lugar.\n");
		 * 
		 * System.out.println("0. Salida.\n"); System.out.println("Elige una opcion: ");
		 * 
		 * String eleccion = new Scanner(System.in).nextLine(); String mensaje;
		 * 
		 * try { enviar(eleccion); } catch (IOException e2) { // TODO Auto-generated
		 * catch block e2.printStackTrace(); } if (eleccion != "0") { try {
		 * 
		 * switch (eleccion) { case "1": escribirResena(); break; case "2":
		 * listadoUsuarios(); break; case "3": seguirUsuario(); break; case "4":
		 * opinionesUsuario(); break; case "5": opninionesLugar(); break; } } catch
		 * (IOException e) { System.out.println("Eleccion no valida");
		 * e.printStackTrace(); } } else { try {
		 * System.out.println(entrada.readLine());// "Fin del programa."
		 * System.out.println("Adios!"); socket.close(); } catch (IOException e1) {
		 * 
		 * e1.printStackTrace(); }
		 * 
		 * }
		 */

	}

	private void escribirResena() throws IOException {
		new VentanaResena().setVisible(true);

		/*
		 * System.out.println(entrada.readLine()); //
		 * ("Vamos a escribir una reseña nueva: \n" +
		 * 
		 * String mensaje; mensaje = entrada.readLine();// "Que lugar has elegido: "
		 * System.out.println(mensaje);
		 * 
		 * if (mensaje.equalsIgnoreCase("Que lugar has elegido: ")) enviar(new
		 * Scanner(System.in).nextLine());
		 * 
		 * mensaje = entrada.readLine(); System.out.println(mensaje); if
		 * (mensaje.equalsIgnoreCase("Calificalo del 0 al 10: ")) enviar(new
		 * Scanner(System.in).nextLine());
		 * 
		 * mensaje = entrada.readLine(); System.out.println(mensaje); if
		 * (mensaje.equalsIgnoreCase("Escribe una breve opinion de este lugar: "))
		 * enviar(new Scanner(System.in).nextLine());
		 * 
		 * mensaje = entrada.readLine(); System.out.println(mensaje); if (mensaje.
		 * equalsIgnoreCase("Escribe una breve anotacion personal sobre el lugar: "))
		 * enviar(new Scanner(System.in).nextLine());
		 */

		menu();

	}

	public void listadoUsuarios() throws IOException {

		String mensaje = entrada.readLine();
		/*
		 * [
		 * {"usuario": "luis","¨pass": ",..", "seguidos": ["¨sadasd", "¨sad", "äsdsd"] },
		 * {},
		 * {}
		 * ] 
		 */
		
		
		/*
		 * List<Usuario> listado = new ArrayList<Usuario>(); JsonElement doc = new
		 * JsonParser().parse(mensaje); if (doc.isJsonArray()) { for (JsonElement
		 * elemento : doc.getAsJsonArray()) { if (elemento.isJsonObject()) { JsonObject
		 * valor = elemento.getAsJsonObject(); String nombre = ""; if
		 * (valor.has("usuario")) nombre = valor.get("usuario").getAsString(); Usuario
		 * usuario = new Usuario(nombre); listado.add(usuario); } }
		 * 
		 * }
		 */
		
		Usuario[] usuarios = new Gson().fromJson(mensaje, Usuario[].class);
		List<Usuario> listado = Arrays.asList(usuarios);

		new Ventanas.VentanaMostrar(listado.toString());

		/*
		 * try { String mensaje = entrada.readLine(); while (mensaje != null &&
		 * !mensaje.equals("Listado terminado")) { System.out.println(mensaje); mensaje
		 * = entrada.readLine(); }
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		menu();

	}

	private void seguirUsuario() throws IOException {
		new VentanaSeguir().setVisible(true);
		String mensaje = entrada.readLine();
		JsonElement jsonMensaje = new JsonParser().parse(mensaje);
		List<Usuario> lista = new ArrayList<Usuario>();
		if (jsonMensaje.isJsonArray()) {
			for (JsonElement elemento : jsonMensaje.getAsJsonArray()) {
				if (elemento.isJsonObject()) {
					JsonObject valor = elemento.getAsJsonObject();
					String seguido = null;
					String seguirOK = null;
					if (valor.has("seguido"))
						seguido = valor.get("seguido").getAsString();
					if (valor.has("seguir"))
						seguirOK = valor.get("seguirOK").getAsString();
					lista.add(new Usuario(seguido, Boolean.parseBoolean(seguirOK)));
					new Ventanas.VentanaMostrar(lista.toString());
				}
			}
		}
		menu();

	}

	public void opinionesUsuario() {

		new Ventanas.VentanaOpinionesUsuario();

		try {
			String opiniones = entrada.readLine();
			JsonElement doc = new JsonParser().parse(opiniones);
			List<Resena> lista = new ArrayList<Resena>();
			if (doc.isJsonArray()) {
				for (JsonElement elemento : doc.getAsJsonArray()) {
					if (elemento.isJsonObject()) {
						JsonObject valor = elemento.getAsJsonObject();
						String lugar = null;
						String calificacion = null;
						String opinion = null;
						if (valor.has("lugar"))
							lugar = valor.get("lugar").getAsString();
						if (valor.has("calificacion"))
							calificacion = valor.get("calificacion").getAsString();
						if (valor.has("opinion"))
							opinion = valor.get("opinion").getAsString();

						Resena nueva = new Resena(lugar, calificacion, opinion);

						lista.add(nueva);
					}
				}
			}

			new Ventanas.VentanaMostrar(lista.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { System.out.println(entrada.readLine()); //
		 * "Dime el nombre del usuario que quieres ver sus opiniones: " String nombre =
		 * new Scanner(System.in).nextLine();
		 * System.out.println("Veamos las opiniones de " + nombre); enviar(nombre);
		 * String mensaje = entrada.readLine();
		 * 
		 * while (mensaje != null && !mensaje.equals("Listado terminado")) {
		 * System.out.println(mensaje); mensaje = entrada.readLine();
		 * 
		 * }
		 * 
		 * } catch (IOException e) {
		 * 
		 * e.printStackTrace(); }
		 */
		menu();

	}

	public void opninionesLugar() {

		

		try {
			String opiniones = entrada.readLine();
			JsonElement doc = new JsonParser().parse(opiniones);
			List<Resena> lista = new ArrayList<Resena>();
			if (doc.isJsonArray()) {
				for (JsonElement elemento : doc.getAsJsonArray()) {
					if (elemento.isJsonObject()) {
						JsonObject valor = elemento.getAsJsonObject();
						String usuario = null;
						String lugar = null;
						String calificacion = null;
						String opinion = null;
						if (valor.has("usuario"))
							usuario = valor.get("usuario").getAsString();
						if (valor.has("lugar"))
							lugar = valor.get("lugar").getAsString();
						if (valor.has("calificacion"))
							calificacion = valor.get("calificacion").getAsString();
						if (valor.has("opinion"))
							opinion = valor.get("opinion").getAsString();

						Resena nueva = new Resena(usuario, lugar, calificacion, opinion);

						lista.add(nueva);
					}
				}
			}

			new Ventanas.VentanaMostrar(lista.toString()).setVisible(true);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * try { System.out.println(entrada.readLine());//
		 * "Sobre que lugar quieres ver las opiniones: " enviar(new
		 * Scanner(System.in).nextLine()); // envio el lugar
		 * 
		 * String mensaje = entrada.readLine(); while (mensaje != null &&
		 * !mensaje.equals("Listado terminado")) { System.out.println(mensaje); mensaje
		 * = entrada.readLine(); // mientras que la entrada tenga lineas para leer las
		 * imprimo } } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		menu();

	}
	
	/*
	 * Si quiero enviar un dato en JSON:
	 * String json = new Gson().toJson(objetoQueQuieroTransformar);
	 * enviar(json);
	 * 
	 * Si quiero recibir un dato en JSON:
	 * String mensaje = recibir();
	 * Tipo miObjeto = new Gson().fromJson(mensaje, Tipo.class);
	 */

	public static void main(String[] args) {
		new Cliente().menuInicial(); //Este es el unico new Cliente() que puede haber en todo el proyecto

	}

}
