package chess.Board;

import chess.Pieces.Position;
import chess.Player.Player;

import java.util.Scanner;

public class Game {

    private final Player player1;
    private final Player player2;
    private final ChessBoard board;

    private Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.board = new ChessBoard();
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void startGame(){
        this.board.init();
        this.board.printBoard();
        play();
    }

    public void play(){
        Scanner scanner = new Scanner(System.in);
        int turn = 0;
        while(turn<10){
            String name = turn%2 == 0 ? player1.getName() : player2.getName();
            System.out.println(name + " : Select a piece play ");
            int x1 = scanner.nextInt();
            int y1 = scanner.nextInt();
            System.out.println("Select a position to move the piece");
            int x2 = scanner.nextInt();
            int y2 = scanner.nextInt();
            boolean res = this.board.move(new Position(x1,y1),new Position(x2,y2),turn);
            if(res){
                this.board.printBoard();
                turn++;
            }
        }
    }









    public static class GameBuilder{
        private Player player1;
        private Player player2;

        public GameBuilder() {
        }

        public GameBuilder addPlayer1(String name){
            player1 = new Player(name);
            return this;
        }

        public GameBuilder addPlayer2(String name){
            player2 = new Player(name);
            return this;
        }

        public Game build() throws Exception {
            if(player1 == null || player2 == null){
                throw new Exception("Players are not added");
            }
            return new Game(player1,player2);
        }

    }
}
