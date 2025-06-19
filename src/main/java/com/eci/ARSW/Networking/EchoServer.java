package com.eci.ARSW.Networking;
import java.net.*;
import java.io.*;

public class EchoServer {
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

    public void funtrigo() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(6000);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 6000.");
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
        String  inputLine;
        String outputLine;
        inputLine = in.readLine();
        switch (inputLine) {
            case "fun:sin":
                outputLine = "Respuesta: " + String.valueOf(Math.sin(0));
                out.println(outputLine);

        }



        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }
}

