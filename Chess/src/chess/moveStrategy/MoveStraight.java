package chess.moveStrategy;

import chess.Pieces.Piece;
import chess.Pieces.Position;

import java.util.List;

public class MoveStraight implements MoveStrategy{
    private Object board;

    public MoveStraight(Object board) {
        this.board = board;
    }
    public MoveStraight() {
    }
    @Override
    public List<Position> getAllValidPosition(Piece piece) {
        return List.of();
    }
}
