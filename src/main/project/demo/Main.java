package src.main.project.demo;

import src.main.project.Board;
import src.main.project.GUI.ChessGUI;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        ChessGUI gui = new ChessGUI(board);
    }
}
