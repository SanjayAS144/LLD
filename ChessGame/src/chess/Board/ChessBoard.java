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
    private final String defaultVal = "___";
    private final int white = 0;
    private final int black = 1;

    public ChessBoard() {
        this.board = new String[8][8];
        this.pieceMap = new HashMap<String,Piece>();
        this.whitePiece = new ArrayList<String>(Arrays.asList("WR1","WH1","WB1","WQ1","WK1","WB2","WH2","WR2"));
        this.blackPiece= new ArrayList<String>(Arrays.asList("BR1","BH1","BB1","BQ1","BK1","BB2","BH2","BR2"));
    }

    public void init(){
        Arrays.setAll(this.board,i->{
            String[] row = new String[8];
            Arrays.fill(row,defaultVal);
            return row;
        });
        addPawns(this.whitePiece,white);
        addPawns(this.blackPiece,black);
        createBoard(this.whitePiece,0,7);
        createBoard(this.blackPiece,1,0);
    }

    public void printBoard(){
        System.out.print("  ");
        for(int i = 0;i<8;i++){
            System.out.print(" "+ String.valueOf(i) + "  ");
        }
        System.out.println(" ");
        for(int i = 0;i<8;i++){
            System.out.print(String.valueOf(i) + " ");
            for(int j = 0;j<8;j++){
                System.out.print(this.board[i][j]+" ");
            }
            System.out.println(" ");
        }
    }

    public boolean move(Position pos1, Position pos2,int turn){
       int x1 = pos1.getX();
       int y1 = pos1.getY();
       if(this.board[x1][y1].equals(defaultVal)){
           System.out.println("you selected a blank square");
           return false;
       }

       Piece selectedPiece = getPiece(pos1);
       if(turn%2 != selectedPiece.getColor()){
           System.out.println("you are not allowed to move this " + selectedPiece.getPieceType().toString());
           return false;
       }

        int x2 = pos2.getX();
        int y2 = pos2.getY();
        if(!this.board[x2][y2].equals(defaultVal)){
            System.out.println("Can not move to selected position");
            return false;
        }

       selectedPiece.move(pos2);
       this.board[pos2.getX()][pos2.getY()] = this.board[x1][y1];
       this.board[x1][y1] = defaultVal;
       return true;
    }

    private Piece getPiece(Position selectedPosition){
        return pieceMap.get(this.board[selectedPosition.getX()][selectedPosition.getY()]);
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
