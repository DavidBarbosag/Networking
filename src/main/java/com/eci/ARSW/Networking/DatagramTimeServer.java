package com.eci.ARSW.Networking;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servidor UDP que envía la hora actual a los clientes que lo soliciten.
 * Escucha en el puerto 4445 y responde con la fecha y hora actual en formato de cadena.
 */
public class DatagramTimeServer implements Runnable {

    private DatagramSocket socket;
    private volatile boolean running = true;

    public DatagramTimeServer() {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException ex) {
            Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void stop() {
        running = false;
        socket.close(); // Esto forza que receive() lance IOException
    }

    /**
     * Método que se ejecuta en un hilo separado para escuchar solicitudes de clientes.
     * Cuando recibe un paquete, envía la fecha y hora actual al cliente.
     */
    @Override
    public void run() {
        byte[] buf = new byte[256];
        while (running) {
            try {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                String dString = new Date().toString();
                buf = dString.getBytes();

                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException ex) {
                if (!running) {
                    System.out.println(">>> Servidor detenido correctamente.");
                } else {
                    Logger.getLogger(DatagramTimeServer.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            }
        }
        if (socket != null && !socket.isClosed()) {
            socket.close();
        }
    }
}
