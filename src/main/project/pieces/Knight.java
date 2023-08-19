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
public class Knight extends Piece {

    public Knight(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        List<Coordinates> allMoves = new ArrayList<>();
        Coordinates current = getBoard().getCoordinatesOfPiece(this);
        int[] dxValues = {1, -1, 2, -2};
        int[] dyValues = {1, -1, 2, -2};

        for (int dx : dxValues) {
            for (int dy : dyValues) {
                if (Math.abs(dx) == Math.abs(dy)) {
                    continue;
                }

                Coordinates tempCoords = new Coordinates(current.x() + dx, current.y() + dy);
                if (!getBoard().validCoordinates(tempCoords)) {
                    continue;
                }

                Piece piece = getBoard().getPieceAtCoordinates(tempCoords);
                if (piece != null && piece.getColor().equals(getColor())) {
                    continue;
                }

                allMoves.add(tempCoords);
            }
        }
        return allMoves;
    }

    @Override
    public void move(int x, int y) {
        getBoard().movePiece(this, x, y);
    }

    @Override
    public ImageIcon getImageIcon() {
        String path = IMG_FOLDER + File.separator + (getColor().equals(Color.WHITE) ? "knightW.png" : "knightB.png");
        return new ImageIcon(path);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♞" : "♘";
    }
}
