package com.eci.ARSW.Networking;
import java.io.*;
import java.net.*;

/**
 * Cliente que se conecta a un servidor para enviar un número y recibir su cuadrado,
 * o para enviar una función trigonométrica y recibir el resultado.
 * Utiliza sockets para la comunicación con el servidor.
 */
public class EchoClient {


    /**
     * Método para enviar un número al servidor y recibir su cuadrado.
     * @param num El número que se enviará al servidor para calcular su cuadrado.
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión o la comunicación.
     */
    public void clientNum(int num) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 5000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }
        out.println(num);
        System.out.println("Cuadrado del numero: " + in.readLine());

        out.close();
        in.close();
        echoSocket.close();
    }

    /**
     * Método para enviar una función trigonométrica al servidor y recibir el resultado.
     * @param fun La función trigonométrica o número que se enviará al servidor.
     * @throws IOException Si ocurre un error de entrada/salida durante la conexión o la comunicación.
     */
    public void clientFunTrigo(String fun) throws IOException {
        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;

        try {
            echoSocket = new Socket("127.0.0.1", 6000);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host!");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: localhost.");
            System.exit(1);
        }
        out.println(fun);
        System.out.println("El resultado de la operacion es: " + in.readLine());

        out.close();
        in.close();
        echoSocket.close();
    }
}

