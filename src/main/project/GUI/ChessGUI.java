package src.main.project.GUI;

import src.main.project.Board;
import src.main.project.Coordinates;
import src.main.project.pieces.Piece;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * @author Samuel Malec
 */
public class ChessGUI {
    public static final int BOARD_SIZE = 8;
    public static final int SQUARE_SIZE = 80;
    public static final Color WHITE_COLOR = new Color(228, 230, 237);
    public static final Color BLACK_COLOR = new Color(99, 166, 107);
    public static final Color ATTACKED_COLOR = new Color(232, 106, 81);
    public static final int RADIUS = 15;
    private JFrame frame;
    private JPanel chessPanel;
    private List<Coordinates> attackedCoordinates = null;
    private Board board;

    public ChessGUI(Board board) {
        this.board = board;
        frame = new JFrame("Chess");
        frame.setLocationRelativeTo(null);
        frame.setSize(BOARD_SIZE * SQUARE_SIZE, BOARD_SIZE * SQUARE_SIZE);
        chessPanel = new JPanel(new GridLayout(BOARD_SIZE, BOARD_SIZE));
        frame.add(chessPanel);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        chessPanel.addMouseListener(new ChessMouseListener(this, board));
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

                if (getAttackedCoordinates() != null && getAttackedCoordinates().contains(new Coordinates(x, y))) {
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
    }

    public List<Coordinates> getAttackedCoordinates() {
        return attackedCoordinates;
    }

    public void setAttackedCoordinates(List<Coordinates> attackedCoordinates) {
        this.attackedCoordinates = attackedCoordinates;
    }
}
