package src.test;

import org.junit.jupiter.api.Test;
import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;
import src.main.project.players.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Samuel Malec
 */
public class PawnTest {
    private Board board = new Board(new Player(Color.WHITE), new Player(Color.BLACK));


    @Test
    void correctMovements() {
        Piece piece = new Pawn(Color.WHITE, board);
        Coordinates coords = new Coordinates(0, 6);
        board.putPiece(piece, coords);

        List<Coordinates> expected = new ArrayList<>();
        expected.add(new Coordinates(0, 5));
        expected.add(new Coordinates(0, 4));
        assertEquals(piece.getPossibleMoves(), expected);

        piece.move(0, 4);
        expected.clear();
        expected.add(new Coordinates(0, 3));
        assertEquals(piece.getPossibleMoves(), expected);
        piece.move(0, 3);

        Piece enPassantPawn = new Pawn(Color.BLACK, board);
        Coordinates newCoords = new Coordinates(1, 1);
        board.putPiece(enPassantPawn, newCoords);
        expected.clear();
        expected.add(new Coordinates(1, 2));
        expected.add(new Coordinates(1, 3));
        assertEquals(enPassantPawn.getPossibleMoves(), expected);
        enPassantPawn.move(1, 3);

        // now testing enPassant
        expected.clear();
        expected.add(new Coordinates(0, 2));
        expected.add(new Coordinates(1, 2));
        assertEquals(piece.getPossibleMoves(), expected);

        piece.move(1, 2);
        assertEquals(board.getPiecesOfColor(Color.BLACK).size(), 0);
        assertEquals(board.getPiecesOfColor(Color.WHITE).size(), 1);
    }
}
