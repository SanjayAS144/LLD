import java.sql.SQLOutput;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        try {
            Game game = new Game.GameBuilder().addPlayer1("Sanjay",'X').addPlayer2("Bot",'O').build();
            game.StartGame();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}