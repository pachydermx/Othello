package kadai.exp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by pachydermx on 15/06/10.
 */
public class Server extends Thread{
    private ServerSocket ss;
    private Socket clientSocket;
    private PrintWriter out;
    private int port;

    public Server(int port){
        this.port = port;
    }

    public void run(){
        try{
            ss = new ServerSocket(port);
            // wait for connection
            clientSocket = ss.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            out.println("hello");
            while ((inputLine = in.readLine()) != null){
                System.out.println("Server: " + inputLine);
            }
            out.close();
            in.close();
            clientSocket.close();
            ss.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void sendMessage(String message){
        out.println(message);
    }
}
