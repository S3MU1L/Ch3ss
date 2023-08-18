package src.main.project.GUI;

import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.pieces.Piece;

import java.util.List;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Samuel Malec
 */
public class ChessMouseListener implements MouseListener {
    private ChessGUI gui;
    private Board board;
    private Piece piece;
    private boolean firstClick = true;

    public ChessMouseListener(ChessGUI gui, Board board) {
        this.gui = gui;
        this.board = board;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private int getRow(int y) {
        return y / ChessGUI.SQUARE_SIZE;
    }

    private int getColumn(int x) {
        return x / ChessGUI.SQUARE_SIZE;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = getColumn(e.getX());
        int y = getRow(e.getY());
        Piece currPiece = board.getPieceAtCoordinates(x, y);

        if (firstClick && (currPiece == null || !currPiece.getColor().equals(board.getCurrentColor()))) {
            gui.setAttackedCoordinates(null);
            gui.drawBoard();
            return;
        }

        if (currPiece != null && currPiece.getColor().equals(board.getCurrentColor())) {
            firstClick = false;
            piece = currPiece;
            gui.setAttackedCoordinates(currPiece.getPossibleMoves());
            gui.drawBoard();
            return;
        }

        if (!firstClick) {
            Coordinates coords = new Coordinates(x, y);
            if (gui.getAttackedCoordinates().contains(coords)) {
                board.movePiece(piece, coords);
                board.changeCurrentColor();
            }
            gui.setAttackedCoordinates(null);
            gui.drawBoard();
            firstClick = true;
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
