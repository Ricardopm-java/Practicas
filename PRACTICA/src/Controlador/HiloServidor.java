package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import DAO.ResenaDAO;
import DAO.UsuarioDAO;
import Modelo.Opciones;
import Modelo.Resena;
import Modelo.Usuario;

public class HiloServidor extends Thread {

	private String usuarioNombre;
	private BufferedReader entrada;
	private BufferedWriter salida;
	private Socket socketServ;

	public HiloServidor(Socket socketServ) throws IOException {

		super();
		this.socketServ = socketServ;

	}

	@Override
	public void run() {
		super.run();
		try {
			entrada = new BufferedReader(new InputStreamReader(socketServ.getInputStream()));
			salida = new BufferedWriter(new OutputStreamWriter(socketServ.getOutputStream()));
			loggin();
			menuOpciones();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * private void opcionElegida() {
	 * 
	 * String opcionRecibida; try { opcionRecibida = lectura();
	 * System.out.println("la opcion elegida es " + opcionRecibida);
	 * 
	 * switch (opcionRecibida) { case "1": menuOpciones(Opciones.RESENA);
	 * 
	 * break; case "2": menuOpciones(Opciones.LISTARUSUARIOS); break; case "3":
	 * menuOpciones(Opciones.SEGUIR); break; case "4":
	 * menuOpciones(Opciones.OPINIONESUSUARIO); break; case "5":
	 * menuOpciones(Opciones.OPINIONESLUGAR); break; case "0":
	 * enviar("Fin del programa."); break; }
	 * 
	 * opcionRecibida = null;
	 * 
	 * } catch (IOException e) { e.printStackTrace(); }
	 * 
	 * }
	 */

	private void loggin() throws IOException {

		String lectura = lectura().toLowerCase();

		System.out.println(lectura);

		String[] arraylectura = lectura.split("#");
		Usuario usuario = new Usuario(arraylectura[0], arraylectura[1]);
		System.out.println(usuario);
		

		boolean confirmado = new UsuarioDAO().comprobarUsuario(usuario);

		System.out.println(confirmado);

		String login;
		ArrayList<String> lista = new ArrayList<String>();
		if (confirmado) {

			login = "correcto";
			usuarioNombre = arraylectura[0];
			lista.add(login);

			enviar(new Gson().toJsonTree(lista).toString());
			menuOpciones();
		}
		if(confirmado = false) {
			login = "incorrecto";
			enviar(new Gson().toJsonTree(lista).toString());
		}
		
		String lecturaNueva = lectura();
		if(lecturaNueva.equalsIgnoreCase("registro")) {
			String nuevo = lectura();
			JsonElement nuevojs = new JsonParser().parse(nuevo);
			List<Usuario> listaNuevo = new ArrayList<Usuario>();
			String nombre = null; String pass = null;
			if (nuevojs.isJsonArray()) { 
				for (JsonElement elemento : nuevojs.getAsJsonArray()) { 
					if (elemento.isJsonObject()) { 
						JsonObject valor = elemento.getAsJsonObject();
						if (valor.has("nombre")) nombre = valor.get("nombre").getAsString();
						if (valor.has("pass")) pass = valor.get("pass").getAsString();
					}
				}
			}
		 Usuario nuevoUsuario = new Usuario(nombre, pass);
		 new UsuarioDAO().registrarUsuario(nuevoUsuario);
		 enviar("registrado");
		 menuOpciones();
			
		/*
		 * String registro = lectura();
		 * 
		 * if (registro.equalsIgnoreCase("no")) {
		 * enviar("Vuelve a introducir tus datos: "); loggin();
		 * 
		 * } else { String nuevo = lectura(); ;  
		 * 
		 * 
		 * 
		 * } } }
		 */
				
				/*
				 * if () {
				 * // // registro con exito //loggin();
				 * } else { // registro fallido loggin(); }
				 */

			}
		
	}

	private void menuOpciones() throws IOException {
		String eleccion = entrada.readLine();
		JsonElement eleccionJson = new JsonParser().parse(eleccion);

		String elegido = null;

		if (eleccionJson.isJsonArray()) {
			for (JsonElement elemento : eleccionJson.getAsJsonArray()) {
				if (elemento.isJsonObject()) {
					JsonObject valor = elemento.getAsJsonObject();
					if (valor.has("eleccion"))
						eleccion = valor.get("eleccion").getAsString();
				}
			}
		}

		switch (eleccion) {
		case "resena":
			escribirResena();
			break;
		case "lista":
			listarUsuarios();
			break;
		case "seguir":
			seguirUsuario();
			break;
		case "opiniones":
			opinionesUsuario();
			break;
		case "lugar":
			opinionesLugar();
			break;
		case "salir":
			socketServ.close();
			break;

		}

		/*
		 * switch (criterio) { case RESENA: escribirResena(); break;
		 * 
		 * case SEGUIR: seguirUsuario(); break;
		 * 
		 * case LISTARUSUARIOS: listarUsuarios(); break;
		 * 
		 * case OPINIONESUSUARIO: opinionesUsuario(); break;
		 * 
		 * case OPINIONESLUGAR: opinionesLugar(); break; }
		 * 
		 * opcionElegida();
		 */
	}

	private void escribirResena() throws IOException {

		String mensaje = entrada.readLine();

		String lugar = null;
		String valoracion = null;
		String opinion = null;
		String nota = null;

		JsonElement recibido = new JsonParser().parse(mensaje);
		List<Resena> lista = new ArrayList<Resena>();
		if (recibido.isJsonArray()) {
			for (JsonElement elemento : recibido.getAsJsonArray()) {
				if (elemento.isJsonObject()) {
					JsonObject valor = elemento.getAsJsonObject();
					if (valor.has("lugar"))
						lugar = valor.get("lugar").getAsString();
					if (valor.has("valoracion"))
						valoracion = valor.get("valoracion").getAsString();
					if (valor.has("opinion"))
						opinion = valor.get("opinion").getAsString();
					if (valor.has("nota"))
						nota = valor.get("nota").getAsString();
				}
			}
		}
		/*
		 * enviar("Vamos a escribir una reseña nueva:");
		 * 
		 * enviar("Que lugar has elegido: "); String lugar = lectura().toLowerCase();
		 * 
		 * enviar("Calificalo del 0 al 10: "); String valoracion = lectura();
		 * 
		 * enviar("Escribe una breve opinion de este lugar: ");
		 * 
		 * String opinion = lectura();
		 * 
		 * enviar("Escribe una breve anotacion personal sobre el lugar: ");
		 * 
		 * String nota = lectura();
		 */

		Resena resena = new Resena(lugar, valoracion, opinion, nota, usuarioNombre);
		new ResenaDAO().nuevo(resena);

	}

	private void seguirUsuario() throws IOException {
		String mensaje = lectura().toLowerCase();
		boolean seguir = new UsuarioDAO().seguir(usuarioNombre, mensaje);
		ArrayList<Usuario> lista = new ArrayList<Usuario>();
		Usuario seguido = new Usuario(mensaje, seguir);
		if (seguir) {

			lista.add(seguido);
			enviar(new Gson().toJsonTree(lista).toString()); // envio el nombre del seguido y true

		} else {
			lista.add(seguido);
			enviar(new Gson().toJsonTree(lista).toString()); // envio el nombre del seguido y false
		}

	}

	private void listarUsuarios() throws IOException {
		ArrayList<Usuario> resultado;
		resultado = new UsuarioDAO().listar();
		enviar(new Gson().toJsonTree(resultado).toString());

		/*
		 * System.out.println(resultado); for (Usuario user : resultado) {
		 * enviar(user.toString()); } enviar("Listado terminado");
		 */

	}

	private void opinionesUsuario() throws IOException {

		String consulta = entrada.readLine();
		Usuario usuario = new Usuario(consulta.toLowerCase());
		if (new UsuarioDAO().tieneResena(usuario)) {
			ArrayList<Resena> opiniones = new ResenaDAO().opinionesUsuario(usuario);
			enviar(new Gson().toJsonTree(opiniones).toString());
		}

		/*
		 * enviar("Dime el nombre del usuario que quieres ver sus opiniones: ");
		 * 
		 * String nombre = lectura().toLowerCase(); // nombre del usuario a seguir
		 * System.out.println("El usuario elegido es: " + nombre);
		 * 
		 * Usuario usuario = new Usuario(nombre); if (new
		 * UsuarioDAO().tieneResena(usuario)) { // si tiene ese usuario reseñas escritas
		 * ArrayList<Resena> opiniones = new ResenaDAO().opinionesUsuario(usuario); //
		 * guardo el resultado en un // arraylist for (Resena op : opiniones) {//
		 * recorro el arraylist enviar(op.imprimirOpinion()); // lo voy enviando segun
		 * lo voy recorriendo con el // formato de imprimiropinion }
		 * 
		 * enviar("Listado terminado");
		 * 
		 * } else { enviar("El usuario " + usuario.getUsuario() + "no tiene reseñas.");
		 * // si no existe ese // usuario
		 * 
		 * }
		 */
	}

	private void opinionesLugar() throws IOException {

		String consulta = lectura();
		if (new ResenaDAO().comprobarLugar(consulta)) {
			ArrayList<Resena> opiniones = new ResenaDAO().opinionesLugar(consulta);
			enviar(new Gson().toJsonTree(opiniones).toString());
		}

		/*
		 * enviar("Sobre que lugar quieres ver las opiniones: "); String lugarOpinion =
		 * lectura(); System.out.println("El lugar elegido es " + lugarOpinion);
		 * 
		 * if (new ResenaDAO().comprobarLugar(lugarOpinion)) { ArrayList<Resena>
		 * opiniones = new ResenaDAO().opinionesLugar(lugarOpinion); for (Resena op :
		 * opiniones) { enviar(op.imprimirLugar()); }
		 * 
		 * enviar("Listado terminado");
		 * 
		 * } else {
		 * enviar("Este lugar no esta en la base de datos o esta mal escrito."); }
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

}
