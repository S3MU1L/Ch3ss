package src.main.project.pieces;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import java.util.List;

/**
 * @author Samuel Malec
 */
public class Knight extends Piece {

    public Knight(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        return null;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u265E" : "\u2658";
    }
}
