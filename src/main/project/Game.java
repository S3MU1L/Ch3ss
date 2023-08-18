package src.main.project;

import src.main.project.GUI.ChessGUI;
import src.main.project.pieces.King;
import src.main.project.pieces.Piece;

import java.util.List;
import java.util.Scanner;

/**
 * @author Samuel Malec
 */
public class Game {
    private final Board board = new Board();
    private final Scanner scanner = new Scanner(System.in);
    private final Player playerOne = new Player(Color.WHITE);
    private final Player playerTwo = new Player(Color.BLACK);

    public void play() {
        board.initializeBoard();
        Player currentPlayer = playerOne;
        ChessGUI gui = new ChessGUI(board);

//        while (true) {
//            System.out.println("Current player: " + currentPlayer.getColor());
//            String input = scanner.nextLine();
//            String[] data = input.split(" ");
//            if (!Notation.isCorrectNotation(data[0]) || !Notation.isCorrectNotation(data[1])) {
//                System.out.println("Invalid notation, the correct format: <a,h><1,8>");
//                continue;
//            }
//
//            Coordinates from = Notation.coordsFromNotation(data[0]);
//            Coordinates to = Notation.coordsFromNotation(data[1]);
//            Piece piece = board.getPieceAtCoordinates(from);
//            if (piece == null) {
//                System.out.println("Invalid piece, please select a square that is not empty");
//                continue;
//            }
//
//            if (!piece.getColor().equals(currentPlayer.getColor())) {
//                System.out.println("Invalid piece, please select piece that has the color: " + currentPlayer.getColor());
//                continue;
//            }
//
//            List<Coordinates> possibleMoves = board.getOnlySafeMoves(piece);
//            for (var move : possibleMoves) {
//                System.out.println(move);
//            }
//
//            if (piece instanceof King) {
//                List<Coordinates> castleMoves = ((King) piece).getCastleMoves();
//                if (!castleMoves.contains(to) && !possibleMoves.contains(to)) {
//                    System.out.println("Invalid second coordinate");
//                    continue;
//                }
//            } else if (!possibleMoves.contains(to)) {
//                System.out.println("Invalid second coordinate");
//                continue;
//            }
//
//            piece.move(to);
//            currentPlayer = currentPlayer.equals(playerOne) ? playerTwo : playerOne;
//            gui.drawBoard();
//        }
    }
}
