package src.main.project.pieces;

import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class Pawn extends Piece {
    private boolean firstMove = true;
    private boolean enPassantPossible = false;

    private static final int[][] BPAWN_POSITION_TABLE = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000}
    };

    private static final int[][] WPAWN_POSITION_TABLE = {
            {1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {0, 0, 0, 0, 0, 0, 0, 0}
    };

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
        enPassantPossible = firstMove && Math.abs(coords.y() - y) == 2;
        firstMove = false;
    }

    @Override
    public ImageIcon getImageIcon() {
        String imageName = getColor().equals(Color.WHITE) ? "pawnW.png" : "pawnB.png";
        URL imageURL = getClass().getResource(IMG_FOLDER + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public int getValue() {
        return 100;
    }

    @Override
    public int[][] getPositionTable() {
        return getColor().equals(Color.WHITE) ? WPAWN_POSITION_TABLE : BPAWN_POSITION_TABLE;
    }
}
