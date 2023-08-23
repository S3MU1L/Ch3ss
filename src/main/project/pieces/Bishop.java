package src.main.project.pieces;

import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class Bishop extends Piece {
    public Bishop(Color color, Board board) {
        super(color, board);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u265D" : "\u2657";
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
        URL imageURL = getClass().getResource("/img/" + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }
}
