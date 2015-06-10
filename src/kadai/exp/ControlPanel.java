package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/10.
 */
public class ControlPanel extends JPanel{
    PlayerDisplay pd;
    ScoreBoard sb;
    SpaceRemainDisplay srd;
    Server server;

    public ControlPanel(){
        // config panel
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new FlowLayout());

        // config widgets
        pd = new PlayerDisplay();
        this.add(pd);
        sb = new ScoreBoard();
        this.add(sb);
        srd = new SpaceRemainDisplay();
        this.add(srd);
    }

    public void update(OthelloPieceState state, String playerName, int blackScore, int whiteScore){
        pd.show(state, playerName);
        sb.showScore(blackScore, whiteScore);
        srd.showRemain(64 - blackScore - whiteScore);
    }

}
