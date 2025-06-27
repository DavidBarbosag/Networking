package com.eci.ARSW.Networking;

public class DatagramaTest {

    public static void main(String[] args) {
        // Primera instancia del servidor
        DatagramTimeServer server = new DatagramTimeServer();
        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Iniciar cliente
        Thread clientThread = new Thread(new DatagramTimeClient());
        clientThread.start();

        try {
            // Esperar 5.2 segundos
            Thread.sleep(5200);
            System.out.println(">>> Apagando el servidor...");
            server.stop();

            // Esperar 10.2 segundos con el servidor apagado
            Thread.sleep(10200);

            // Reiniciar el servidor
            System.out.println(">>> Reiniciando el servidor...");
            server = new DatagramTimeServer();  // Nueva instancia
            serverThread = new Thread(server);
            serverThread.start();

            // Esperar 10 segundos con el servidor encendido nuevamente
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Finalizar cliente
        System.out.println(">>> Finalizando prueba...");
        clientThread.interrupt();
        server.stop();
    }
}
