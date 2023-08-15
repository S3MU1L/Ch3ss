package src.test;

import org.junit.jupiter.api.Test;
import src.main.project.Board;
import src.main.project.Color;
import src.main.project.pieces.King;
import src.main.project.pieces.Queen;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


/**
 * @author Samuel Malec
 */
public class BoardTest {
    private final Board board = new Board();

    @Test
    void putPieceAtBoard() {
        var piece = new King(Color.WHITE);
        assertNull(board.putPiece(piece, 3892189, 43829482));
        assertEquals(board.putPiece(piece, 3, 3).getId(), piece.getId());
    }

    @Test
    void getPieceAtCoordinates() {
        assertNull(board.getPieceAtCoordinates(5, 5));
        assertNull(board.getPieceAtCoordinates(2, 3));
        var piece = new Queen(Color.BLACK);
        assertEquals(board.putPiece(piece, 4, 0).getId(), piece.getId());
        assertEquals(board.getPieceAtCoordinates(4, 0).getId(), piece.getId());
    }


}
