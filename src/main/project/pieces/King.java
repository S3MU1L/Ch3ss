package src.main.project.pieces;

import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class King extends Piece {
    public King(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u2654" : "\u265A";
    }
}
