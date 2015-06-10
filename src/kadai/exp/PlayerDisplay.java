package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/10.
 */
public class PlayerDisplay extends JPanel{
    private OthelloPiece playerIcon;
    private JLabel playerLabel;

    public PlayerDisplay () {
        this.setPreferredSize(new Dimension(180, 85));

        playerIcon = new OthelloPiece(-1);
        playerLabel = new JLabel();
        playerLabel.setPreferredSize(new Dimension(85, 85));

        this.add(playerIcon);
        this.add(playerLabel);
    }

    public void show(OthelloPieceState playerColor, String playerName){
        this.playerIcon.changeState(playerColor);
        this.playerLabel.setText("Player :" + playerName);
    }
}

