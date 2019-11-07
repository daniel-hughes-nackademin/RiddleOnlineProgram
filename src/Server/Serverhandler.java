package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Serverhandler {

    public Serverhandler(){
        try {
            int portNr = 41414;
            ServerSocket serverSocket = new ServerSocket(portNr);

            boolean running = true;
            System.out.println("Server Handler is running. Awaiting first client connection.");
            while (running) {
                Socket socketToClient = serverSocket.accept();
                new Server(socketToClient);

                String clientName = socketToClient.getRemoteSocketAddress().toString();
                System.out.println(clientName + " connected to Server.");
                System.out.println("Telling very lame riddles to client.\n");
                System.out.println("Awaiting new client connections.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
        new Serverhandler();
    }
}
