package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

   public void runServer() {
           System.out.println("Server is running. Awaiting client connection.");
       int portNr = 41414;
       try(
               ServerSocket serverSocket = new ServerSocket(portNr);
               Socket socketToClient = serverSocket.accept();
               PrintWriter writer = new PrintWriter(socketToClient.getOutputStream(), true);
               BufferedReader reader = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()))
               ){
           String clientName = socketToClient.getRemoteSocketAddress().toString();
           System.out.println(clientName + " connected to Server.");
           System.out.println("Telling very lame riddles to client.");

           String output, input;

           Protocol protocol = new Protocol();
           output = protocol.processInput(null);
           writer.println(output);
           while ((input = reader.readLine()) != null){
               output = protocol.processInput(input);
               writer.println(output);
           }
       } catch (IOException e){
           e.printStackTrace();
       }

   }


    public static void main(String[] args) {
        new Server().runServer();
    }
}
