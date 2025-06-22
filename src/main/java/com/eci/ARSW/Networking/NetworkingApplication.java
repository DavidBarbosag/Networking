package com.eci.ARSW.Networking;

import com.eci.ARSW.Networking.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Scanner;

@SpringBootApplication
public class NetworkingApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(NetworkingApplication.class, args);

		// --------- URL Downloader ---------
		URLReader urlReader = new URLReader();
		urlReader.urlFixed();

		try {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese una URL: ");
			String urlString = scanner.nextLine();
			urlReader.htmlDownloader(urlString);
		} catch (MalformedURLException e) {
			System.err.println("URL inválida: " + e.getMessage());
		}

		// --------- Servidor de Cuadrado (Puerto 5000) ---------
		Thread serverCuadradoThread = new Thread(() -> {
			EchoServer server = new EchoServer();
			try {
				server.serverCuadrado();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		serverCuadradoThread.start();

		// --------- Servidor de Funciones Trigonométricas (Puerto 6000) ---------
		Thread serverTrigoThread = new Thread(() -> {
			EchoServer server = new EchoServer();
			try {
				server.funtrigo();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		serverTrigoThread.start();

		// --------- Esperar a que los servidores se inicien ---------
		try {
			Thread.sleep(1000); // esperar un segundo
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// --------- Cliente: enviar número para elevar al cuadrado ---------
		EchoClient client = new EchoClient();
		Scanner numCuadrado = new Scanner(System.in);
		System.out.print("Ingrese el número que se va a elevar al cuadrado: ");
		try {
			int numero = Integer.parseInt(numCuadrado.nextLine());
			client.clientNum(numero);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// --------- Cliente: funciones trigonométricas ---------

		EchoClient clientFun = new EchoClient();
		Scanner funTrigo = new Scanner(System.in);

		while (true) {
			System.out.print("Ingrese una función o número (fun:sin, fun:cos, fun:tan, fun:exit o valor numérico): ");
			String entrada = funTrigo.nextLine();
			try {
				clientFun.clientFunTrigo(entrada);
				if (entrada.equals("fun:exit")) break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// --------- Servidor de Hora (UDP, puerto 4445) ---------
		Thread udpServerThread = new Thread(new DatagramTimeServer());
		udpServerThread.start();

		// --------- Cliente de Hora (UDP) ---------
		Thread udpClientThread = new Thread(new DatagramTimeClient());
		udpClientThread.start();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		udpClientThread.interrupt();
		udpServerThread.interrupt();

		// --------- Chat RMI entre pares ---------
		try {
			Scanner scanner = new Scanner(System.in);

			// Puerto local para publicar el servicio RMI
			System.out.print("Ingrese el puerto local para publicar el servicio RMI: ");
			int localPort = Integer.parseInt(scanner.nextLine());

			// Crear y publicar el objeto remoto en el registro local
			java.rmi.registry.LocateRegistry.createRegistry(localPort);
			com.eci.ARSW.Networking.rmi.ChatInterface localChat = new com.eci.ARSW.Networking.rmi.ChatImplementation();
			java.rmi.registry.Registry localRegistry = java.rmi.registry.LocateRegistry.getRegistry(localPort);
			localRegistry.rebind("chat", localChat);

			System.out.println("Objeto RMI publicado en puerto " + localPort);

			// IP y puerto del usuario remoto
			System.out.print("Ingrese la IP del usuario remoto: ");
			String remoteIP = scanner.nextLine();
			System.out.print("Ingrese el puerto remoto: ");
			int remotePort = Integer.parseInt(scanner.nextLine());

			// Intentar conectar al otro usuario con reintentos
			com.eci.ARSW.Networking.rmi.ChatInterface remoteChat = null;
			while (remoteChat == null) {
				try {
					java.rmi.registry.Registry remoteRegistry = java.rmi.registry.LocateRegistry.getRegistry(remoteIP, remotePort);
					remoteChat = (com.eci.ARSW.Networking.rmi.ChatInterface) remoteRegistry.lookup("chat");
				} catch (Exception e) {
					System.out.println("Esperando a que el usuario remoto esté disponible en " + remoteIP + ":" + remotePort + "...");
					Thread.sleep(2000);
				}
			}

			System.out.println("Conectado exitosamente. Escriba sus mensajes (Ctrl+C para salir):");

			// Bucle para enviar mensajes
			while (true) {
				String msg = scanner.nextLine();
				remoteChat.receiveMessage(msg);
			}

		} catch (Exception e) {
			System.err.println("Error en el chat RMI:");
			e.printStackTrace();
		}


	}
}
