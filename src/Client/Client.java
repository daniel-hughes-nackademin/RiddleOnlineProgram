package Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public Client() {
        runClient();
    }

    public static void main(String[] args) {
        new Client();
    }


    public void runClient(){
        InetAddress localHost = null;
        int portNr = 41414;
        try {
            localHost = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        try(
                Socket riddleSocket = new Socket(localHost, portNr);
                PrintWriter writer = new PrintWriter(riddleSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(riddleSocket.getInputStream()));
                BufferedReader sysIn = new BufferedReader(new InputStreamReader(System.in))
                ){
            String serverName = riddleSocket.getRemoteSocketAddress().toString();
            System.out.println("Connected to server: " + serverName);
            String fromServer, fromUser;

            while ((fromServer = reader.readLine()) != null){
                fromServer = fromServer.replace('#', '\n');
                System.out.println("Server: " + fromServer);

                if (fromServer.equals("Fine. Be that way. Goodbye.")){
                    System.exit(0);
                }

                fromUser = sysIn.readLine();
                if(fromUser != null){
                    System.out.println("Client: " + fromUser);
                    writer.println(fromUser);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
