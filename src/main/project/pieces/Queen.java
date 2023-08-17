package src.main.project.pieces;


import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import javax.swing.*;
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
        List<Coordinates> result = new ArrayList<>();
        Coordinates queenCoordinates = getBoard().getCoordinatesOfPiece(this);

        getBoard().putPiece(rook, queenCoordinates);
        result.addAll(rook.getPossibleMoves());

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
        String path = getColor().equals(Color.WHITE) ? "img/queenW.png" : "img/queenB.png";
        return new ImageIcon(path);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "♛" : "♕";
    }
}
