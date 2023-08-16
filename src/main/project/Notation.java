package src.main.project;

/**
 * @author Samuel Malec
 */
public class Notation {
    public static boolean isCorrectNotation(String notation) {
        if (notation.length() != 2) {
            return false;
        }
        return notation.charAt(0) >= 'a' && notation.charAt(1) <= 'h'
                && notation.charAt(1) - '1' < Board.BOARD_SIZE;
    }

    public static Coordinates coordsFromNotation(String notation) {
        int x = notation.charAt(0) - 'a';
        int y = Board.BOARD_SIZE - Integer.parseInt(String.valueOf(notation.charAt(1)));
        System.out.println(x + " " + y);
        return new Coordinates(x, y);
    }
}
