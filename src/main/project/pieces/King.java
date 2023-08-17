package src.main.project.pieces;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Samuel Malec
 */
public class King extends Piece {
    private boolean hasMoved = false;
    private boolean inCheck = false;

    public King(Color color, Board board) {
        super(color, board);
    }

    public boolean isInCheck() {
        List<Piece> oppositePieces = getBoard().getPiecesOfColor(getColor().getOppositeColor());
        if (getBoard().getAllAttackedCoords(oppositePieces).contains(getBoard().getCoordinatesOfPiece(this))) {
            inCheck = true;
            return true;
        }
        return false;
    }

    public void setInCheck(boolean inCheck) {
        this.inCheck = inCheck;
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> result = new ArrayList<>();
        Coordinates pieceCoords = getBoard().getCoordinatesOfPiece(this);
        for (int dx : new int[]{1, 0, -1}) {
            for (int dy : new int[]{1, 0, -1}) {
                Coordinates coords = new Coordinates(pieceCoords.x() + dx, pieceCoords.y() + dy);
                if (dx == 0 && dy == 0) {
                    continue;
                }
                if (!getBoard().validCoordinates(coords)) {
                    continue;
                }

                Piece piece = getBoard().getPieceAtCoordinates(coords);
                if (piece != null && piece.getColor().equals(getColor())) {
                    continue;
                }
                result.add(coords);
            }
        }
        // we have to hardcode castling
        return result;
    }

    @Override
    public void move(int x, int y) {
        // TODO handle castling
        getBoard().movePiece(this, x, y);
        hasMoved = true;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u265A" : "\u2654";
    }

}
