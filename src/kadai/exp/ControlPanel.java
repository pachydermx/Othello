package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pachydermx on 15/06/10.
 */
public class ControlPanel extends JPanel{
    public PlayerDisplay pd;
    public ScoreBoard sb;
    public SpaceRemainDisplay srd;
    public ConnectionManager cm;
    public GameControl gc;

    public ControlPanel(){
        // config panel
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(200, 600));
        this.setLayout(new FlowLayout());

        // connection
        cm = new ConnectionManager();

        // config widgets
        pd = new PlayerDisplay();
        this.add(pd);
        sb = new ScoreBoard();
        this.add(sb);
        srd = new SpaceRemainDisplay();
        this.add(srd);
        gc = new GameControl(cm);
        this.add(gc);
    }

    public void update(OthelloPieceState state, String playerName, int blackScore, int whiteScore){
        pd.show(state, playerName);
        sb.showScore(blackScore, whiteScore);
        srd.showRemain(64 - blackScore - whiteScore);
    }

}
