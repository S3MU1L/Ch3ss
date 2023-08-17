package src.main.project.pieces;


import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import java.util.ArrayList;
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
        Rook rook = new Rook(getColor(), getBoard());
        Bishop bishop = new Bishop(getColor(), getBoard());
        List<Coordinates> result = new ArrayList<>();
        result.addAll(rook.getPossibleMoves());
        result.addAll(bishop.getPossibleMoves());
        return result;
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
