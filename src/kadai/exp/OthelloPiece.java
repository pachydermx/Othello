package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloPiece extends JButton{
    public OthelloPieceState state = OthelloPieceState.None;
    private ImageIcon whitePiece, blackPiece;
    public OthelloPiece(){
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
    }
    public void changeState(OthelloPieceState newState){
        if(newState == OthelloPieceState.Black){
            this.setIcon(blackPiece);
        }
        if(newState == OthelloPieceState.White){
            this.setIcon(whitePiece);
        }
        this.state = newState;
    }
}
