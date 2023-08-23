package src.main.project.board;

/**
 * @author Samuel Malec
 */
public enum Color {
    WHITE,
    BLACK;

    /**
     * Get the opposite color of a piece
     * @return Color.WHITE for black pieces, Color.Black otherwise
     */
    public Color getOppositeColor() {
        if (this.equals(WHITE)) {
            return BLACK;
        }
        return WHITE;
    }
}
