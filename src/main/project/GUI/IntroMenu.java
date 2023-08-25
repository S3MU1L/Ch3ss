package src.main.project.GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import static src.main.project.GUI.GUIConstants.*;

/**
 * @author Samuel Malec
 */
public class IntroMenu {
    public IntroMenu() {
        JFrame frame = new JFrame("Main Menu");
        frame.setResizable(false);
        frame.setSize(600, 600);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        JLabel welcomeLabel = new JLabel("Select a game type: ");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(new EmptyBorder(NORTH_PADDING, PADDING, PADDING, PADDING));
        welcomeLabel.setFont(WELCOME_FONT);

        frame.add(welcomeLabel, BorderLayout.NORTH);

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

        JButton humanButton = new JButton("Against a human");
        panel.add(humanButton);
        humanButton.addActionListener(new HummanButtonListener(frame));

        JButton botButton = new JButton("Against a bot");
        panel.add(botButton);
        botButton.addActionListener(new BotButtonListener(frame));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}