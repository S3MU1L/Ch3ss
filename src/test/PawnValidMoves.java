package src.test;

import org.junit.jupiter.api.Test;
import src.main.project.Board;
import src.main.project.Color;
import src.main.project.Coordinates;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Samuel Malec
 */
public class PawnValidMoves {
    private Board board = new Board();


    @Test
    void correctMovements() {
        Board board = new Board();
        Piece piece = new Pawn(Color.WHITE, board);
        Coordinates coords = new Coordinates(0, 6);
        board.putPiece(piece, coords);

        List<Coordinates> expected = new ArrayList<>();
        expected.add(new Coordinates(0, 5));
        expected.add(new Coordinates(0, 4));

        assertEquals(piece.getPossibleMoves(), expected);
    }
}
