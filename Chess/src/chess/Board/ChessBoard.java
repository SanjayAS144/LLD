package chess.Board;

import chess.Pieces.Piece;
import chess.Pieces.PieceFactory;
import chess.Pieces.PieceType;
import chess.Pieces.Position;

import java.util.*;

public class ChessBoard {
    private final String[][] board;
    private final Map<String, Piece> pieceMap;
    private final List<String> whitePiece;
    private final List<String> blackPiece;

    public ChessBoard() {
        this.board = new String[8][8];
        this.pieceMap = new HashMap<String,Piece>();
        this.whitePiece = new ArrayList<String>(Arrays.asList("WR1","WH1","WB1","WQ1","WK1","WB2","WH2","WR2"));
        this.blackPiece= new ArrayList<String>(Arrays.asList("BR1","BH1","BB1","BQ1","BK1","BB2","BH2","BR2"));
    }

    public void init(){
        addPawns(this.whitePiece,0);
        addPawns(this.blackPiece,1);
        createBoard(this.whitePiece,0,0);
        createBoard(this.blackPiece,1,7);
    }

    public void printBoard(){
        for(int i = 0;i<8;i++){
            for(int j = 0;j<8;j++){
                System.out.print(this.board[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public Piece getPiece(Position position){
        return pieceMap.get(this.board[position.getX()][position.getY()]);
    }

    private void createBoard(List<String> pieces,int color,int i){
        int j = 0;
        for(String piece:pieces){
            char pieceType = piece.charAt(1);
            pieceMap.put(piece, PieceFactory.getPiece(pieceType,color, new Position(i,j)));
            this.board[i][j] = piece;
            j++;
            if(j>=8){
                i += i == 0? 1:-1;
                j = 0;
            }
        }
    }

    private void addPawns(List<String> pieces,int color){
        String pawn = color == 0?"WP":"BP";
        for(int i = 0;i<8;i++)pieces.add(pawn+String.valueOf(i+1));
    }


}
