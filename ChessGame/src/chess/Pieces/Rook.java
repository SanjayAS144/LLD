package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Rook extends Piece{
    public Rook(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.rook;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }
}
