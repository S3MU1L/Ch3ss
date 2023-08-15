package src.main.project.pieces;

import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class Rook extends Piece {
    public Rook(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u2656" : "\u265C";
    }

}
