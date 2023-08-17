package src.main.project.pieces;


import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author Samuel Malec
 */
public abstract class Piece {
    private static final AtomicLong COUNTER = new AtomicLong();
    private final long id = COUNTER.getAndIncrement();
    private final Color color;
    private Board board;

    public Piece(Color color, Board board) {
        this.color = color;
        this.board = board;
    }

    public Color getColor() {
        return color;
    }

    public long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public abstract List<Coordinates> getPossibleMoves();

    public abstract void move(int x, int y);

    public abstract ImageIcon getImageIcon();

    public void move(Coordinates coords) {
        move(coords.x(), coords.y());
    }

}
