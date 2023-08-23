package src.main.project.GUI;

import src.main.project.board.Board;
import src.main.project.board.Coordinates;
import src.main.project.board.GameState;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;
import src.main.project.pieces.Queen;
import src.main.project.players.BotPlayer;
import src.main.project.players.HumanPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static src.main.project.board.GameState.CHECK;
import static src.main.project.board.GameState.PLAYING;

/**
 * @author Samuel Malec
 */
public class ChessGUI {
    public static final int BOARD_SIZE = 8;
    public static final int SQUARE_SIZE = 80;
    public static final Color WHITE_COLOR = new Color(238, 238, 210);
    public static final Color BLACK_COLOR = new Color(118, 150, 86);
    public static final Color ATTACKED_COLOR = new Color(232, 106, 81);
    public static final Color HIGHLIGHTED_COLOR = new Color(186, 202, 68);
    public static final int RADIUS = 15;
    private final JFrame frame;
    private final JPanel chessPanel;
    private List<Coordinates> possibleMoves = null;
    private final Board board;
    private Coordinates firstClick = null;
    private int whiteMilliseconds = 600000;
    private int blackMilliseconds = 600000;
    private GameState gameState = PLAYING;
    private final Timer timer;

    public ChessGUI(Board board) {
        this.board = board;
        frame = new JFrame("Chess");
        frame.setLocationRelativeTo(null);
        frame.setSize(700, 700);
        frame.setLayout(new BorderLayout());

        JLabel blackTime = new JLabel(millisecondsToString(blackMilliseconds));
        Font timeFont = new Font("Comic-Sans", Font.BOLD, 20);
        blackTime.setFont(timeFont);
        blackTime.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(blackTime, BorderLayout.NORTH);

        chessPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        chessPanel.addMouseListener(new ChessMouseListener(this));
        frame.add(chessPanel, BorderLayout.CENTER);

        JLabel whiteTime = new JLabel(millisecondsToString(whiteMilliseconds));
        whiteTime.setFont(timeFont);
        whiteTime.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(whiteTime, BorderLayout.SOUTH);

        timer = new Timer(250, e -> {
            if (getBoard().getCurrentColor().equals(src.main.project.board.Color.WHITE)) {
                whiteMilliseconds -= 250;
            } else {
                blackMilliseconds -= 250;
            }

            if ((getBoard().getCurrentColor() == src.main.project.board.Color.BLACK) &&
                    (getBoard().getCurrentPlayer() instanceof HumanPlayer) &&
                    (getBoard().getOppositePlayer() instanceof HumanPlayer) ||
                    (getBoard().getOppositePlayer().getColor() == src.main.project.board.Color.WHITE && getBoard().getOppositePlayer() instanceof BotPlayer)) {
                whiteTime.setText(millisecondsToString(blackMilliseconds));
                blackTime.setText(millisecondsToString(whiteMilliseconds));
            } else {
                whiteTime.setText(millisecondsToString(whiteMilliseconds));
                blackTime.setText(millisecondsToString(blackMilliseconds));
            }
        });

        timer.start();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        if (board.getCurrentPlayer() instanceof BotPlayer) {
            handleBotPlayer();
        }
        drawBoard();
    }

