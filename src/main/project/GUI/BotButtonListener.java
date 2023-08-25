package src.main.project.GUI;

import src.main.project.GUI.utils.GameType;
import src.main.project.GUI.utils.GuiStarter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Samuel Malec
 */
public class BotButtonListener implements ActionListener {
    private final JFrame frame;

    public BotButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        GuiStarter.startGUI(GameType.BOT_VS_HUMAN);
    }
}
