package src.main.project.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import src.main.project.GUI.utils.GameType;
import src.main.project.GUI.utils.GuiStarter;
import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.players.HumanPlayer;
import src.main.project.players.Player;

import javax.swing.*;

/**
 * @author Samuel Malec
 */
public class HummanButtonListener implements ActionListener {
    private final JFrame frame;

    public HummanButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        GuiStarter.startGUI(GameType.OFFLINE_HUMAN_VS_HUMAN);

    }
}
