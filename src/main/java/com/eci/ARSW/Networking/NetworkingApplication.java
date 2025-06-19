package com.eci.ARSW.Networking;

import com.eci.ARSW.Networking.URLReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.MalformedURLException;
import java.util.Scanner;

@SpringBootApplication
public class NetworkingApplication {

	public static void main(String[] args) throws MalformedURLException {
		SpringApplication.run(NetworkingApplication.class, args);

		URLReader urlReader = new URLReader();
		urlReader.urlFixed();
		try{
			Scanner scanner = new Scanner(System.in);
			System.out.print("Ingrese una URL: ");
			String urlString = scanner.nextLine();
			urlReader.htmlDownloader(urlString);
		} catch (MalformedURLException e) {
			System.err.println("URL inválida: " + e.getMessage());
		}

		Thread serverThread = new Thread(() -> {
			EchoServer server = new EchoServer();
			try {
				server.serverCuadrado();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		serverThread.start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		EchoClient client = new EchoClient();

		Scanner numCuadrado = new Scanner(System.in);
		System.out.print("Ingrese el numero que se va a elevar al cuadrado");
		try {
			client.clientNum(Integer.parseInt(numCuadrado.nextLine()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
