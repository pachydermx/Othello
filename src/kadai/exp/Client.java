package kadai.exp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by pachydermx on 15/06/10.
 */
public class Client extends Thread{
    private int port;
    private String hostname;
    private PrintWriter out;
    private Socket serverSocket;
    private ConnectionManager cm;

    public Client(ConnectionManager cm, String hostname, int port){
        this.cm = cm;
        this.hostname = hostname;
        this.port = port;
    }

    public void run(){
        try {
            serverSocket = new Socket(this.hostname, this.port);
            out = new PrintWriter(serverSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            String inStr;
            out.println("client: hello");
            while ((inStr = in.readLine()) != null){
                System.out.println("Client:: Received Message::" + inStr);
                this.cm.didReceivedMessage(inStr);
            }
            out.close();
            in.close();
            serverSocket.close();
        } catch (Exception e) {
            System.out.println("Client Error");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        System.out.println("Client:: Send Message::" + message);
        this.out.println(message);
    }
}
