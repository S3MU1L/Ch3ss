package src.main.project.pieces;


import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;

import javax.swing.*;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


/**
 * @author Samuel Malec
 */
public abstract class Piece {
    private static final AtomicLong COUNTER = new AtomicLong();
    protected final String IMG_FOLDER = File.separator + "img" + File.separator;
    private final long id = COUNTER.getAndIncrement();
    private final Color color;
    private final Board board;

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
        board.changeCurrentPlayer();

        List<Piece> pieces = board.getPiecesOfColor(board.getCurrentColor());
        for (Piece piece : pieces) {
            if (piece instanceof Pawn) {
                ((Pawn) piece).setEnPassantPossible(false);
            }
        }

    }

}
