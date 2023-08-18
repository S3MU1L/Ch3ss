package src.main.project.GUI;

import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.pieces.King;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class ChessMouseListener implements MouseListener {
    private ChessGUI gui;
    private Piece piece;
    private boolean firstClick = true;

    public ChessMouseListener(ChessGUI gui) {
        this.gui = gui;
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
        Piece currPiece = gui.getBoard().getPieceAtCoordinates(x, y);

        if (firstClick && (currPiece == null || !currPiece.getColor().equals(gui.getBoard().getCurrentColor()))) {
            gui.setAttackedCoordinates(null);
        } else if (currPiece != null && currPiece.getColor().equals(gui.getBoard().getCurrentColor())) {
            firstClick = false;
            piece = currPiece;
            List<Coordinates> safeMoves = gui.getBoard().getOnlySafeMoves(piece);
            if (piece instanceof King) {
                List<Coordinates> castleCoords = ((King) piece).getCastleMoves();
                safeMoves.addAll(castleCoords);
            }
            gui.setAttackedCoordinates(safeMoves);
        } else if (!firstClick) {
            Coordinates coords = new Coordinates(x, y);
            if (piece instanceof King) {
                List<Coordinates> castleMoves = ((King) piece).getCastleMoves();
                if (castleMoves.contains(coords) || gui.getAttackedCoordinates().contains(coords)) {
                    piece.move(coords);
                }
            } else if (gui.getAttackedCoordinates().contains(coords)) {
                piece.move(coords);
            }
            gui.setAttackedCoordinates(null);
            firstClick = true;
        }
        gui.drawBoard();
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
