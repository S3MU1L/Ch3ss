package src.main.project.demo;

import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.GUI.ChessGUI;
import src.main.project.players.BotPlayer;
import src.main.project.players.HumanPlayer;
import src.main.project.players.Player;

import java.util.Random;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random rand = new Random();
        int x = rand.nextInt(2);
        Color firstColor = x == 1 ? Color.BLACK : Color.WHITE;
        Player first = new HumanPlayer(firstColor);
        Player second = new BotPlayer(firstColor.getOppositeColor());
        Board board = new Board(first, second);
        ChessGUI gui = new ChessGUI(board);
    }
}
