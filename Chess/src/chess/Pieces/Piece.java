package chess.Pieces;

import java.util.List;

public interface Piece {
    Position getPiecePosition();
    PieceType getPieceType();
    List<Position> getAllMoves();
    int getColor();
    void move(Position position);
}
