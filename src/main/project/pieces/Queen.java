package src.main.project.pieces;


import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class Queen extends Piece {

    public Queen(Color color, Board board) {
        super(color, board);
    }

    @Override
    public List<Coordinates> getPossibleMoves() {
        Rook rook = new Rook(getColor(), getBoard());
        Bishop bishop = new Bishop(getColor(), getBoard());
        Coordinates queenCoordinates = getBoard().getCoordinatesOfPiece(this);

        getBoard().putPiece(rook, queenCoordinates);
        List<Coordinates> result = new ArrayList<>(rook.getPossibleMoves());

        getBoard().putPiece(bishop, queenCoordinates);
        result.addAll(bishop.getPossibleMoves());

        getBoard().putPiece(this, queenCoordinates);
        return result;
    }

    @Override
    public void move(int x, int y) {
        getBoard().movePiece(this, x, y);
    }

    @Override
    public ImageIcon getImageIcon() {
        String imageName = getColor().equals(Color.WHITE) ? "queenW.png" : "queenB.png";
        URL imageURL = getClass().getResource(File.separator + "img" + File.separator + imageName);
        assert imageURL != null;
        return new ImageIcon(imageURL);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♛" : "♕";
    }
}
