package com.eci.ARSW.Networking;
import java.io.*;
import java.net.*;

/**
 * Clase que contiene el ejercicio 1 y 2 del taller de networking
 */
public class URLReader {

    /**
     * Metodo que tiene una URL fija e imprime la informacion de esta
     * (Ejercicio 1)
     * @throws MalformedURLException excepcion para URL mal conformadas
     */
    public void urlFixed() throws MalformedURLException {
        URL google = new URL("http://www.google.com/");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(google.openStream()))) {
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                System.out.println(inputLine);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        System.out.println("Protocol: " + google.getProtocol());
        System.out.println("Authority: " + google.getAuthority());
        System.out.println("Host: " + google.getHost());
        System.out.println("Port: " + google.getPort());
        System.out.println("Path: " + google.getPath());
        System.out.println("Query: " + google.getQuery());
        System.out.println("File: " + google.getFile());
        System.out.println("Ref: " + google.getRef());
    }

    /**
     * Metodo que recibe una URL, obtiene su .html y lo guarda en un archivo.
     * @param urlString String que reperesenta una URL
     * @throws MalformedURLException excepcion para URL mal conformadas
     */
    public void htmlDownloader(String urlString) throws MalformedURLException {
        URL urltoD = new URL(urlString);
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(urltoD.openStream()));
            BufferedWriter out = new BufferedWriter(new FileWriter("resultado.html"));
            String inputLine = null;
            while ((inputLine = reader.readLine()) != null) {
                out.write(inputLine);
                out.newLine();
            }
            out.close();
            reader.close();
            File htmlFile = new File("resultado.html");
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
