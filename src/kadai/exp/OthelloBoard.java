package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloBoard extends JPanel implements ActionListener{
    public OthelloPiece[] pieces;
    public OthelloGame game;
    public OthelloPieceState currentState;

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

        // config game
        game = new OthelloGame();
        game.pieces[1][1] = OthelloPieceState.White;
        game.pieces[2][2] = OthelloPieceState.White;
        game.pieces[1][2] = OthelloPieceState.Black;
        game.pieces[2][1] = OthelloPieceState.Black;
        this.updateBoard();

    }

    private void updateBoard(){
        OthelloPieceState[] newState = this.game.getBoard();
        System.out.println(Arrays.toString(newState));

        for (int i = 0; i < 64; i++){
            this.pieces[i].changeState(newState[i]);
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
        OthelloPiece thePiece = (OthelloPiece)e.getSource();
        int[] location = this.getXYfromIndex(thePiece.index);
        boolean success = this.game.placePiece(location[0], location[1], currentState);
        this.updateBoard();
        if (success) this.changePlayer();
    }
}
