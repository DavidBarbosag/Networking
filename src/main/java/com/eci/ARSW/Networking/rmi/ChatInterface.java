package com.eci.ARSW.Networking.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interfaz remota para el servicio de chat.
 * Define el método que los clientes pueden invocar para enviar mensajes.
 */
public interface ChatInterface extends Remote {
    void receiveMessage(String message) throws RemoteException;
}
