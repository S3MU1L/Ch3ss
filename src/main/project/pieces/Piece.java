package src.main.project.pieces;


import src.main.project.Color;

import java.util.concurrent.atomic.AtomicLong;


/**
 * @author Samuel Malec
 */
public class Piece {
    private static final AtomicLong COUNTER = new AtomicLong();
    private final long id = COUNTER.getAndIncrement();
    private Color color;

    public Piece(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return id;
    }
}
