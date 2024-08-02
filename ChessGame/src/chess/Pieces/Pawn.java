package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Pawn extends Piece{

    public Pawn(int color,Position position,List<MoveStrategy> moveStrategies) {
        super(color,position,moveStrategies);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.pawn;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }

}
