package src.main.project.players;

import src.main.project.board.Color;

/**
 * @author Samuel Malec
 */
public class Player {
    private final Color color;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

}