    public List<Coordinates> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<Coordinates> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }

    public Board getBoard() {
        return board;
    }

    public Coordinates getFirstClick() {
        return firstClick;
    }

    public void setFirstClick(Coordinates firstClick) {
        this.firstClick = firstClick;
    }

    private String millisecondsToString(int milliseconds) {
        int seconds = milliseconds / 1000;
        int minutes = seconds / 60;
        seconds %= 60;
        return String.format("%02d:%02d", minutes, seconds);
    }

    public void drawBoard() {
        chessPanel.removeAll();
        src.main.project.board.Color currColor = getBoard().getCurrentColor();
        if (getBoard().getCurrentPlayer() instanceof BotPlayer) {
            currColor = currColor.getOppositeColor();
        }

        int from = 0;
        int to = BOARD_SIZE;
        int increment = 1;
        if (currColor.equals(src.main.project.board.Color.BLACK)) {
            from = BOARD_SIZE - 1;
            to = -1;
            increment = -1;
        }

        for (int y = from; y != to; y += increment) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                JPanel squarePanel = new JPanel();
                squarePanel.setPreferredSize(new Dimension(SQUARE_SIZE, SQUARE_SIZE));
                if ((y + x) % 2 == 0) {
                    squarePanel.setBackground(WHITE_COLOR);
                } else {
                    squarePanel.setBackground(BLACK_COLOR);
                }

                if (getFirstClick() != null && y == getFirstClick().y() && x == getFirstClick().x()) {
                    squarePanel.setBackground(HIGHLIGHTED_COLOR);
                }

                if (getPossibleMoves() != null && getPossibleMoves().contains(new Coordinates(x, y))) {
                    if (!board.isEmpty(x, y)) {
                        squarePanel.setBackground(ATTACKED_COLOR);
                    } else {
                        squarePanel.setLayout(new BorderLayout());
                        JPanel circlePanel = new JPanel() {
                            @Override
                            protected void paintComponent(Graphics g) {
                                super.paintComponent(g);
                                g.setColor(Color.RED);
                                int diameter = RADIUS * 2;
                                int x = (getWidth() - diameter) / 2;
                                int y = (getHeight() - diameter) / 2;
                                g.fillOval(x, y, diameter, diameter);
                            }
                        };
                        circlePanel.setBackground(squarePanel.getBackground());
                        squarePanel.add(circlePanel, BorderLayout.CENTER);
                    }
                }

                Piece currPiece = board.getPieceAtCoordinates(x, y);
                if (currPiece instanceof Pawn && (currPiece.getColor() == src.main.project.board.Color.WHITE && y == 0
                        || currPiece.getColor() == src.main.project.board.Color.BLACK && y == 7)) {
                    currPiece = new Queen(currPiece.getColor(), board);
                    board.putPiece(currPiece, x, y);
                }
                if (currPiece != null) {
                    ImageIcon pieceIcon = currPiece.getImageIcon();
                    JLabel pieceLabel = new JLabel(pieceIcon);
                    squarePanel.add(pieceLabel);
                }
                chessPanel.add(squarePanel);
            }
        }
        chessPanel.revalidate();
        chessPanel.repaint();
        frame.pack();
        setState();
        if ((gameState.equals(PLAYING) || gameState.equals(CHECK)) && board.getCurrentPlayer() instanceof BotPlayer) {
            handleBotPlayer();
        }
    }

    public void handleBotPlayer() {
        Random random = new Random();
        List<Piece> pieces = board.getPiecesOfColor(board.getCurrentColor());
        int randomIndex;
        Piece randPiece;
        ArrayList<Coordinates> possibleMoves;
        do {
            randomIndex = random.nextInt(pieces.size());
            randPiece = pieces.get(randomIndex);
            possibleMoves = new ArrayList<>(board.getSafeMovesOfPiece(randPiece));
        } while (possibleMoves.isEmpty());

        randomIndex = random.nextInt(possibleMoves.size());
        randPiece.move(possibleMoves.get(randomIndex));
        drawBoard();
    }

    public void setState() {
        gameState = GameState.gameState(board);
        if (gameState.equals(PLAYING)) {
            return;
        }

        if (gameState.equals(CHECK)) {
            SoundPlayer.playCheckSound();
            return;
        }

        if (gameState.equals(GameState.WHITE)) {
            SoundPlayer.playCheckmateSound();
            JOptionPane.showMessageDialog(frame, "White has won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else if (gameState.equals(GameState.BLACK)) {
            SoundPlayer.playCheckmateSound();
            JOptionPane.showMessageDialog(frame, "Black has won!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        } else if (gameState.equals(GameState.DRAW)) {
            SoundPlayer.playCheckmateSound();
            JOptionPane.showMessageDialog(frame, "It's a draw!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }

        frame.setEnabled(false);
        timer.stop();
    }

}