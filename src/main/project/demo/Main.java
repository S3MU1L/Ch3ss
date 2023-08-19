package src.main.project.demo;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.GUI.ChessGUI;
import src.main.project.players.BotPlayer;
import src.main.project.players.HumanPlayer;
import src.main.project.players.Player;

import java.util.Random;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) {
        Random rand = new Random();
        int x = rand.nextInt(2);
        Color firstColor = x == 1 ? Color.BLACK : Color.WHITE;
        Player first = new HumanPlayer(firstColor);
        Player second = new BotPlayer(firstColor.getOppositeColor());
        Board board = new Board(first, second);
        board.initializeBoard();
        ChessGUI gui = new ChessGUI(board);
    }
}
