package src.main.project.pieces;

import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u2657" : "\u265D";
    }
}
