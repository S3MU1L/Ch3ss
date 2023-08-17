package src.main.project.pieces;


import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import java.util.List;

/**
 * @author Samuel Malec
 */
public class Queen extends Piece {

    public Queen(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        return null;
    }

    @Override
    public void move(int x, int y) {
        getBoard().movePiece(this, x, y);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u265B" : "\u2655";
    }
}
