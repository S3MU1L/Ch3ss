package src.main.project.GUI;

import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.GameState;
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

        Timer timer = new Timer(250, e -> {
            if (getBoard().getCurrentColor().equals(src.main.project.Color.WHITE)) {
                whiteMilliseconds -= 250;
            } else {
                blackMilliseconds -= 250;
            }

            // When two people are playing against each other, black player will use 'white' time label, since the sides switch
            if (getBoard().getCurrentColor().equals(src.main.project.Color.BLACK) &&
                    getBoard().getCurrentPlayer() instanceof HumanPlayer && getBoard().getOppositePlayer() instanceof HumanPlayer) {
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
        src.main.project.Color currColor = getBoard().getCurrentColor();
        if (getBoard().getCurrentPlayer() instanceof BotPlayer) {
            currColor = currColor.getOppositeColor();
        }

        int from = 0;
        int to = BOARD_SIZE;
        int increment = 1;
        if (currColor.equals(src.main.project.Color.BLACK)) {
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
                if (currPiece instanceof Pawn && (currPiece.getColor() == src.main.project.Color.WHITE && y == 0
                        || currPiece.getColor() == src.main.project.Color.BLACK && y == 7)) {
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

        if (board.getCurrentPlayer() instanceof BotPlayer) {
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
        GameState state = GameState.gameState(board);
        if (state.equals(GameState.CHECK)) {
            SoundPlayer.playCheckSound();
        } else if (state.equals(GameState.WHITE)) {
            SoundPlayer.playCheckmateSound();
            frame.setEnabled(false);
        } else if (state.equals(GameState.BLACK)) {
            SoundPlayer.playCheckmateSound();
            frame.setEnabled(false);
        } else if (state.equals(GameState.DRAW)) {
            SoundPlayer.playCheckmateSound();
            frame.setEnabled(false);
        }
    }

    // After hours of debugging I just decided to auto-promote pawns to queens, since this dialog was laggy
//    private Piece showPawnPromotionDialog() {
//        SoundPlayer.playPromoteSound();
//        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
//        int choice = JOptionPane.showOptionDialog(frame, "Select a piece to promote your pawn:", "Pawn Promotion", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
//
//        Piece newPiece = null;
//        switch (choice) {
//            case 0:
//                newPiece = new Queen(board.getCurrentColor().getOppositeColor(), board);
//                break;
//            case 1:
//                newPiece = new Rook(board.getCurrentColor().getOppositeColor(), board);
//                break;
//            case 2:
//                newPiece = new Bishop(board.getCurrentColor().getOppositeColor(), board);
//                break;
//            case 3:
//                newPiece = new Knight(board.getCurrentColor().getOppositeColor(), board);
//                break;
//        }
//        return newPiece;
//    }
}
