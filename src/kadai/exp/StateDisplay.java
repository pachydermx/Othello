package kadai.exp;

import javax.swing.*;
import java.awt.*;

/**
 * Created by pachydermx on 15/06/10.
 */
public class StateDisplay extends JPanel{
    private JLabel label;
    public StateDisplay(){
        this.setPreferredSize(new Dimension(200, 22));
        label = new JLabel();
        label.setPreferredSize(new Dimension(180, 18));
        this.add(label);
    }
    public void setLabel(String text){
        this.label.setText(text);
    }
}
