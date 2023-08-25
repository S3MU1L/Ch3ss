package src.main.project.GUI;

import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;
import src.main.project.pieces.King;
import src.main.project.pieces.Piece;
import src.main.project.players.BotPlayer;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import static src.main.project.GUI.GUIConstants.SQUARE_SIZE;

/**
 * @author Samuel Malec
 */
public class ChessMouseListener implements MouseListener {
    private final ChessGUI gui;

    public ChessMouseListener(ChessGUI gui) {
        this.gui = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    private int getRow(int y) {
        int row = y / SQUARE_SIZE;
        if (gui.getBoard().getCurrentPlayer() instanceof BotPlayer && gui.getBoard().getCurrentColor().equals(Color.WHITE)) {
            return Board.BOARD_SIZE - 1 - row;
        }
        if (gui.getBoard().getCurrentPlayer() instanceof BotPlayer && gui.getBoard().getCurrentColor().equals(Color.BLACK)) {
            return row;
        }
        if (gui.getBoard().getCurrentColor().equals(Color.WHITE)) {
            return row;
        }
        return Board.BOARD_SIZE - 1 - row;
    }

    private int getColumn(int x) {
        return x / SQUARE_SIZE;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = getColumn(e.getX());
        int y = getRow(e.getY());
        Piece currPiece = gui.getBoard().getPieceAtCoordinates(x, y);
        Coordinates coords = new Coordinates(x, y);

        if (gui.getPossibleMoves() != null && gui.getPossibleMoves().contains(coords)) {
            if (gui.getBoard().isEmpty(coords)) {
                SoundPlayer.playMoveSound();
            } else {
                SoundPlayer.playCaptureSound();
            }
            gui.getBoard().getPieceAtCoordinates(gui.getFirstClick()).move(coords);
            gui.setFirstClick(null);
            gui.setPossibleMoves(null);
            gui.resetTimerTick();
            gui.drawBoard();
            gui.setState();
        } else if (currPiece != null && currPiece.getColor().equals(gui.getBoard().getCurrentColor())) {
            List<Coordinates> possibleMoves = gui.getBoard().getSafeMovesOfPiece(currPiece);
            if (currPiece instanceof King) {
                possibleMoves.addAll(((King) currPiece).getCastleMoves());
            }
            gui.setPossibleMoves(possibleMoves);
            gui.setFirstClick(coords);
            gui.drawBoard();
        } else {
            SoundPlayer.playIllegalSound();
            gui.drawBoard();
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
