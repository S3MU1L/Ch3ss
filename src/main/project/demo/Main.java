package src.main.project.demo;

import src.main.project.Board;
import src.main.project.Game;

import java.util.Scanner;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        System.out.println(board);
    }
}
