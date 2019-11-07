package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

    Socket socketToClient;
    Thread thread;

    public Server(Socket socketToClient){
        this.socketToClient = socketToClient;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try (
                PrintWriter writer = new PrintWriter(socketToClient.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketToClient.getInputStream()))
        ) {
            String output, input;

            Protocol protocol = new Protocol();
            output = protocol.processInput(null);
            writer.println(output);
            while ((input = reader.readLine()) != null) {
                output = protocol.processInput(input);
                writer.println(output);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
