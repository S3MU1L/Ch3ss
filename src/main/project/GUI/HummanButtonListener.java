package src.main.project.GUI.introGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import src.main.project.GUI.chessBoardGUI.ChessGUI;
import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.players.HumanPlayer;
import src.main.project.players.Player;

import javax.swing.*;

/**
 * @author Samuel Malec
 */
public class HummanButtonListener implements ActionListener {
    private JFrame frame;

    public HummanButtonListener(JFrame frame) {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        frame.dispose();
        Random rand = new Random();
        int x = rand.nextInt(2);
        Color firstColor = x == 1 ? Color.BLACK : Color.WHITE;
        Player first = new HumanPlayer(firstColor);
        Player second = new HumanPlayer(firstColor.getOppositeColor());
        Board board = new Board(first, second);
        ChessGUI gui = new ChessGUI(board);

    }
}
