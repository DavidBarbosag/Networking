package com.eci.ARSW.Networking;
import java.net.*;
import java.io.*;

/**
 * Servidor que proporciona dos funcionalidades:
 * 1. Recibir un número y devolver su cuadrado.
 * 2. Proporcionar un servicio de funciones trigonométricas (seno, coseno, tangente).
 * Utiliza sockets para la comunicación con los clientes.
 */
public class EchoServer {

    /**
     * Metodo que crea un servidor que recibe un numero y devuelve su cuadrado.
     * @throws IOException excepcion para errores de entrada/salida
     */
    public void serverCuadrado() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 5000.");
            System.exit(1);
        }

        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        int  inputLine;
        String outputLine;
        inputLine = Integer.parseInt(in.readLine());
        outputLine = "Respuesta: " + String.valueOf(inputLine * inputLine);
        out.println(outputLine);

        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }



    /**
     * Metodo que crea un servidor de funciones trigonométricas.
     * fun:sin, fun:cos, fun:tan, fun:exit
     * @throws IOException excepcion para errores de entrada/salida
     */
    public void funtrigo() throws IOException {
        ServerSocket serverSocket = new ServerSocket(6000);
        System.out.println("Servidor iniciado en el puerto 6000...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

            String funcionActual = "cos";
            boolean activo = true;

            while (activo) {
                String inputLine = in.readLine();
                if (inputLine == null) break;

                if (inputLine.startsWith("fun:")) {
                    switch (inputLine) {
                        case "fun:sin":
                            funcionActual = "sin";
                            out.println("Función cambiada a seno.");
                            break;
                        case "fun:cos":
                            funcionActual = "cos";
                            out.println("Función cambiada a coseno.");
                            break;
                        case "fun:tan":
                            funcionActual = "tan";
                            out.println("Función cambiada a tangente.");
                            break;
                        case "fun:exit":
                            out.println("Saliendo del servidor de funciones trigonométricas.");
                            activo = false;
                            break;
                        default:
                            out.println("Comando no reconocido.");
                    }
                } else {
                    try {
                        double numero = Double.parseDouble(inputLine);
                        double resultado;

                        switch (funcionActual) {
                            case "sin":
                                funcionActual = "sin";
                                resultado = Math.sin(numero);
                                break;
                            case "cos":
                                funcionActual = "cos";
                                resultado = Math.cos(numero);
                                break;
                            case "tan":
                                funcionActual = "tan";
                                resultado = Math.tan(numero);
                                break;
                            default:
                                resultado = 0;
                        }

                        out.println(resultado);
                    } catch (NumberFormatException e) {
                        out.println("Entrada inválida: se esperaba un número o comando 'fun:'.");
                    }
                }
            }

            in.close();
            out.close();
            clientSocket.close();
        }
    }




}

