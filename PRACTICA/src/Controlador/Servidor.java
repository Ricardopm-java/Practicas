package Controlador;


import java.io.IOException;

import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {

	private ServerSocket socket;

	
	  public void Servidor() {
	  
	  }
	 

	public Servidor() {

		try {
			socket = new ServerSocket(1001);
		} catch (IOException e) {
			System.out.println(e);
			System.out.println("imposible crear el socket del servidor en puerto 1001");
		}

	}

	public boolean conectar() {

		while (true) {
			try {
				Socket socketServ = socket.accept();
				new HiloServidor(socketServ).start();

				System.out.println("servidor conectado");

			} catch (IOException e) {
				e.printStackTrace();

			}

		}
	}
	
}
