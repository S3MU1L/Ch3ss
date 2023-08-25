package src.main.project.GUI.utils;

import src.main.project.GUI.ChessGUI;
import src.main.project.board.Board;
import src.main.project.board.Color;
import src.main.project.players.BotPlayer;
import src.main.project.players.HumanPlayer;
import src.main.project.players.Player;

import java.util.Random;

/**
 * @author Samuel Malec
 */
public class GuiStarter {
    public static void startGUI(GameType gameType) {
        Random rand = new Random();
        int x = rand.nextInt(2);
        Color firstColor = x == 1 ? Color.BLACK : Color.WHITE;
        Player first = null;
        Player second = null;
        switch (gameType) {
            case OFFLINE_HUMAN_VS_HUMAN -> {
                first = new HumanPlayer(firstColor);
                second = new HumanPlayer(firstColor.getOppositeColor());
            }
            case BOT_VS_HUMAN -> {
                first = new HumanPlayer(firstColor);
                second = new BotPlayer(firstColor.getOppositeColor());
            }
            case BOT_VS_BOT -> {
                first = new BotPlayer(firstColor);
                second = new BotPlayer(firstColor.getOppositeColor());
            }
        }
        assert first != null;
        Board board = new Board(first, second);
        ChessGUI gui = new ChessGUI(board);
    }
}
