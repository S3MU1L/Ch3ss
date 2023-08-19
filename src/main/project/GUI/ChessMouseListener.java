package src.main.project.GUI;

import src.main.project.Coordinates;
import src.main.project.pieces.King;
import src.main.project.pieces.Piece;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class ChessMouseListener implements MouseListener {
    private ChessGUI gui;

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
        Coordinates coords = new Coordinates(x, y);

        if (currPiece != null && currPiece.getColor().equals(gui.getBoard().getCurrentColor())) {
            List<Coordinates> possibleMoves = gui.getBoard().getSafeMovesOfPiece(currPiece);
            if (currPiece instanceof King) {
                possibleMoves.addAll(((King) currPiece).getCastleMoves());
            }
            gui.setPossibleMoves(possibleMoves);
            gui.setFirstClick(coords);
        } else if (gui.getPossibleMoves() != null && gui.getPossibleMoves().contains(coords)) {
            if (gui.getBoard().isEmpty(coords)) {
                SoundPlayer.playMoveSound();
            } else {
                SoundPlayer.playCaptureSound();
            }
            gui.getBoard().getPieceAtCoordinates(gui.getFirstClick()).move(coords);
            gui.setState();
            gui.setFirstClick(null);
            gui.setPossibleMoves(null);
        }
        else {
            SoundPlayer.playIllegalSound();
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
