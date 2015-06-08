package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloBoard extends JPanel implements ActionListener{
    public OthelloPiece[] pieces;
    public Boolean currentWhite;
    public OthelloBoard() {
        // config panel
        this.setBackground(Color.gray);
        this.setPreferredSize(new Dimension(600, 600));
        this.setLayout(new FlowLayout());
        // config pieces
        for (OthelloPiece piece : pieces = new OthelloPiece[64]) {
            piece = new OthelloPiece();
            // config piece
            this.add(piece);
            piece.addActionListener(this);
        }
        // default values
        currentWhite = false;
    }

    public void actionPerformed(ActionEvent e){
        OthelloPiece thePiece = (OthelloPiece)e.getSource();
        if(currentWhite){
            thePiece.changeState(OthelloPieceState.White);
        } else {
            thePiece.changeState(OthelloPieceState.Black);
        }
        currentWhite = !currentWhite;
    }
}
