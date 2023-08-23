package src.test;

import org.junit.jupiter.api.Test;
import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.board.Coordinates;
import src.main.project.pieces.Knight;
import src.main.project.pieces.Piece;
import src.main.project.players.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KnightTest {
    private final Board board = new Board(new Player(Color.WHITE), new Player(Color.BLACK));

    @Test
    void correctMovements() {
        Piece piece = new Knight(Color.WHITE, board);
        Coordinates coords = new Coordinates(3, 3);
        board.putPiece(piece, coords);

        List<Coordinates> expected = new ArrayList<>();
        expected.add(new Coordinates(1, 2));
        expected.add(new Coordinates(1, 4));
        expected.add(new Coordinates(2, 1));
        expected.add(new Coordinates(2, 5));
        expected.add(new Coordinates(4, 1));
        expected.add(new Coordinates(4, 5));
        expected.add(new Coordinates(5, 2));
        expected.add(new Coordinates(5, 4));
        List<Coordinates> received = piece.getPossibleMoves();
        assertEquals(received.size(), expected.size());
        for (var move : received) {
            assertTrue(expected.contains(move));
        }

        piece.move(2, 5);
        expected.clear();
        expected.add(new Coordinates(0, 4));
        expected.add(new Coordinates(3, 3));
        expected.add(new Coordinates(1, 7));
        expected.add(new Coordinates(4, 6));
        expected.add(new Coordinates(0, 6));
        expected.add(new Coordinates(3, 7));
        expected.add(new Coordinates(4, 4));
        expected.add(new Coordinates(1, 3));
        received = piece.getPossibleMoves();
        assertEquals(received.size(), expected.size());
        for (var move : received) {
            assertTrue(expected.contains(move));
        }
    }
}