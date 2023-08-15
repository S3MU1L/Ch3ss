package src.main.project.pieces;

import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u2659" : "\u265F";
    }
}
