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
    private ConnectionManager cm;
    BufferedReader in;

    public Server(ConnectionManager cm, int port){
        this.cm = cm;
        this.port = port;
    }

    public void run(){
        try{
            ss = new ServerSocket(port);
            // wait for connection
            clientSocket = ss.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String inputLine;
            out.println("server: hello");
            while ((inputLine = in.readLine()) != null){
                System.out.println("Server:: Received Message::" + inputLine);
                this.cm.didReceivedMessage(inputLine);
            }
        } catch (Exception e){
            System.out.println("Server Error");
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        System.out.println("Server:: Send Message::" + message);
        out.println(message);
    }

    public void closeConnection(){
        try {
            out.close();
            in.close();
            clientSocket.close();
            ss.close();
        } catch (Exception e) {

        }
    }
}
