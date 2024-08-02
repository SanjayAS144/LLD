package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class King extends Piece{
    public King(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.king;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }
}
