package src.main.project.demo;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;
import src.main.project.Notation;
import src.main.project.Player;
import src.main.project.pieces.Piece;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * @author Samuel Malec
 */
public class Main {
    public static void main(String[] args) {
        Board board = new Board();
        board.initializeBoard();
        System.out.println(board);
        Scanner scanner = new Scanner(System.in);
        Player playerOne = new Player(Color.WHITE);
        Player playerTwo = new Player(Color.BLACK);
        Player currentPlayer = playerOne;
        System.out.println("Write coordinates in the format: <from> <to>, eg. e4 e5");

        while (true) {
            String input = scanner.nextLine();
            String[] data = input.split(" ");

            if (!Notation.isCorrectNotation(data[0]) || !Notation.isCorrectNotation(data[1])) {
                System.out.println("Invalid notation, the correct format: <a,h><1,8>");
                continue;
            }

            Coordinates from = Notation.coordsFromNotation(data[0]);
            Coordinates to = Notation.coordsFromNotation(data[1]);
            Piece piece = board.getPieceAtCoordinates(from);
            board.movePiece(piece, to);
            currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
            System.out.println(board);
        }

    }
}
