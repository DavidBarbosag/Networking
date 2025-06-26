package com.eci.ARSW.Networking;

public class DatagramaTest {

    public static void main(String[] args) {
        Thread serverThread = new Thread(new DatagramTimeServer());
        serverThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread clientThread = new Thread(new DatagramTimeClient());
        clientThread.start();


        try {
            serverThread.interrupt();
            System.out.println(">>> Apagando el servidor...");
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            serverThread.start();
            Thread.sleep(15000);
            System.out.println(">>> Finalizando prueba...");
            clientThread.interrupt();
            serverThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

