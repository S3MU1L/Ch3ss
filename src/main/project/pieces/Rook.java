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
public class Rook extends Piece {
    private boolean moved = false;

    public Rook(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> allMoves = new ArrayList<>();
        Coordinates pieceCoords = getBoard().getCoordinatesOfPiece(this);
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        for (int i = 0; i < dx.length; i++) {
            int x = dx[i];
            int y = dy[i];
            Coordinates coords = new Coordinates(pieceCoords.x() + x, pieceCoords.y() + y);
            while (getBoard().validCoordinates(coords)) {
                Piece piece = getBoard().getPieceAtCoordinates(coords);
                if (piece == null) {
                    allMoves.add(coords);
                } else {
                    if (piece.getColor().equals(getColor().getOppositeColor())) {
                        allMoves.add(coords);
                    }
                    break;
                }
                coords = new Coordinates(coords.x() + x, coords.y() + y);
            }
        }
        return allMoves;
    }

    @Override
    public void move(int x, int y) {
        getBoard().movePiece(this, x, y);
        moved = true;
    }

    @Override
    public ImageIcon getImageIcon() {
        String imageName = getColor().equals(Color.WHITE) ? "rookW.png" : "rookB.png";
        URL imageURL = getClass().getResource("/img/" + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♜" : "♖";
    }

    public boolean hasMoved() {
        return moved;
    }

}
