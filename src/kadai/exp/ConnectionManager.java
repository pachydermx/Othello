package kadai.exp;

/**
 * Created by pachydermx on 15/06/10.
 */
public class ConnectionManager {
    public GameMode mode;
    public Server server;
    public Client client;
    private int port;
    private String hostname;
    public OthelloBoard ob;

    public ConnectionManager(){
        //this.init(GameMode.Server, null, 50000);
    }

    public void init(GameMode mode, String hostname, int port){
        this.mode = mode;
        this.hostname = hostname;
        this.port = port;
        if (mode == GameMode.Server){
            this.server = new Server(this, this.port);
            server.start();
        } else {
            this.client = new Client(this, this.hostname, this.port);
            client.start();
            ob.myTurn = false;
        }
    }

    public void sendMessage(String message){
        if (ob.myTurn) {
            if (this.mode == GameMode.Server) {
                this.server.sendMessage(message);
            } else if (this.mode == GameMode.Client) {
                this.client.sendMessage(message);
            }
            ob.myTurn = false;
        }
    }

    public void didReceivedMessage(String message){
        String[] location = message.split(",");
        if (location[0].equals("p")) {
            System.out.println("Try to place at " + location[1] + "," + location[2]);
            ob.placePiece(Integer.parseInt(location[1]), Integer.parseInt(location[2]), ob.currentState, false);
            ob.myTurn = true;
        } else {
            System.out.println("Unknown ID: " + location[0]);
        }
    }
}
