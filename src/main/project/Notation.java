package src.main.project;

/**
 * @author Samuel Malec
 */
public class Notation {
    public static boolean isCorrectNotation(String notation) {
        return true;
    }

    public static Coordinates coordsFromNotation(String notation) {
        int x = Character.toLowerCase(notation.charAt(0)) - 'a';
        int y = Board.BOARD_SIZE - 1 - Integer.parseInt(String.valueOf(notation.charAt(1)));
        System.out.println(x + " " + y);
        return new Coordinates(x, y);
    }
}
