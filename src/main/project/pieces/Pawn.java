package src.main.project.pieces;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class Pawn extends Piece {
    private boolean firstMove = true;
    private boolean enPassantPossible = false;

    public Pawn(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> allMoves = new ArrayList<>();
        Coordinates pieceCoords = getBoard().getCoordinatesOfPiece(this);
        int increment = getColor() == Color.WHITE ? -1 : 1;
        int x = pieceCoords.x();
        int y = pieceCoords.y();

        // moving forward
        for (int i : new int[]{1, 2}) {
            if (!firstMove && i != 1) {
                break;
            }
            if (!getBoard().validCoordinates(x, y + i * increment)) {
                break;
            }
            if (!getBoard().isEmpty(x, y + i * increment)) {
                break;
            }
            allMoves.add(new Coordinates(x, y + i * increment));
        }

        // taking a piece diagonally
        for (int i : new int[]{1, -1}) {
            if (!getBoard().validCoordinates(x + i, y + increment)) {
                continue;
            }

            Piece piece = getBoard().getPieceAtCoordinates(x + i, y + increment);
            Coordinates tempCoords = new Coordinates(x + i, y + increment);
            if (piece != null && piece.getColor().equals(getColor().getOppositeColor())) {
                allMoves.add(tempCoords);
            }

            Piece neighbour = getBoard().getPieceAtCoordinates(x + i, y);
            if (neighbour != null && neighbour.getColor().equals(getColor().getOppositeColor()) && neighbour instanceof Pawn) {
                if (((Pawn) neighbour).isEnPassantPossible()) {
                    allMoves.add(tempCoords);
                }
            }
        }
        return allMoves;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♟" : "♙";
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

    public void move(int x, int y) {
        Board board = getBoard();
        if (!board.validCoordinates(x, y)) {
            return;
        }

        Coordinates coords = board.getCoordinatesOfPiece(this);
        // checking enPassant
        int increment = getColor().equals(Color.WHITE) ? 1 : -1;
        if (!firstMove && board.isEmpty(x, y) && (Math.abs(x - coords.x()) + Math.abs(y - coords.y()) == 2)) {
            board.putPiece(null, x, y + increment);
        }

        board.putPiece(null, coords.x(), coords.y());
        board.putPiece(this, x, y);
        if (firstMove && Math.abs(coords.y() - y) == 2) {
            enPassantPossible = true;
        } else {
            enPassantPossible = false;
        }
        firstMove = false;
    }

    @Override
    public ImageIcon getImageIcon() {
        String path = IMG_FOLDER + File.separator + (getColor().equals(Color.WHITE) ? "pawnW.png" : "pawnB.png");
        return new ImageIcon(path);
    }
}
