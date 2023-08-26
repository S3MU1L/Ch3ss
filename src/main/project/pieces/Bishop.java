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
public class Bishop extends Piece {
    private static final int[][] BBISHOP_POSITION_TABLE = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };

    private static final int[][] WBISHOP_POSITION_TABLE = {
            {-20, -10, -10, -10, -10, -10, -10, -20},
            {-10, 0, 0, 0, 0, 0, 0, -10},
            {-10, 0, 5, 10, 10, 5, 0, -10},
            {-10, 5, 5, 10, 10, 5, 5, -10},
            {-10, 0, 10, 10, 10, 10, 0, -10},
            {-10, 10, 10, 10, 10, 10, 10, -10},
            {-10, 5, 0, 0, 0, 0, 5, -10},
            {-20, -10, -10, -10, -10, -10, -10, -20}
    };

    public Bishop(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♝" : "♗";
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> allPossible = new ArrayList<>();
        Coordinates pieceCoords = getBoard().getCoordinatesOfPiece(this);
        for (int dx : new int[]{1, -1}) {
            for (int dy : new int[]{1, -1}) {
                Coordinates coords = new Coordinates(pieceCoords.x() + dx, pieceCoords.y() + dy);
                while (getBoard().validCoordinates(coords)) {
                    Piece piece = getBoard().getPieceAtCoordinates(coords);
                    if (piece == null) {
                        allPossible.add(coords);
                    } else {
                        if (piece.getColor().equals(getColor().getOppositeColor())) {
                            allPossible.add(coords);
                        }
                        break;
                    }
                    coords = new Coordinates(coords.x() + dx, coords.y() + dy);
                }
            }
        }
        return allPossible;
    }

    @Override
    public void move(int x, int y) {
        getBoard().movePiece(this, x, y);
    }

    @Override
    public ImageIcon getImageIcon() {
        String imageName = getColor().equals(Color.WHITE) ? "bishopW.png" : "bishopB.png";
        URL imageURL = getClass().getResource(IMG_FOLDER + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public int getValue() {
        return 330;
    }

    @Override
    public int[][] getPositionTable() {
        return getColor().equals(Color.WHITE) ? WBISHOP_POSITION_TABLE : BBISHOP_POSITION_TABLE;
    }
}
