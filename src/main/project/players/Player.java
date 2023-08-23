package src.main.project.players;

import src.main.project.board.Color;

/**
 * @author Samuel Malec
 */
public class Player {
    private final Color color;
    private int timeLeft = PlayerTimeConstants.START_TIME;

    public Player(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void decrementTime() {
        timeLeft -= PlayerTimeConstants.DECREMENT;
    }

}
