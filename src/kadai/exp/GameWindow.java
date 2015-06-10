package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/08.
 */
public class GameWindow extends JFrame{
    public GameWindow() {
        // create a game window
        this.setTitle("Othello");
        this.setSize(new Dimension(830, 630));
        this.setResizable(false);
        this.setLayout(new FlowLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create othello board
        OthelloBoard ob = new OthelloBoard();
        this.add(ob);

        // create control panel
        ControlPanel cp = new ControlPanel();
        this.add(cp);

        ob.cp = cp;

        // show window
        this.setVisible(true);

        // start game
        ob.newGame();
    }
}
