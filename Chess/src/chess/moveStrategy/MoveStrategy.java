package chess.moveStrategy;

import chess.Pieces.Piece;
import chess.Pieces.Position;

import java.util.List;

public interface MoveStrategy {
    List<Position> getAllValidPosition(Piece piece);
}
