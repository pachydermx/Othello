package kadai.exp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by pachydermx on 15/06/10.
 */
public class GameControl extends JPanel implements ActionListener{
    public JLabel modeDisplay;
    public JTextField hostnameInput, portInput;
    public JButton serverButton, clientButton;
    private ConnectionManager cm;

    public GameControl (ConnectionManager cm){
        this.cm = cm;
        this.setPreferredSize(new Dimension(180, 200));
        this.setLayout(new FlowLayout());

        modeDisplay = new JLabel("Single Player");
        modeDisplay.setPreferredSize(new Dimension(180, 20));
        hostnameInput = new JTextField("127.0.0.1");
        hostnameInput.setPreferredSize(new Dimension(180, 25));
        portInput = new JTextField("50000");
        portInput.setPreferredSize(new Dimension(180, 25));
        serverButton = new JButton("Create Server");
        clientButton = new JButton("Connect To Server");

        this.add(modeDisplay);
        this.add(hostnameInput);
        this.add(portInput);
        this.add(serverButton);
        this.add(clientButton);

        serverButton.addActionListener(this);
        clientButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == serverButton){
            this.cm.init(GameMode.Server, null, Integer.parseInt(portInput.getText()));
            modeDisplay.setText("Server Mode");
        } else if (e.getSource() == clientButton){
            this.cm.init(GameMode.Client, hostnameInput.getText(), Integer.parseInt(portInput.getText()));
            modeDisplay.setText("Client Mode");
        }
        hostnameInput.setEnabled(false);
        portInput.setEnabled(false);
        serverButton.setEnabled(false);
        clientButton.setEnabled(false);
    }
}
