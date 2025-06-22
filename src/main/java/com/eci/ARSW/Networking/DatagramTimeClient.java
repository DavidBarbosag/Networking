package com.eci.ARSW.Networking;

import java.io.IOException;
import java.net.*;

public class DatagramTimeClient implements Runnable {

    private static final int SERVER_PORT = 4445;
    private static final int TIMEOUT_MS = 2000;
    private static final int INTERVAL_MS = 5000;

    /**
     * Cliente que solicita la hora actual al servidor UDP.
     * Envía un paquete de solicitud al servidor y espera una respuesta.
     * Si no recibe respuesta en el tiempo especificado, mantiene la última hora conocida.
     */
    @Override
    public void run() {
        String lastTime = "00:00:00 (sin hora aún)";
        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(TIMEOUT_MS);

            InetAddress address = InetAddress.getByName("localhost");

            byte[] buf = new byte[256];

            while (true) {
                try {
                    DatagramPacket request = new DatagramPacket(buf, buf.length, address, SERVER_PORT);
                    socket.send(request);

                    DatagramPacket response = new DatagramPacket(buf, buf.length);
                    socket.receive(response);

                    String received = new String(response.getData(), 0, response.getLength());
                    lastTime = received;
                } catch (SocketTimeoutException e) {
                    System.out.println("Servidor no disponible, manteniendo hora anterior.");
                } catch (IOException e) {
                    System.out.println("Error de conexión: " + e.getMessage());
                }

                System.out.println("Hora actual: " + lastTime);

                try {
                    Thread.sleep(INTERVAL_MS);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Error al crear el socket del cliente: " + e.getMessage());
        }
    }
}
