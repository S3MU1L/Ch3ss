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
        frame.setSize(INTRO_WIDTH, INTRO_HEIGHT);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel welcomeLabel = new JLabel("Select a game type: ");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        welcomeLabel.setFont(WELCOME_FONT);
        frame.add(welcomeLabel, BorderLayout.NORTH);
        frame.getContentPane().setBackground(INTRO_COLOR);

        JPanel panel = new JPanel(new GridLayout(2, 1, PADDING, PADDING));
        panel.setBackground(INTRO_COLOR);
        panel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));

        JButton humanButton = createStyledButton("Against a human");
        panel.add(humanButton);
        humanButton.addActionListener(new HumanButtonListener(frame));

        JButton botButton = createStyledButton("Against a bot");
        panel.add(botButton);
        botButton.addActionListener(new BotButtonListener(frame));

        frame.add(panel, BorderLayout.CENTER);
    }

    public static JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(46, 204, 113));
        button.setForeground(Color.BLACK);
        button.setBorder(new EmptyBorder(5, 5, 5, 5));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(39, 174, 96));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(46, 204, 113));
            }
        });

        return button;
    }
}