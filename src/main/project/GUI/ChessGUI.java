package src.main.project.GUI;

import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.GameState;
import src.main.project.pieces.Bishop;
import src.main.project.pieces.Knight;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;
import src.main.project.pieces.Queen;
import src.main.project.pieces.Rook;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
    private JFrame frame;
    private JPanel chessPanel;
    private List<Coordinates> possibleMoves = null;
    private GameState state;
    private Board board;
    private Coordinates firstClick = null;

    public ChessGUI(Board board) {
        this.board = board;
        frame = new JFrame("Chess");
        frame.setLocationRelativeTo(null);
        frame.setSize(700, 700);
        chessPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        frame.add(chessPanel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        chessPanel.addMouseListener(new ChessMouseListener(this));
        drawBoard();
    }

    public void drawBoard() {
        chessPanel.removeAll();
        for (int y = 0; y < BOARD_SIZE; y++) {
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
                    currPiece = showPawnPromotionDialog(x, y);
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

    public void setState() {
        this.state = GameState.gameState(board);
        GameState state = GameState.gameState(board);
//        if (state.equals(GameState.CHECK)) {
//            System.out.println("Check");
//        } else if (state.equals(GameState.WHITE)) {
//            System.out.println("White has won");
//            frame.disable();
//        } else if (state.equals(GameState.BLACK)) {
//            System.out.println("Black has won");
//            frame.disable();
//        } else if (state.equals(GameState.DRAW)) {
//            System.out.println("Draw");
//            frame.disable();
//        } else {
//            System.out.println("Playing");
//        }


    }

    private Piece showPawnPromotionDialog(int x, int y) {
        Object[] options = {"Queen", "Rook", "Bishop", "Knight"};
        int choice = JOptionPane.showOptionDialog(frame,
                "Select a piece to promote your pawn:",
                "Pawn Promotion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        Piece newPiece = null;
        switch (choice) {
            case 0:
                newPiece = new Queen(board.getCurrentColor().getOppositeColor(), board);
                break;
            case 1:
                newPiece = new Rook(board.getCurrentColor().getOppositeColor(), board);
                break;
            case 2:
                newPiece = new Bishop(board.getCurrentColor().getOppositeColor(), board);
                break;
            case 3:
                newPiece = new Knight(board.getCurrentColor().getOppositeColor(), board);
                break;
        }
        return newPiece;
    }
}
