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
    public StateDisplay sd;

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
        sd = new StateDisplay();
        this.add(sd);
    }

    public void update(OthelloPieceState state, String playerName, int blackScore, int whiteScore, int possibleSteps){
        pd.show(state, playerName);
        sb.showScore(blackScore, whiteScore);
        int remain = 64 - blackScore - whiteScore;
        srd.showRemain(remain);
        if (remain == 0 || possibleSteps == 0){
            if (blackScore > whiteScore){
                sd.setLabel("Black Wins");
            } else if (blackScore < whiteScore){
                sd.setLabel("White Wins");
            } else {
                sd.setLabel("TIE");
            }
        }
    }

}
