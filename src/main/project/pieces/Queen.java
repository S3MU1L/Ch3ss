package src.main.project.pieces;


import src.main.project.Color;

/**
 * @author Samuel Malec
 */
public class Queen extends Piece {

    public Queen(Color color) {
        super(color);
    }

    @Override
    public String toString() {
        return getColor().equals(Color.WHITE) ? "\u2655" : "\u265B";
    }
}
