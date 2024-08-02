package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Queen extends Piece{
    public Queen(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.queen;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }
}
