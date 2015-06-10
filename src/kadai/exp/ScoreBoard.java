package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/10.
 */
public class ScoreBoard extends JPanel{
    private JLabel blackScoreLabel, whiteScoreLabel;

    public ScoreBoard(){
        this.setPreferredSize(new Dimension(180, 180));
        OthelloPiece whitePiece = new OthelloPiece(-1);
        whitePiece.changeState(OthelloPieceState.White);
        OthelloPiece blackPiece = new OthelloPiece(-1);
        blackPiece.changeState(OthelloPieceState.Black);
        blackScoreLabel = new JLabel();
        blackScoreLabel.setPreferredSize(new Dimension(80, 80));
        whiteScoreLabel = new JLabel();
        whiteScoreLabel.setPreferredSize(new Dimension(80, 80));
        this.add(blackPiece);
        this.add(blackScoreLabel);
        this.add(whitePiece);
        this.add(whiteScoreLabel);
    }
    public void showScore(int blackScore, int whiteScore){
        this.blackScoreLabel.setText("Score :" + Integer.toString(blackScore));
        this.whiteScoreLabel.setText("Score :" + Integer.toString(whiteScore));
    }
}
