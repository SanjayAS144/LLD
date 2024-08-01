import java.util.Scanner;

public class Game {
    private final Player player1;
    private final Player player2;
    private final int boardSize;
    private final Board board;
    private final int totalTurns;

    private Game(Player player1, Player player2, int boardSize) {
        this.player1 = player1;
        this.player2 = player2;
        this.boardSize = boardSize;
        this.board = new Board(boardSize);
        this.totalTurns = boardSize*boardSize;
    }

    public void StartGame(){
        System.out.println("Provide the row number and the col number to set the value : ");
        this.board.Initialize();
        this.board.printBoard();
        playGame();
    }

    private void playGame(){
        try {
            int currentTurn = 0;
            Scanner scanner = new Scanner(System.in);
            while(currentTurn < totalTurns){
                Player currentPlayer = currentTurn%2 == 0?player1:player2;
                System.out.println(currentPlayer.getName() + " Enter row and column : ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();
                Boolean isMarked = this.board.mark(row,col,currentPlayer.getMarker(),currentPlayer.getState());
                if(isMarked){
                    boolean checkForWinner = this.board.checkWinner();
                    if(checkForWinner){
                        System.out.printf("Hurry! %s won the Game",currentPlayer.getName());
                        break;
                    }
                    currentTurn++;
                }
                this.board.printBoard();

            }
        }catch (Exception ex){
            System.out.println("Aborted the Game : "+ex.getMessage());
        }
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public static class GameBuilder{
        private Player player1;
        private Player player2;
        private int boardSize = 3;

        public GameBuilder() {
        }

        public GameBuilder addPlayer1(String name,Character marker) throws Exception {
            marker = marker == null ?'X':marker;
            if(marker.equals('_')){
                throw new Exception("can not use this marker");
            }
            this.player1 = new Player
                    .PlayerBuilder()
                    .setName(name)
                    .setMarker(marker)
                    .setState(1)
                    .build();
            return this;
        }

        public GameBuilder addPlayer2(String name,Character marker) throws Exception {
            marker = marker == null ?'O':marker;
            if(marker.equals('_')){
                throw new Exception("can not use this marker");
            }
            this.player2 = new Player
                    .PlayerBuilder()
                    .setName(name)
                    .setMarker(marker)
                    .setState(-1)
                    .build();
            return this;
        }

        public void setBoardSize(int boardSize) {
            this.boardSize = boardSize;
        }

        public Game build() throws Exception {
            if(player1 == null || player2 == null)
                throw new Exception("Add both the players to continue with the game");
            return new Game(this.player1,this.player2,this.boardSize);
        }
    }
}
