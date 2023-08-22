package src.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;
import src.main.project.pieces.Piece;
import src.main.project.players.Player;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player(Color.WHITE);
        player2 = new Player(Color.BLACK);
        board = new Board(player1, player2);
        board.initializeBoard();
    }

    @Test
    public void testGetCurrentColor() {
        assertEquals(Color.WHITE, board.getCurrentColor());
    }

    @Test
    public void testGetCurrentPlayer() {
        assertEquals(player1, board.getCurrentPlayer());
    }

    @Test
    public void testGetOppositePlayer() {
        assertEquals(player2, board.getOppositePlayer());
    }

    @Test
    public void testChangeCurrentPlayer() {
        board.changeCurrentPlayer();
        assertEquals(player2, board.getCurrentPlayer());
        board.changeCurrentPlayer();
        assertEquals(player1, board.getCurrentPlayer());
    }

    @Test
    public void testValidCoordinates() {
        assertTrue(board.validCoordinates(0, 0));
        assertTrue(board.validCoordinates(7, 7));
        assertFalse(board.validCoordinates(-1, 0));
        assertFalse(board.validCoordinates(8, 5));
        assertFalse(board.validCoordinates(3, 9));
    }

    @Test
    public void testGetPieceAtCoordinates() {
        assertNull(board.getPieceAtCoordinates(0, 2));
        assertNotNull(board.getPieceAtCoordinates(0, 1));
    }

    @Test
    public void testGetPiecesOfColor() {
        List<Piece> whitePieces = board.getPiecesOfColor(Color.WHITE);
        assertEquals(16, whitePieces.size());
    }

    @Test
    public void testGetSafeMovesOfColor() {
        Set<Coordinates> safeMoves = board.getSafeMovesOfColor(Color.WHITE);
        assertEquals(16, safeMoves.size());
    }

    @Test
    public void testMovePiece() {
        Piece pawn = board.getPieceAtCoordinates(1, 0);
        board.movePiece(pawn, 2, 0);
        assertNull(board.getPieceAtCoordinates(1, 0));
        assertNotNull(board.getPieceAtCoordinates(2, 0));
    }

    // More tests can be added for other methods and edge cases
}
