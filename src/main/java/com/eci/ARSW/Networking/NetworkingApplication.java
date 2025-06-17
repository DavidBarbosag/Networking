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
	}
}
