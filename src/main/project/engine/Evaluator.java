package src.main.project.engine;

import src.main.project.board.Board;
import src.main.project.board.Coordinates;
import src.main.project.pieces.Piece;

import java.util.List;

/**
 * @author Samuel Malec
 */
public class Evaluator {

    private static final int MAX_DEPTH = 3;

    public static void makeBestMove(Board board) {
        int bestScore = Integer.MIN_VALUE;
        Piece bestPiece = null;
        Coordinates bestCoordinate = null;
        List<Piece> pieces = board.getPiecesOfColor(board.getCurrentColor());

        for (Piece piece : pieces) {
            List<Coordinates> safePositions = board.getSafeMovesOfPiece(piece);
            Coordinates originalCoordinates = board.getCoordinatesOfPiece(piece);
            for (Coordinates coord : safePositions) {
                Piece pieceAtCoords = board.getPieceAtCoordinates(coord);
                board.putPiece(piece, coord);
                board.putPiece(null, originalCoordinates);
                int score = minimax(board, MAX_DEPTH - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestPiece = piece;
                    bestCoordinate = coord;
                }
                board.putPiece(piece, originalCoordinates);
                board.putPiece(pieceAtCoords, coord);
            }
        }
        bestPiece.move(bestCoordinate);
    }

    public static int minimax(Board board, int depth, int alpha, int beta, boolean isMaximizing) {
        if (depth == 0) {
            return evaluate(board);
        }

        List<Piece> pieces = board.getPiecesOfColor(isMaximizing ? board.getCurrentColor() : board.getCurrentColor().getOppositeColor());

        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (Piece piece : pieces) {
                List<Coordinates> safePositions = board.getSafeMovesOfPiece(piece);
                Coordinates originalCoordinates = board.getCoordinatesOfPiece(piece);
                for (Coordinates coord : safePositions) {
                    Piece pieceAtCoords = board.getPieceAtCoordinates(coord);
                    board.putPiece(piece, coord);
                    board.putPiece(null, originalCoordinates);
                    int eval = minimax(board, depth - 1, alpha, beta, false);
                    maxEval = Math.max(maxEval, eval);
                    alpha = Math.max(alpha, eval);
                    board.putPiece(piece, originalCoordinates);
                    board.putPiece(pieceAtCoords, coord);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (Piece piece : pieces) {
                List<Coordinates> safePositions = board.getSafeMovesOfPiece(piece);
                Coordinates originalCoordinates = board.getCoordinatesOfPiece(piece);
                for (Coordinates coord : safePositions) {
                    Piece pieceAtCoords = board.getPieceAtCoordinates(coord);
                    board.putPiece(piece, coord);
                    board.putPiece(null, originalCoordinates);
                    int eval = minimax(board, depth - 1, alpha, beta, true);
                    minEval = Math.min(minEval, eval);
                    beta = Math.min(beta, eval);
                    board.putPiece(piece, originalCoordinates);
                    board.putPiece(pieceAtCoords, coord);
                    if (beta <= alpha) {
                        break;
                    }
                }
            }
            return minEval;
        }
    }

    public static int evaluate(Board board) {
        int result = 0;
        for (int y = 0; y < Board.BOARD_SIZE; y++) {
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                if (board.isEmpty(x, y)) {
                    continue;
                }
                Piece currPiece = board.getPieceAtCoordinates(x, y);
                if (currPiece.getColor().equals(board.getCurrentColor())) {
                    result += currPiece.getValue() * currPiece.getPositionTable()[y][x];
                } else {
                    result -= Math.abs(currPiece.getValue() * currPiece.getPositionTable()[y][x]);
                }
            }
        }
        return result;
    }
}
