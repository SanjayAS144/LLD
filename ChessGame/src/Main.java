import chess.Board.ChessBoard;
import chess.Board.Game;
import chess.Pieces.Pawn;
import chess.Pieces.Piece;
import chess.Pieces.PieceType;
import chess.Pieces.Position;
import chess.moveStrategy.MoveDiagonally;
import chess.moveStrategy.MoveStraight;
import chess.moveStrategy.MoveStrategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        try {
            Game game = new Game.GameBuilder().addPlayer1("Sanjay").addPlayer2("Bot").build();
            game.startGame();

        }catch (Exception ex){
            System.out.println("Game Crashed ! " + ex.getMessage());
        }
    }
}