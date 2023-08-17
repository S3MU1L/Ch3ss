package src.main.project;

import src.main.project.pieces.Bishop;
import src.main.project.pieces.King;
import src.main.project.pieces.Knight;
import src.main.project.pieces.Pawn;
import src.main.project.pieces.Piece;
import src.main.project.pieces.Queen;
import src.main.project.pieces.Rook;


/**
 * @author Samuel Malec
 */
public class Board {
    public static final int BOARD_SIZE = 8;
    private final Piece[][] chessBoard = new Piece[BOARD_SIZE][BOARD_SIZE];

    public Piece[][] getChessBoard() {
        return chessBoard;
    }

    public void initializeBoard() {
        for (int x = 0; x < BOARD_SIZE; x++) {
            chessBoard[1][x] = new Pawn(Color.BLACK, this);
            chessBoard[6][x] = new Pawn(Color.WHITE, this);
        }

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (i == 0 || i == 7) {
                putPiece(new Rook(Color.BLACK, this), i, 0);
                putPiece(new Rook(Color.WHITE, this), i, 7);
            }
            if (i == 1 || i == 6) {
                putPiece(new Knight(Color.BLACK, this), i, 0);
                putPiece(new Knight(Color.WHITE, this), i, 7);
            }
            if (i == 2 || i == 5) {
                putPiece(new Bishop(Color.BLACK, this), i, 0);
                putPiece(new Bishop(Color.WHITE, this), i, 7);
            }
            if (i == 3) {
                putPiece(new Queen(Color.BLACK, this), i, 0);
                putPiece(new Queen(Color.WHITE, this), i, 7);
            }
            if (i == 4) {
                putPiece(new King(Color.BLACK, this), i, 0);
                putPiece(new Queen(Color.WHITE, this), i, 7);
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        char delimiter = '|';
        for (int y = 0; y < BOARD_SIZE; y++) {
            sb.append("  --------------------------------").append(System.lineSeparator());
            sb.append(BOARD_SIZE - y).append(" ").append(delimiter);
            for (int x = 0; x < BOARD_SIZE; x++) {
                Piece currentPiece = getPieceAtCoordinates(x, y);
                String pieceString = currentPiece == null ? " " : currentPiece.toString();
                sb.append(" ").append(pieceString).append(" ").append(delimiter);
            }
            sb.append(System.lineSeparator());
        }
        sb.append("  --------------------------------").append(System.lineSeparator());

        sb.append("   ");
        for (int j = 0; j < BOARD_SIZE; j++) {
            sb.append(" ").append((char) ('A' + j)).append("  ");
        }

        return sb.toString();
    }

    public Coordinates findPieceById(Piece piece) {
        for (int y = 0; y < BOARD_SIZE; y++) {
            for (int x = 0; x < BOARD_SIZE; x++) {
                if (chessBoard[y][x] == null) {
                    continue;
                }
                if (chessBoard[y][x].getId() == piece.getId()) {
                    return new Coordinates(x, y);
                }
            }
        }
        return null;
    }

    public Piece putPiece(Piece piece, Coordinates coords) {
        return putPiece(piece, coords.x(), coords.y());
    }

    public Piece putPiece(Piece piece, int x, int y) {
        chessBoard[y][x] = piece;
        return piece;
    }

    public void movePiece(Piece piece, Coordinates coords) {
        movePiece(piece, coords.x(), coords.y());
    }

    public void movePiece(Piece piece, int x, int y) {
        if (!validCoordinates(x, y)) {
            return;
        }
        Coordinates coords = findPieceById(piece);
        putPiece(null, coords.x(), coords.y());
        putPiece(piece, x, y);
    }

    public boolean isEmpty(int x, int y) {
        return chessBoard[y][x] == null;
    }

    public boolean isEmpty(Coordinates coords) {
        return isEmpty(coords.x(), coords.y());
    }

    public boolean validCoordinates(int x, int y) {
        return (x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE);
    }

    public boolean validCoordinates(Coordinates coords) {
        return validCoordinates(coords.x(), coords.y());
    }

    public Piece getPieceAtCoordinates(int x, int y) {
        if (!validCoordinates(x, y)) {
            return null;
        }
        if (isEmpty(x, y)) {
            return null;
        }
        return chessBoard[y][x];
    }

    public Piece getPieceAtCoordinates(Coordinates coords) {
        return getPieceAtCoordinates(coords.x(), coords.y());
    }

}