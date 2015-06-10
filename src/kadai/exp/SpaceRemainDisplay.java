package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/10.
 */
public class SpaceRemainDisplay extends JPanel{
    JLabel spaceRemainLabel;
    public SpaceRemainDisplay() {
        this.setPreferredSize(new Dimension(180, 30));
        spaceRemainLabel = new JLabel();
        spaceRemainLabel.setPreferredSize(new Dimension(160, 18));
        this.add(spaceRemainLabel);
    }

    public void showRemain(int remain){
        this.spaceRemainLabel.setText("Space Remaining: " + Integer.toString(remain));
    }
}
