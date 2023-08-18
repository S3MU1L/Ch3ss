package src.main.project;

import src.main.project.pieces.King;
import src.main.project.pieces.Piece;

import java.util.Set;

/**
 * @author Samuel Malec
 */
public enum GameState {
    PLAYING, WHITE, BLACK, CHECK, DRAW;

    public static GameState gameState(Board board) {
        Piece king = board.getKingOfColor(board.getCurrentColor());
        Set<Coordinates> moves = board.getPossibleMovesOfColor(board.getCurrentColor());
        boolean inCheck = ((King) king).isInCheck();
        if (moves.size() == 0 && inCheck) {
            return board.getCurrentColor().equals(Color.WHITE) ? BLACK : WHITE;
        }
        if (moves.size() == 0) {
            return DRAW;
        }
        return inCheck ? CHECK : PLAYING;
    }
}
