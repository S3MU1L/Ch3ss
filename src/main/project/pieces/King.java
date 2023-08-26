package src.main.project.pieces;

import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;
import src.main.project.GUI.SoundPlayer;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author Samuel Malec
 */
public class King extends Piece {
    private boolean hasMoved = false;

    private static final int[][] WKING_POSITION_TABLE = {
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {20, 30, 10, 0, 0, 10, 30, 20},
    };

    private static final int[][] BKING_POSITION_TABLE = {
            {20, 30, 10, 0, 0, 10, 30, 20},
            {20, 20, 0, 0, 0, 0, 20, 20},
            {-10, -20, -20, -20, -20, -20, -20, -10},
            {-20, -30, -30, -40, -40, -30, -30, -20},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
            {-30, -40, -40, -50, -50, -40, -40, -30},
    };

    public King(Color color, Board board) {
        super(color, board);
    }

    public boolean isInCheck() {
        List<Piece> oppositePieces = getBoard().getPiecesOfColor(getColor().getOppositeColor());
        return getBoard().getAllAttackedCoords(oppositePieces).contains(getBoard().getCoordinatesOfPiece(this));
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
        return result;
    }

    public List<Coordinates> getCastleMoves() {

        List<Coordinates> result = new ArrayList<>();
        if (hasMoved) {
            return result;
        }
        Coordinates coords = getBoard().getCoordinatesOfPiece(this);
        Set<Coordinates> attackedSquares = getBoard().getAllAttackedCoords(getBoard().getPiecesOfColor(getColor().getOppositeColor()));
        if (canCastleKingSide(coords, attackedSquares)) {
            result.add(new Coordinates(coords.x() + 2, coords.y()));
        }
        if (canCastleQueenSide(coords, attackedSquares)) {
            result.add(new Coordinates(coords.x() - 2, coords.y()));
        }

        return result;
    }

    private boolean canCastleQueenSide(Coordinates coords, Set<Coordinates> attackedSquares) {
        int kingY = coords.y();

        Piece queensideRook = getBoard().getPieceAtCoordinates(0, kingY);
        if (!(queensideRook instanceof Rook) || ((Rook) queensideRook).hasMoved()) {
            return false;
        }

        for (int x = coords.x() - 1; x >= 1; x--) {
            if (getBoard().getPieceAtCoordinates(x, kingY) != null) {
                return false;
            }
        }

        return !attackedSquares.contains(coords) && !attackedSquares.contains(new Coordinates(coords.x() - 1, kingY)) &&
                !attackedSquares.contains(new Coordinates(coords.x() - 2, kingY));
    }

    private boolean canCastleKingSide(Coordinates coords, Set<Coordinates> attackedSquares) {
        int kingY = coords.y();

        Piece kingsideRook = getBoard().getPieceAtCoordinates(7, kingY);
        if (!(kingsideRook instanceof Rook) || ((Rook) kingsideRook).hasMoved()) {
            return false;
        }

        for (int x = coords.x() + 1; x <= 6; x++) {
            if (getBoard().getPieceAtCoordinates(x, kingY) != null) {
                return false;
            }
        }

        return !attackedSquares.contains(coords) && !attackedSquares.contains(new Coordinates(coords.x() + 1, kingY)) &&
                !attackedSquares.contains(new Coordinates(coords.x() + 2, kingY));
    }

    @Override
    public void move(int x, int y) {
        Coordinates kingCoords = getBoard().getCoordinatesOfPiece(this);
        if (!hasMoved && Math.abs(x - kingCoords.x()) == 2 && y == kingCoords.y()) {
            int rookX, rookNewX;
            if (x > kingCoords.x()) {
                rookX = 7;
                rookNewX = x - 1;
            } else {
                rookX = 0;
                rookNewX = x + 1;
            }
            SoundPlayer.playCastleSound();
            Piece rook = getBoard().getPieceAtCoordinates(rookX, y);
            getBoard().movePiece(rook, rookNewX, y);
        }
        getBoard().movePiece(this, x, y);
        hasMoved = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        String imageName = getColor().equals(Color.WHITE) ? "kingW.png" : "kingB.png";
        URL imageURL = getClass().getResource(IMG_FOLDER + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public int getValue() {
        return 20000;
    }

    @Override
    public int[][] getPositionTable() {
        return getColor().equals(Color.WHITE) ? WKING_POSITION_TABLE : BKING_POSITION_TABLE;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♚" : "♔";
    }

}
