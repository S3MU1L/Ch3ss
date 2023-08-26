package src.main.project.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.main.project.GUI.utils.GameType;
import src.main.project.GUI.utils.GuiStarter;

import javax.swing.*;

/**
 * @author Samuel Malec
 */
public class HumanButtonListener implements ActionListener {
    private final JFrame frame;

    public HumanButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
            GuiStarter.startGUI(GameType.OFFLINE_HUMAN_VS_HUMAN);
        });
    }
}
