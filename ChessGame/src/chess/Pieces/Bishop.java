package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Bishop extends Piece{
    public Bishop(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.bishop;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }
}
