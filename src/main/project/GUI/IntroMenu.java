package src.main.project.GUI.introGUI;

import javax.swing.*;
import java.awt.*;

/**
 * @author Samuel Malec
 */
public class IntroMenu {
    public IntroMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Please choose the type of game you want to play: ");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JButton humanButton = new JButton("Against a human");
        panel.add(humanButton);
        humanButton.addActionListener(new HummanButtonListener(frame));

        JButton botButton = new JButton("Against a bot");
        panel.add(botButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}