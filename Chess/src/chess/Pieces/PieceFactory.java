package chess.Pieces;

import chess.moveStrategy.MoveDiagonally;
import chess.moveStrategy.MoveStraight;
import chess.moveStrategy.MoveStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PieceFactory {

    private static final Map<Character,PieceType> pieceMap = new HashMap<>(){{
        put('R',PieceType.rook);
        put('H',PieceType.rook);
        put('B',PieceType.rook);
        put('Q',PieceType.rook);
        put('K',PieceType.rook);
        put('P',PieceType.rook);
    }};

    public static Piece getPiece(char type,int color,Position position){
        PieceType pieceType = pieceMap.get(type);
        switch (pieceType){
            case PieceType.pawn -> {
                return new Pawn(color, position, new ArrayList<MoveStrategy>(){});
            }
            case PieceType.bishop -> {
                return new Bishop(color, position, List.of(new MoveDiagonally()));
            }
            case PieceType.knight -> {
                return new Knight(color, position, new ArrayList<MoveStrategy>(){});
            }
            case PieceType.rook -> {
                return new Rook(color, position, List.of(new MoveStraight()));
            }
            case PieceType.queen -> {
                return new Queen(color, position, List.of(new MoveStraight(),new MoveDiagonally()));
            }
            case PieceType.king -> {
                return new King(color, position, List.of(new MoveStraight(),new MoveDiagonally()));
            }
            default -> {
                return null;
            }
        }
    }
}
