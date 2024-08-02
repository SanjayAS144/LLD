package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public class Pawn implements Piece{

    private final int color;
    private Position position;
    private List<MoveStrategy> moveStrategies;

    public Pawn(int color,Position position,List<MoveStrategy> moveStrategies) {
        this.color = color;
        this.position = position;
        this.moveStrategies = moveStrategies;
    }

    @Override
    public Position getPiecePosition() {
        return this.position;
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.pawn;
    }

    @Override
    public List<Position> getAllMoves() {
        return List.of();
    }

    @Override
    public int getColor() {
        return this.color;
    }

    @Override
    public void move(Position position) {
    }

}
