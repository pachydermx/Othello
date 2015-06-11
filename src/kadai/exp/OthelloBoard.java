package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloBoard extends JPanel implements ActionListener{
    public OthelloPiece[] pieces;
    public OthelloGame game;
    public OthelloPieceState currentState;
    public ControlPanel cp;
    public String blackName, whiteName;
    public boolean myTurn = true;

    public OthelloBoard() {
        // config panel
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new FlowLayout());

        // config pieces
        this.pieces = new OthelloPiece[64];
        for (int i = 0; i < 64; i++) {
            this.pieces[i] = new OthelloPiece(i);
            // config piece
            this.add(pieces[i]);
            pieces[i].addActionListener(this);
        }
        // default values
        this.currentState = OthelloPieceState.Black;

        // set names
        this.blackName = "Black";
        this.whiteName = "White";

    }

    public void newGame() {
        // config game
        game = new OthelloGame();
        game.pieces[3][3] = OthelloPieceState.White;
        game.pieces[4][4] = OthelloPieceState.White;
        game.pieces[3][4] = OthelloPieceState.Black;
        game.pieces[4][3] = OthelloPieceState.Black;
        this.updateBoard();
    }

    private void updateBoard(){
        // scan possible steps
        this.game.scan(currentState);
        // get board
        OthelloPieceState[] newState = this.game.getBoard();

        // perform
        for (int i = 0; i < 64; i++){
            this.pieces[i].changeState(newState[i]);
        }
        this.tryToUpdateControlPanel();
    }

    private void tryToUpdateControlPanel(){
        try {
            String stateName;
            OthelloPieceState next;
            if (this.currentState == OthelloPieceState.Black){
                stateName = blackName;
                next = OthelloPieceState.Black;
            } else {
                stateName = whiteName;
                next = OthelloPieceState.White;
            }
            cp.update(next, stateName, this.game.blackScore, this.game.whiteScore, this.game.possibleSteps);
        } catch (Exception e){
            System.out.println("Failed on Update Control Panel");
        }
    }

    private void tryToSendPiece(int x, int y){
        try {
            cp.cm.sendMessage("p," + x + ","+ y);
        } catch (Exception e){
            System.out.println("Failed on Sending Piece");
        }
    }

    private int[] getXYfromIndex(int index){
        return new int[]{index / 8, index % 8};
    }

    private void changePlayer() {
        if (this.currentState == OthelloPieceState.Black){
            this.currentState = OthelloPieceState.White;
        } else {
            this.currentState = OthelloPieceState.Black;
        }
    }

    public void actionPerformed(ActionEvent e){
        if (myTurn) {
            OthelloPiece thePiece = (OthelloPiece) e.getSource();
            int[] location = this.getXYfromIndex(thePiece.index);
            this.placePiece(location[0], location[1], currentState, true);
        }
    }

    public boolean placePiece(int x, int y, OthelloPieceState color, boolean send){
        System.out.println(String.format("Trying to Place %d, %d", x, y));
        boolean success = this.game.placePiece(x, y, color, true);
        this.updateBoard();
        if (success) {
            if (send) {
                this.tryToSendPiece(x, y);
            }
            this.changePlayer();
            System.out.println("... success");

            File log = new File("log.txt");
            try {
                log.createNewFile();
                FileOutputStream fos = new FileOutputStream(log, true);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                PrintWriter pw = new PrintWriter(osw);
                pw.println(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()) + String.format(" - Piece at %d, %d", x, y));
                pw.close();
            } catch (Exception e){
                e.printStackTrace();
            }

            // logger

        } else {
            System.out.println("... failed");
        }
        this.updateBoard();
        return success;
    }
}
