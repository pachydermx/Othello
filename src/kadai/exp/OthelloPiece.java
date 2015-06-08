package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by pachydermx on 15/06/08.
 */
public class OthelloPiece extends JButton{
    public OthelloPieceState state = OthelloPieceState.None;
    public OthelloPiece(){
        this.setPreferredSize(new Dimension(69, 69));
        this.setBackground(Color.blue);
    }
    public void changeState(OthelloPieceState newState){
        System.out.println(newState);
        if(newState == OthelloPieceState.Black){
            this.setForeground(Color.black);
        }
        this.state = newState;
    }
}
