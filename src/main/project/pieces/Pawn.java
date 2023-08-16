package src.main.project.pieces;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class Pawn extends Piece {
    private final boolean firstMove = true;
    private boolean enPassantPossible = false;

    public Pawn(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> result = new ArrayList<>();
        Coordinates pieceCoords = getBoard().findPieceById(this);
        int increment = getColor() == Color.WHITE ? -1 : 1;
        int x = pieceCoords.x();
        int y = pieceCoords.y();

        // moving forward
        for (int i : new int[]{1, 2}) {
            if (!firstMove && i == 2) {
                continue;
            }
            if (!getBoard().validCoordinates(x, y + i * increment)) {
                continue;
            }
            if (!getBoard().isEmpty(x, y + i * increment)) {
                continue;
            }
            result.add(new Coordinates(x, y + i * increment));
        }

        // taking a piece diagonally
        for (int i : new int[]{1, -1}) {
            if (getBoard().validCoordinates(x + i, y + increment) && !getBoard().isEmpty(x + i, y + increment)) {
                result.add(new Coordinates(x + i, y + increment));
            }

            Piece neighbour = getBoard().getPieceAtCoordinates(x + i, y);
            if (neighbour != null && neighbour.getColor().equals(getColor().getOppositeColor()) && neighbour instanceof Pawn) {
                if (((Pawn) neighbour).isEnPassantPossible()) {
                    result.add(new Coordinates(x + i, y + increment));
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u265F" : "\u2659";
    }

    public boolean isFirstMove() {
        return firstMove;
    }

    public boolean isEnPassantPossible() {
        return enPassantPossible;
    }

    public void setEnPassantPossible(boolean enPassantPossible) {
        this.enPassantPossible = enPassantPossible;
    }
}
