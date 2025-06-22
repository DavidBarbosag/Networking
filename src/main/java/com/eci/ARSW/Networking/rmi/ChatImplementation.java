package com.eci.ARSW.Networking.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementación del servicio de chat que extiende UnicastRemoteObject
 * y proporciona la funcionalidad para recibir mensajes.
 */
public class ChatImplementation extends UnicastRemoteObject implements ChatInterface {

    /**
     * Constructor que lanza RemoteException.
     * Necesario para la implementación de UnicastRemoteObject.
     *
     * @throws RemoteException si ocurre un error de red al crear el objeto remoto.
     */
    public ChatImplementation() throws RemoteException {
        super();
    }

    /**
     * Método que recibe un mensaje del usuario remoto.
     * Este método es invocado por el cliente RMI para enviar mensajes.
     *
     * @param message El mensaje recibido del usuario remoto.
     * @throws RemoteException si ocurre un error de red al recibir el mensaje.
     */
    @Override
    public void receiveMessage(String message) throws RemoteException {
        System.out.println("Mensaje recibido: " + message);
    }
}
