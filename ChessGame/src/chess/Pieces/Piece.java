package chess.Pieces;

import chess.moveStrategy.MoveStrategy;

import java.util.List;

public abstract class Piece {

    private final int color;
    private Position position;
    private List<MoveStrategy> moveStrategies;

    public Piece(int color,Position position,List<MoveStrategy> moveStrategies) {
        this.color = color;
        this.position = position;
        this.moveStrategies = moveStrategies;
    }

    public Position getPiecePosition(){
        return  this.position;
    }

    public int getColor(){
        return this.color;
    }

    public void move(Position position){
        this.position = position;
    }
    public abstract List<Position> getAllMoves();
    public abstract PieceType getPieceType();
}
