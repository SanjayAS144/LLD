package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Knight extends Piece{
    public Knight(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.knight;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }
}
