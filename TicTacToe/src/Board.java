public class Board {

    private final int  boardSize;
    private char[][] board;
    private int[] colCount;
    private int[] rowCount;
    private int[] diaCount;
    private boolean isWinner;
    public Board(int boardSize) {
        this.boardSize = boardSize;
    }

    public void Initialize(){
        this.board = new char[this.boardSize][this.boardSize];
        this.rowCount = new int[this.boardSize];
        this.colCount = new int[this.boardSize];
        this.diaCount = new int[2];
        this.isWinner = false;
        for(int i =0 ;i<this.boardSize;++i){
            for(int j = 0;j<this.boardSize;++j){
                this.board[i][j] = '_';
            }
        }
    }

    public void printBoard(){
        for(int i = -1;i<this.boardSize;++i){
            String s = (i == -1?" ":String.valueOf(i)) + " ";
            System.out.print(s);
        }
        System.out.println(" ");
        for(int i =0 ;i<this.boardSize;++i){
            System.out.print(i+ " ");
            for(int j = 0;j<this.boardSize;++j){
                System.out.print(board[i][j]+ " ");
            }
            System.out.println(" ");
        }
    }

    public Boolean mark(int row,int col,char marker,int state) throws Exception{
        if(row > this.boardSize || col > this.boardSize){
            System.out.println("Selected cell is not in range");
            return false;
        }

        if(this.board[row][col] != '_'){
            System.out.println("Cell is already marked with the value " + this.board[row][col] + ", Please retry");
            return false;
        }

        this.board[row][col] = marker;
        updateResults(row,col,state);
        return true;
    }

    public void updateResults(int row,int col, int state){
        if(row == col)
            this.diaCount[0]+=state;

        if(row+col == this.boardSize)
            this.diaCount[1]+=state;

        this.rowCount[row]+=state;
        this.colCount[col]+=state;

        if(this.diaCount[0] == this.boardSize || this.diaCount[1] == this.boardSize || this.rowCount[row] == this.boardSize || this.colCount[col] == this.boardSize)
            this.isWinner = true;

        if(this.diaCount[0] == -this.boardSize || this.diaCount[1] == -this.boardSize || this.rowCount[row] == -this.boardSize || -this.colCount[col] == -this.boardSize)
            this.isWinner = true;
    }

    public boolean checkWinner(){
        return isWinner;
    }
}
