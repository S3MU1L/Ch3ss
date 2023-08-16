package src.test;

import org.junit.jupiter.api.Test;
import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.Notation;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Samuel Malec
 */
public class NotationTest {

    @Test
    void coordsFromNotation() {
        Coordinates coords;
        for (int y = Board.BOARD_SIZE - 1; y >= 0; y--) {
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                coords = new Coordinates(x, y);
                StringBuilder sb = new StringBuilder();
                sb.append((char) ('a' + x));
                sb.append((char) (Board.BOARD_SIZE - y + '0'));
                assertEquals(Notation.coordsFromNotation(sb.toString()), coords);
            }
        }
    }
}
