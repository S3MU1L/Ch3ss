package src.main.project.pieces;

import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class Knight extends Piece {
    public Knight(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return  getColor().equals(Color.WHITE) ? "\u2658" : "\u265E";
    }
}
