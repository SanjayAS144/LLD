package chess.moveStrategy;

import chess.Pieces.Piece;
import chess.Pieces.Position;

import java.util.List;

public class MoveDiagonally implements MoveStrategy{
    private Object board;

    public MoveDiagonally(Object board) {
        this.board = board;
    }

    public MoveDiagonally() {
    }

    @Override
    public List<Position> getAllValidPosition(Piece piece) {
        return List.of();
    }
}
