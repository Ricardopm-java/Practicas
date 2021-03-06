package Controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;

import DAO.ResenaDAO;
import DAO.UsuarioDAO;
import Modelo.Resena;
import Modelo.ResenaEscribir;
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

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void loggin() throws IOException {

		String lectura = lectura().toLowerCase();

		System.out.println(lectura);

		String[] arraylectura = lectura.split("#");
		Usuario usuario = new Usuario(arraylectura[0], arraylectura[1]);
		System.out.println(usuario);

		boolean confirmado = new UsuarioDAO().comprobarUsuario(usuario);

		System.out.println(confirmado);

		String login = null;

		if (confirmado) {
			login = "correcto";
			usuarioNombre = arraylectura[0];
			// lista.add(login);
			enviar(login);
			System.out.println(login);

			menuOpciones();

		}
		if (!confirmado) {
			login = "incorrecto";
			enviar(login);
			while (login.equalsIgnoreCase("incorrecto")) {
				String lecturaNueva = lectura();
				
				if (lecturaNueva.equalsIgnoreCase("reintentar")) {
					
					loggin();
				}
				if (lecturaNueva.equalsIgnoreCase("registro")) {
					String nuevo = lectura();
					Usuario nuevoRegistro = new Gson().fromJson(nuevo, Usuario.class);
					String nombre = nuevoRegistro.getUsuario();
					String pass = nuevoRegistro.getPass();
					usuarioNombre = nombre;
					Usuario nuevoUsuario = new Usuario(nombre, pass);
					System.out.println(nuevoUsuario.toString());
					if (new UsuarioDAO().registrarUsuario(nuevoUsuario)) {
						enviar("registrado");
						confirmado = true;
						login = "correcto";
						menuOpciones();
					} else {
						enviar("Registro fallido");
					}

				}

				if (lecturaNueva == "reintentar") {

					loggin();

				}
			}

		}

	}

	private void menuOpciones() throws IOException {
		boolean salir = false;
		do {
			String eleccion = entrada.readLine();

			switch (eleccion) {
			case "resena":
				escribirResena();
				break;
			case "lista":
				listarUsuarios(usuarioNombre);

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
				salir = true;
				enviar("Hasta pronto");
				socketServ.close();
				break;

			}
		} while(!salir);
		

	}

	private void escribirResena() throws IOException {

		String mensaje = entrada.readLine();

		String lugar = null;
		String valoracion = null;
		String opinion = null;
		String nota = null;

		ResenaEscribir resenaNuevaRec = new Gson().fromJson(mensaje, ResenaEscribir.class);
		System.out.println(resenaNuevaRec.toString());
		lugar = resenaNuevaRec.getLugar();
		valoracion = resenaNuevaRec.getCalificacion();
		opinion = resenaNuevaRec.getOpinion();
		nota = resenaNuevaRec.getNota();

		Resena resena = new Resena(lugar, valoracion, opinion, nota, usuarioNombre);
		System.out.println(resena.toString());
		new ResenaDAO().nuevo(resena);

	}

	private void seguirUsuario() throws IOException {
		String mensaje = lectura().toLowerCase();
		boolean seguir = new UsuarioDAO().seguir(usuarioNombre, mensaje);
		Usuario seguido = new Usuario(mensaje, seguir);
		if (seguir) {

			enviar(new Gson().toJson(seguido).toString()); // envio el nombre del seguido y true

		} else {
			enviar(new Gson().toJson(seguido).toString()); // envio el nombre del seguido y false
		}

	}

	private void listarUsuarios(String interesado) throws IOException {
		ArrayList<Usuario> resultado = new UsuarioDAO().listar(interesado);
		enviar(resultado.toString());

	}

	private void opinionesUsuario() throws IOException {

		String consulta = entrada.readLine(); //viene el nombre del usuario que se quiere leer las rese?as
		Usuario usuario = new Usuario(consulta.toLowerCase());
		if (new UsuarioDAO().tieneResena(usuario)) {
			ArrayList<Resena> opiniones = new ResenaDAO().opinionesUsuario(usuario);
			enviar(new Gson().toJson(opiniones).toString());
		}

	}

	private void opinionesLugar() throws IOException {

		String consulta = lectura();
		if (new ResenaDAO().comprobarLugar(consulta)) {
			ArrayList<Resena> opiniones = new ResenaDAO().opinionesLugar(consulta, usuarioNombre);
			enviar(new Gson().toJson(opiniones).toString());
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

}
