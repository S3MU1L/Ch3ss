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
        String imageName = getColor().equals(Color.WHITE) ? "knightW.png" : "knightB.png";
        URL imageURL = getClass().getResource(IMG_FOLDER + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public int getValue() {
        return 30;
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♞" : "♘";
    }
}
