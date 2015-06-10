package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloPiece extends JButton{
    public OthelloPieceState state = OthelloPieceState.None;
    public int index;
    private ImageIcon whitePiece, blackPiece, possible;
    public OthelloPiece(int index){
        this.index = index;
        this.setPreferredSize(new Dimension(69, 69));
        // get rid of borders
        this.setBackground(null);
        this.setOpaque(false);
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setContentAreaFilled(false);
        // icons
        whitePiece = new ImageIcon("WhitePiece.png");
        blackPiece = new ImageIcon("BlackPiece.png");
        possible = new ImageIcon("Possible.png");
    }
    public void changeState(OthelloPieceState newState){
        if(newState == OthelloPieceState.Black){
            this.setIcon(blackPiece);
        } else if(newState == OthelloPieceState.White){
            this.setIcon(whitePiece);
        } else if(newState == OthelloPieceState.Possible){
            this.setIcon(possible);
        } else {
            this.setIcon(null);
        }
        this.state = newState;
    }
}
